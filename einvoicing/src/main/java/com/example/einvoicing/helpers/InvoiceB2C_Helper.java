package com.example.einvoicing.helpers;

import com.example.einvoicing.dto.InvoiceDTO;
import com.example.einvoicing.entities.*;
import com.example.einvoicing.mapper.InvoiceB2CMapper;
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
public class InvoiceB2C_Helper {
    private Gson gson = new Gson();
    @Autowired
    UserRepo userRepo;
    @Autowired
    CompanyRepo companyRepo;

    @Autowired
    InvoiceLineitemB2CRepo invoiceLineitemB2CRepo;
    @Autowired
    InvoiceB2CRepo invoiceB2CRepo;

    public String saveInvoiceB2C(InvoiceB2C invoiceB2C) {
        ErrorCustom error = new ErrorCustom();
        String jsonError;
        try {
            setInvoice(invoiceB2C);
            invoiceB2CRepo.save(invoiceB2C);
            List<LineItemB2C> lineItems = invoiceB2C.getLineItemList();
            for (LineItemB2C lineItem : lineItems) {
                lineItem.setInvoiceId(invoiceB2C);
            }
            invoiceLineitemB2CRepo.saveAll(lineItems);
            error.setErrorStatus("success");
            error.setError("Credit note saved successfully");
            jsonError = gson.toJson(error);
            return jsonError;
        }catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return gson.toJson(e.getMessage());
        }
    }

    private void setInvoice(InvoiceB2C invoice) throws NoSuchAlgorithmException {
        invoice.setSerialNo(getAvaiableId(invoice.getCompanyID()));
//        UUID uuid = UUID.fromString("00809e66-36d5-436f-93c4-e4e2c76cce0d");
        invoice.setUuid(String.valueOf(UUID.randomUUID()));
        invoice.setHash(Utility.encrypt(String.valueOf(invoice.getId())));
        invoice.setSupplyDate(invoice.getSupplyDate());
        invoice.setPreviousHash(getPreviousHash(invoice));
    }

    private String getPreviousHash(InvoiceB2C invoice) {
        List<InvoiceB2C> invoiceList = invoiceB2CRepo.findByCompanyID(invoice.getCompanyID());
        String previousHash = "";
        if(invoiceList.size() > 0)
            previousHash = invoiceList.get(invoiceList.size()-1).getHash();
        else
            previousHash = "";

        return previousHash;
    }

    public String getInvoicesB2CByCompanyID(String companyID) {
        try{
            List<InvoiceB2C> invoiceList = invoiceB2CRepo.findByCompanyID(companyID);
            List<InvoiceDTO> collect = invoiceList.stream().map(InvoiceB2CMapper::mapListToInvoiceDTO).collect(Collectors.toList());
            return gson.toJson(collect);
        }catch (Exception e){
            System.out.println("Error in getInvoicesB2CByCompanyID : "+e.getMessage());
            return gson.toJson("Error in getInvoicesB2CByCompanyID : "+e.getMessage());
        }
    }
    public String getInvoicesB2CByLocCompanyID(String companyID, String location) {
        try{
            List<InvoiceB2C> invoiceList = invoiceB2CRepo.getInvoicesB2CByLocCompanyID(companyID, location);
            List<InvoiceDTO> collect = invoiceList.stream().map(InvoiceB2CMapper::mapListToInvoiceDTO).collect(Collectors.toList());
            return gson.toJson(collect);
        }catch (Exception e){
            System.out.println("Error in getInvoicesByCompanyID : "+e.getMessage());
            return gson.toJson("Error in getInvoicesByCompanyID : "+e.getMessage());
        }
    }

    public String getInvoicesB2CById(String id) {
        try{
            InvoiceB2C invoice = invoiceB2CRepo.getInvoicesB2CById(id);
            InvoiceDTO invoiceDTO = InvoiceB2CMapper.mapToInvoiceDTO(invoice);
            return gson.toJson(invoiceDTO);
        }catch (Exception e){
            System.out.println("Error in getInvoicesB2CById : "+e.getMessage());
            return gson.toJson("Error in getInvoicesB2CById : "+e.getMessage());
        }
    }

    public String deleteInvoiceB2CById(long invoice_id) {
        ErrorCustom error = new ErrorCustom();
        String jsonError;
        try {
            InvoiceB2C invoice = invoiceB2CRepo.findById(invoice_id).orElse(null);
            if (invoice != null) {
                invoiceB2CRepo.deleteLineItemsByInvoiceId(invoice);
                invoiceB2CRepo.delete(invoice);
                error.setErrorStatus("success");
                error.setError("Invoice B2C deleted successfully!!!");
                jsonError = gson.toJson(error);
                return jsonError;
            } else {
                error.setErrorStatus("error");
                error.setError("InvoiceB2C not found");
                jsonError = gson.toJson(error);
                return jsonError;
            }
        } catch (Exception e) {
            System.out.println("Error in deleteInvoiceB2cById: " + e.getMessage());
            return gson.toJson("Error in deleteInvoiceB2cById: " + e.getMessage());
        }
    }

    public String getNextInvoiceB2CNoByUserID(String id) {
        User user = userRepo.getById(id);
        String location =  user.getLocation();
        Company company = companyRepo.getCompanyByCompanyID(user.getCompanyID());
        return company.getCompanyName().substring(0,2)+"-"+location.substring(0,2)+"-"+getAvaiableId(user.getCompanyID());
    }

    public String getNextCompanyInvoiceB2CNo(String comapnyID) {
        Company company = companyRepo.getCompanyByCompanyID(comapnyID);
        return company.getCompanyName().substring(0,2)+"-"+getAvaiableId(company.getCompanyID());
    }

    public String getAvaiableId(String companyID) {
        List<InvoiceB2C> invoiceList = invoiceB2CRepo.findByCompanyID(companyID);
        if(invoiceList != null && !invoiceList.isEmpty())
            return String.valueOf(invoiceList.size()+1);
        else
            return String.valueOf(1);
    }

}
