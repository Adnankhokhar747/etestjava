package com.example.einvoicing.helpers;

import com.example.einvoicing.dto.InvoiceDTO;
import com.example.einvoicing.entities.*;
import com.example.einvoicing.mapper.DebitInvoiceB2CMapper;
import com.example.einvoicing.repositories.CompanyRepo;
import com.example.einvoicing.repositories.DebitB2CLineitemRepo;
import com.example.einvoicing.repositories.DebitInvoiceB2CRepo;
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
public class DebitInvoiceB2CHelper {
    private Gson gson = new Gson();
    @Autowired
    UserRepo userRepo;
    @Autowired
    CompanyRepo companyRepo;

    @Autowired
    DebitB2CLineitemRepo debitB2CLineitemRepo;
    @Autowired
    DebitInvoiceB2CRepo debitInvoiceB2CRepo;

    public String saveDeditInvoiceB2C(DebitInvoiceB2C debitInvoice) {
        ErrorCustom error = new ErrorCustom();
        String jsonError;
        try {
            setInvoice(debitInvoice);
            debitInvoiceB2CRepo.save(debitInvoice);
            List<DebitB2CLineItem> lineItems = debitInvoice.getLineItemList();
            for (DebitB2CLineItem lineItem : lineItems) {
                lineItem.setInvoiceId(debitInvoice);
            }
            debitB2CLineitemRepo.saveAll(lineItems);
            error.setErrorStatus("success");
            error.setError("Debit note saved successfully");
            jsonError = gson.toJson(error);
            return jsonError;
        }catch (Exception e) {
            System.out.println("Error in saving Debit Invoice : "+e.getLocalizedMessage());
            return gson.toJson("Error in saving Debit Invoice : "+e.getLocalizedMessage());
        }
    }

    private void setInvoice(DebitInvoiceB2C invoice) throws NoSuchAlgorithmException {
        invoice.setSerialNo(getAvaiableId(invoice.getCompanyID()));
//        UUID uuid = UUID.fromString("00809e66-36d5-436f-93c4-e4e2c76cce0d");
        invoice.setUuid(String.valueOf(UUID.randomUUID()));
        invoice.setHash(Utility.encrypt(String.valueOf(invoice.getId())));
        invoice.setSupplyDate(invoice.getSupplyDate());
        invoice.setPreviousHash(getPreviousHash(invoice));
    }

    private String getPreviousHash(DebitInvoiceB2C invoice) {
        List<DebitInvoiceB2C> invoiceList = debitInvoiceB2CRepo.findByCompanyID(invoice.getCompanyID());
        String previousHash = "";
        if(invoiceList.size() > 0)
            previousHash = invoiceList.get(invoiceList.size()-1).getHash();
        else
            previousHash = "";

        return previousHash;
    }

    public String getDeditInvoicesByCompanyID(String companyID) {
        try{
            List<DebitInvoiceB2C> invoiceList = debitInvoiceB2CRepo.findByCompanyID(companyID);
            List<InvoiceDTO> results = invoiceList.stream().map(DebitInvoiceB2CMapper::mapToInvoiceDTO).collect(Collectors.toList());
            return gson.toJson(results);
        }catch (Exception e){
            System.out.println("Error in getDeditInvoicesB2CByCompanyID : "+e.getMessage());
            return gson.toJson("Error in getDeditInvoicesB2CByCompanyID : "+e.getMessage());
        }
    }

    public String getDebitInvoicesB2CByLocCompanyID(String companyID, String location) {
        try{
            List<DebitInvoiceB2C> invoiceList = debitInvoiceB2CRepo.getDebitInvoicesB2CByLocCompanyID(companyID, location);
            List<InvoiceDTO> results = invoiceList.stream().map(DebitInvoiceB2CMapper::mapToInvoiceDTO).collect(Collectors.toList());
            return gson.toJson(results);
        }catch (Exception e){
            System.out.println("Error in getInvoicesB2C By CompanyID : "+e.getMessage());
            return gson.toJson("Error in getInvoicesB2C By CompanyID : "+e.getMessage());
        }
    }

    public String deleteDebitInvoiceB2CById(long id) {
        ErrorCustom error = new ErrorCustom();
        String jsonError;
        try{
            debitInvoiceB2CRepo.deleteById(id);
            error.setErrorStatus("success");
            error.setError("Debit Invoice B2C deleted successfully!!!");
            jsonError = gson.toJson(error);
            return jsonError;
        }catch (Exception e){
            System.out.println("Error in delete Debit Invoice B2C ById : "+e.getMessage());
            return gson.toJson("Error in delete Debit Invoice B2C ById : "+e.getMessage());
        }
    }

    public String getDebitInvoicesB2CById(String id) {
        try{
            DebitInvoiceB2C invoice = debitInvoiceB2CRepo.getDebitInvoicesB2CById(id);
            InvoiceDTO invoiceDTO = DebitInvoiceB2CMapper.mapToInvoiceDTO(invoice);
            return gson.toJson(invoiceDTO);
        }catch (Exception e){
            System.out.println("Error in getDebitInvoicesB2CById : "+e.getMessage());
            return gson.toJson("Error in getDebitInvoicesB2CById : "+e.getMessage());
        }
    }

    public String getNextDebitInvoiceB2CNoByUserID(String id) {
        User user = userRepo.getById(id);
        String location =  user.getLocation();
        Company company = companyRepo.getCompanyByCompanyID(user.getCompanyID());
        return "Db-B2C-"+company.getCompanyName().substring(0,2)+"-"+location.substring(0,2)+"-"+getAvaiableId(user.getCompanyID());
    }

    public String getNextCompanyDebitInvoiceB2CNo(String comapnyID) {
        Company company = companyRepo.getCompanyByCompanyID(comapnyID);
        return "Db-B2C-"+company.getCompanyName().substring(0,2)+"-"+getAvaiableId(company.getCompanyID());
    }

    public String getAvaiableId(String companyID) {
        List<DebitInvoiceB2C> invoiceList = debitInvoiceB2CRepo.findByCompanyID(companyID);
        if(invoiceList != null && !invoiceList.isEmpty())
            return String.valueOf(invoiceList.size()+1);
        else
            return String.valueOf(1);
    }

}
