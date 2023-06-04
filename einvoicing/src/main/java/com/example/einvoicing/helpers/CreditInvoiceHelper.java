package com.example.einvoicing.helpers;

import com.example.einvoicing.dto.InvoiceDTO;
import com.example.einvoicing.entities.*;
import com.example.einvoicing.mapper.CreditInvoiceMapper;
import com.example.einvoicing.repositories.*;
import com.example.einvoicing.util.Utility;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CreditInvoiceHelper {

    private Gson gson = new Gson();
    @Autowired
    UserRepo userRepo;
    @Autowired
    CompanyRepo companyRepo;
    @Autowired
    private CreditLineItemRepo creditLineItemRepo;
    @Autowired
    CreditInvoiceRepo creditInvoiceRepo;

    public String saveCreditInvoice(CreditInvoice creditInvoice) {
        ErrorCustom error = new ErrorCustom();
        String jsonError;
        try {
            setInvoice(creditInvoice);
            creditInvoiceRepo.save(creditInvoice);
            List<CreditLineItem> lineItems = creditInvoice.getLineItemList();
            for (CreditLineItem lineItem : lineItems) {
                lineItem.setInvoiceId(creditInvoice);
            }
            creditLineItemRepo.saveAll(lineItems);
            error.setErrorStatus("success");
            error.setError("Invoice saved successfully");
            jsonError = gson.toJson(error);
            return jsonError;
        }catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return gson.toJson(e.getMessage());
        }
    }

    public String getCreditInvoicesByCompanyID(String companyID) {
        try{
            List<CreditInvoice> invoiceList = creditInvoiceRepo.getCreditInvoicesByCompanyID(companyID);
            List<InvoiceDTO> results = invoiceList.stream().map(CreditInvoiceMapper::mapToInvoiceDTO).collect(Collectors.toList());
            return gson.toJson(results);
        }catch (Exception e){
            System.out.println("Error in getCreditInvoicesByCompanyID : "+e.getMessage());
            return gson.toJson("Error in getCreditInvoicesByCompanyID : "+e.getMessage());
        }
    }

    public String getCreditInvoicesByLocCompanyID(String companyID, String location) {
        try{
            List<CreditInvoice> invoiceList = creditInvoiceRepo.getCreditInvoicesByLocCompanyID(companyID, location);
            List<InvoiceDTO> results = invoiceList.stream().map(CreditInvoiceMapper::mapToInvoiceDTO).collect(Collectors.toList());
            return gson.toJson(results);
        }catch (Exception e){
            System.out.println("Error in getInvoicesByCompanyID : "+e.getMessage());
            return gson.toJson("Error in getInvoicesByCompanyID : "+e.getMessage());
        }
    }

    public String deleteCreditInvoiceById(long id) {
        ErrorCustom error = new ErrorCustom();
        String jsonError;
        try{
            creditInvoiceRepo.deleteById(id);
            error.setErrorStatus("success");
            error.setError("Credit Note deleted successfully!!!");
            jsonError = gson.toJson(error);
            return jsonError;
        }catch (Exception e){
            System.out.println("Error in deleteCreditInvoiceById : "+e.getMessage());
            return gson.toJson("Error in deleteCreditInvoiceById : "+e.getMessage());
        }
    }

    private String getPreviousHash(CreditInvoice invoice) {
        List<CreditInvoice> invoiceList = creditInvoiceRepo.findByCompanyID(invoice.getCompanyID());
        String previousHash = "";
        if(invoiceList.size() > 0)
            previousHash = invoiceList.get(invoiceList.size()-1).getHash();
        else
            previousHash = "";

        return previousHash;
    }
    private void setInvoice(CreditInvoice invoice) throws NoSuchAlgorithmException {
        invoice.setSerialNo(getAvaiableId(invoice.getCompanyID()));
//        UUID uuid = UUID.fromString("00809e66-36d5-436f-93c4-e4e2c76cce0d");
        invoice.setUuid(String.valueOf(UUID.randomUUID()));
        invoice.setHash(Utility.encrypt(String.valueOf(invoice.getId())));
        invoice.setSupplyDate(invoice.getSupplyDate());
        invoice.setPreviousHash(getPreviousHash(invoice));
    }
    public String getCreditInvoicesById(String id) {
        try{
            CreditInvoice invoice = creditInvoiceRepo.getCreditInvoicesById(id);
            InvoiceDTO invoiceDTO = CreditInvoiceMapper.mapToInvoiceDTO(invoice);
            return gson.toJson(invoiceDTO);
        }catch (Exception e){
            System.out.println("Error in getCreditInvoicesById : "+e.getMessage());
            return gson.toJson("Error in getCreditInvoicesById : "+e.getMessage());
        }
    }

    public String getNextCreditInvoiceNoByUserID(String id) {
        User user = userRepo.getById(id);
        String location =  user.getLocation();
        Company company = companyRepo.getCompanyByCompanyID(user.getCompanyID());
        return "Cr-"+company.getCompanyName().substring(0,2)+"-"+location.substring(0,2)+"-"+getAvaiableId(user.getCompanyID());
    }

    public String getNextCompanyCreditInvoiceNo(String comapnyID) {
        Company company = companyRepo.getCompanyByCompanyID(comapnyID);
        return "Cr-"+company.getCompanyName().substring(0,2)+"-"+getAvaiableId(company.getCompanyID());
    }

    public String getAvaiableId(String companyID) {
        List<CreditInvoice> invoiceList = creditInvoiceRepo.getCreditInvoicesByCompanyID(companyID);
        if(invoiceList != null && !invoiceList.isEmpty())
            return String.valueOf(invoiceList.size()+1);
        else
            return String.valueOf(1);
    }

}
