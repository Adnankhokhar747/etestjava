package com.example.einvoicing.helpers;

import com.example.einvoicing.dto.InvoiceDTO;
import com.example.einvoicing.entities.*;
import com.example.einvoicing.mapper.DebitInvoiceMapper;
import com.example.einvoicing.repositories.CompanyRepo;
import com.example.einvoicing.repositories.DebitInvoiceRepo;
import com.example.einvoicing.repositories.DepitLineitemRepo;
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
public class DebitInvoiceHelper {
    private Gson gson = new Gson();
    @Autowired
    UserRepo userRepo;
    @Autowired
    CompanyRepo companyRepo;

    @Autowired
    DepitLineitemRepo depitLineitemRepo;
    @Autowired
    DebitInvoiceRepo debitInvoiceRepo;

    public String saveDeditInvoice(DebitInvoice debitInvoice) {
        ErrorCustom error = new ErrorCustom();
        String jsonError;
        try {
            setInvoice(debitInvoice);
            debitInvoiceRepo.save(debitInvoice);
            List<DebitLineItem> lineItems = debitInvoice.getLineItemList();
            for (DebitLineItem lineItem : lineItems) {
                lineItem.setInvoiceId(debitInvoice);
            }
            depitLineitemRepo.saveAll(lineItems);
            error.setErrorStatus("success");
            error.setError("Invoice saved successfully");
            jsonError = gson.toJson(error);
            return jsonError;
        }catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return gson.toJson(e.getMessage());
        }
    }

    private void setInvoice(DebitInvoice invoice) throws NoSuchAlgorithmException {
        invoice.setSerialNo(getAvaiableId(invoice.getCompanyID()));
//        UUID uuid = UUID.fromString("00809e66-36d5-436f-93c4-e4e2c76cce0d");
        invoice.setUuid(String.valueOf(UUID.randomUUID()));
        invoice.setHash(Utility.encrypt(String.valueOf(invoice.getId())));
        invoice.setSupplyDate(invoice.getSupplyDate());
        invoice.setPreviousHash(getPreviousHash(invoice));
    }

    private String getPreviousHash(DebitInvoice invoice) {
        List<DebitInvoice> invoiceList = debitInvoiceRepo.findByCompanyID(invoice.getCompanyID());
        String previousHash = "";
        if(invoiceList.size() > 0)
            previousHash = invoiceList.get(invoiceList.size()-1).getHash();
        else
            previousHash = "";

        return previousHash;
    }

    public String getDeditInvoicesByCompanyID(String companyID) {
        try{
            List<DebitInvoice> invoiceList = debitInvoiceRepo.getDebitInvoicesByCompanyID(companyID);
            List<InvoiceDTO> results = invoiceList.stream().map(DebitInvoiceMapper::mapListToInvoiceDTO).collect(Collectors.toList());
            return gson.toJson(results);
        }catch (Exception e){
            System.out.println("Error in getDeditInvoicesByCompanyID : "+e.getMessage());
            return gson.toJson("Error in getDeditInvoicesByCompanyID : "+e.getMessage());
        }
    }

    public String getDebitInvoicesByLocCompanyID(String companyID, String location) {
        try{
            List<DebitInvoice> invoiceList = debitInvoiceRepo.getDebitInvoicesByLocCompanyID(companyID, location);
            List<InvoiceDTO> results = invoiceList.stream().map(DebitInvoiceMapper::mapListToInvoiceDTO).collect(Collectors.toList());
            return gson.toJson(results);
        }catch (Exception e){
            System.out.println("Error in getDebitInvoicesByLocCompanyID : "+e.getMessage());
            return gson.toJson("Error in getDebitInvoicesByLocCompanyID : "+e.getMessage());
        }
    }

    public String getDebitInvoicesById(String id) {
        try{
            DebitInvoice invoice = debitInvoiceRepo.getDebitInvoicesById(id);
            InvoiceDTO invoiceDTO = DebitInvoiceMapper.mapToInvoiceDTO(invoice);
            return gson.toJson(invoiceDTO);
        }catch (Exception e){
            System.out.println("Error in getInvoicesById : "+e.getMessage());
            return gson.toJson("Error in getInvoicesById : "+e.getMessage());
        }
    }

    public String deleteDebitInvoiceById(long id) {
        ErrorCustom error = new ErrorCustom();
        String jsonError;
        try{
            debitInvoiceRepo.deleteById(id);
            error.setErrorStatus("success");
            error.setError("Debit Note deleted successfully!!!");
            jsonError = gson.toJson(error);
            return jsonError;
        }catch (Exception e){
            System.out.println("Error in deleteInvoiceById : "+e.getMessage());
            return gson.toJson("Error in deleteInvoiceById : "+e.getMessage());
        }
    }

    public String getNextDebitInvoiceNoByUserID(String id) {
        User user = userRepo.getById(id);
        String location =  user.getLocation();
        Company company = companyRepo.getCompanyByCompanyID(user.getCompanyID());
        return "De-"+company.getCompanyName().substring(0,2)+"-"+location.substring(0,2)+"-"+getAvaiableId(user.getCompanyID());
    }

    public String getNextCompanyDebitInvoiceNo(String comapnyID) {
        Company company = companyRepo.getCompanyByCompanyID(comapnyID);
        return "De-"+company.getCompanyName().substring(0,2)+"-"+getAvaiableId(company.getCompanyID());
    }

    public String getAvaiableId(String companyID) {
        List<DebitInvoice> invoiceList = debitInvoiceRepo.getDebitInvoicesByCompanyID(companyID);
        if(invoiceList != null && !invoiceList.isEmpty())
            return String.valueOf(invoiceList.size()+1);
        else
            return String.valueOf(1);
    }
}
