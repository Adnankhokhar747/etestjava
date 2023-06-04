package com.example.einvoicing.helpers;

import com.example.einvoicing.dto.InvoiceDTO;
import com.example.einvoicing.entities.*;
import com.example.einvoicing.mapper.CreditInvoiceB2CMapper;
import com.example.einvoicing.repositories.CompanyRepo;
import com.example.einvoicing.repositories.CreditB2CLineitemRepo;
import com.example.einvoicing.repositories.CreditInvoiceB2CRepo;
import com.example.einvoicing.repositories.UserRepo;
import com.example.einvoicing.util.Utility;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CreditInvoiceB2CHelper {
    private Gson gson = new Gson();
    @Autowired
    UserRepo userRepo;
    @Autowired
    CompanyRepo companyRepo;
    @Autowired
    CreditB2CLineitemRepo creditB2CLineitemRepo;
    @Autowired
    CreditInvoiceB2CRepo creditInvoiceB2CRepo;

    public String saveCreditInvoiceB2C(CreditInvoiceB2C creditInvoiceB2C) {
        ErrorCustom error = new ErrorCustom();
        String jsonError;
        try {
            setInvoice(creditInvoiceB2C);
            creditInvoiceB2CRepo.save(creditInvoiceB2C);
            List<CreditB2CLineItem> lineItems = creditInvoiceB2C.getLineItemList();
            for (CreditB2CLineItem lineItem : lineItems) {
                lineItem.setInvoiceId(creditInvoiceB2C);
            }
            creditB2CLineitemRepo.saveAll(lineItems);
            error.setErrorStatus("success");
            error.setError("Credit note saved successfully");
            jsonError = gson.toJson(error);
            return jsonError;
        }catch (Exception e) {
            System.out.println("Error in saving Credit Invoice : "+e.getLocalizedMessage());
            return gson.toJson("Error in saving Credit Invoice : "+e.getLocalizedMessage());
        }
    }
    private void setInvoice(CreditInvoiceB2C invoice) throws NoSuchAlgorithmException {
        invoice.setSerialNo(getAvaiableId(invoice.getCompanyID()));
//        UUID uuid = UUID.fromString("00809e66-36d5-436f-93c4-e4e2c76cce0d");
        invoice.setUuid(String.valueOf(UUID.randomUUID()));
        invoice.setHash(Utility.encrypt(String.valueOf(invoice.getId())));
        invoice.setSupplyDate(invoice.getSupplyDate());
        invoice.setPreviousHash(getPreviousHash(invoice));
    }

    private String getPreviousHash(CreditInvoiceB2C invoice) {
        List<CreditInvoiceB2C> invoiceList = creditInvoiceB2CRepo.findByCompanyID(invoice.getCompanyID());
        String previousHash = "";
        if(invoiceList.size() > 0)
            previousHash = invoiceList.get(invoiceList.size()-1).getHash();
        else
            previousHash = "";

        return previousHash;
    }

    public String getCreditInvoicesByCompanyID(String companyID) {
        try{
            List<CreditInvoiceB2C> invoiceList = creditInvoiceB2CRepo.getCreditInvoicesB2CByCompanyID(companyID);
            List<InvoiceDTO> results = invoiceList.stream().map(CreditInvoiceB2CMapper::mapListToInvoiceDTO).collect(Collectors.toList());
            return gson.toJson(results);
        }catch (Exception e){
            System.out.println("Error in getCreditInvoicesByCompanyID : "+e.getMessage());
            return gson.toJson("Error in getCreditInvoicesByCompanyID : "+e.getMessage());
        }
    }

    public String getCreditInvoicesB2CById(String id) {
        try{
            CreditInvoiceB2C invoice = creditInvoiceB2CRepo.getCreditInvoicesB2CById(id);
            InvoiceDTO invoiceDTO = CreditInvoiceB2CMapper.mapToInvoiceDTO(invoice);
            return gson.toJson(invoiceDTO);
        }catch (Exception e){
            System.out.println("Error in getCreditInvoicesB2CById : "+e.getMessage());
            return gson.toJson("Error in getCreditInvoicesB2CById : "+e.getMessage());
        }
    }

    public String getCreditInvoicesB2CByLocCompanyID(String companyID, String location) {
        try{
            List<CreditInvoiceB2C> invoiceList = creditInvoiceB2CRepo.getCreditInvoicesB2CByLocCompanyID(companyID, location);
            List<InvoiceDTO> results = invoiceList.stream().map(CreditInvoiceB2CMapper::mapToInvoiceDTO).collect(Collectors.toList());
            return gson.toJson(results);
        }catch (Exception e){
            System.out.println("Error in getInvoicesB2C By CompanyID : "+e.getMessage());
            return gson.toJson("Error in getInvoicesB2C By CompanyID : "+e.getMessage());
        }
    }

    public String deleteCreditInvoiceB2CById(long id) {
        ErrorCustom error = new ErrorCustom();
        String jsonError;
        try{
            creditInvoiceB2CRepo.deleteById(id);
            error.setErrorStatus("success");
            error.setError("Credit Invoice B2C deleted successfully!!!");
            jsonError = gson.toJson(error);
            return jsonError;
        }catch (Exception e){
            System.out.println("Error in delete CreditInvoice B2C ById : "+e.getMessage());
            return gson.toJson("Error in delete CreditInvoice B2C ById : "+e.getMessage());
        }
    }

    public String getNextCreditInvoiceB2CNoByUserID(String id) {
        User user = userRepo.getById(id);
        String location =  user.getLocation();
        Company company = companyRepo.getCompanyByCompanyID(user.getCompanyID());
        return "Cr-"+"B2C"+company.getCompanyName().substring(0,2)+"-"+location.substring(0,2)+"-"+getAvaiableId(user.getCompanyID());
    }

    public String getNextCompanyCreditInvoiceB2CNo(String comapnyID) {
        Company company = companyRepo.getCompanyByCompanyID(comapnyID);
        return "Cr-"+"B2C"+company.getCompanyName().substring(0,2)+"-"+getAvaiableId(company.getCompanyID());
    }

    public String getAvaiableId(String companyID) {
        List<CreditInvoiceB2C> invoiceList = creditInvoiceB2CRepo.getCreditInvoicesB2CByCompanyID(companyID);
        if(invoiceList != null && !invoiceList.isEmpty())
            return String.valueOf(invoiceList.size()+1);
        else
            return String.valueOf(1);
    }
}
