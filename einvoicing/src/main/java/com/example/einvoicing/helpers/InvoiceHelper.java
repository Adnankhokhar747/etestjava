package com.example.einvoicing.helpers;

import com.example.einvoicing.dto.InvoiceDTO;
import com.example.einvoicing.entities.*;
import com.example.einvoicing.mapper.InvoiceMapper;
import com.example.einvoicing.repositories.CompanyRepo;
import com.example.einvoicing.repositories.InvoiceRepo;
import com.example.einvoicing.repositories.LineItemRepo;
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
public class InvoiceHelper {
    @Autowired
    private InvoiceRepo invoiceRepo;
    @Autowired
    private LineItemRepo lineItemRepo;

    private Gson gson = new Gson();
    @Autowired
    UserRepo userRepo;
    @Autowired
    CompanyRepo companyRepo;
    @Autowired
    ReportsHelper reportsHelper;
//    private Logger logger = (Logger) LoggerFactory.getLogger(CompanyHelper.class);

    public String saveInvoice(Invoice invoice) {
        ErrorCustom error = new ErrorCustom();
        String jsonError;
        try {
            setInvoice(invoice);
            invoiceRepo.save(invoice);
            List<LineItem> lineItems = invoice.getLineItemList();
            for (LineItem lineItem : lineItems) {
                lineItem.setInvoiceId(invoice);
            }
            lineItemRepo.saveAll(lineItems);
            if(invoice.getStatus().equalsIgnoreCase("Approved"))
                reportsHelper.saveReport(invoice);
            error.setErrorStatus("success");
            error.setError("Invoice saved successfully");
            jsonError = gson.toJson(error);
            return jsonError;
        } catch (Exception e) {
            System.out.println("Error in saving Invoice : " + e.getLocalizedMessage());
            return gson.toJson("Error in saving Invoice : " + e.getLocalizedMessage());
        }
    }

    private void setInvoice(Invoice invoice) throws NoSuchAlgorithmException {
        invoice.setSerialNo(getAvaiableId(invoice.getCompanyID()));
//        UUID uuid = UUID.fromString("00809e66-36d5-436f-93c4-e4e2c76cce0d");
        invoice.setUuid(String.valueOf(UUID.randomUUID()));
        invoice.setHash(Utility.encrypt(String.valueOf(invoice.getId())));
        invoice.setSupplyDate(invoice.getSupplyDate());
        invoice.setPreviousHash(getPreviousHash(invoice));
    }

    //    Company having multiple user w.r.t their locations
    public String getNextInvoiceNoByUserID(String id) {
        User user = userRepo.getById(id);
        String location =  user.getLocation();
        Company company = companyRepo.getCompanyByCompanyID(user.getCompanyID());
        return company.getCompanyName().substring(0,2)+"-"+location.substring(0,2)+"-"+getAvaiableId(user.getCompanyID());
    }

    public String getNextCompanyInvoiceNo(String comapnyID) {
        Company company = companyRepo.getCompanyByCompanyID(comapnyID);
        return company.getCompanyName().substring(0,2)+"-"+getAvaiableId(company.getCompanyID());
    }

    public String getAvaiableId(String companyID) {
        List<Invoice> invoiceList = invoiceRepo.findByCompanyID(companyID);
        if(invoiceList != null && !invoiceList.isEmpty())
            return String.valueOf(invoiceList.size()+1);
        else
            return String.valueOf(1);
    }

    private String getPreviousHash(Invoice invoice) {
        List<Invoice> invoiceList = invoiceRepo.findByCompanyID(invoice.getCompanyID());
        String previousHash = "";
        if(invoiceList.size() > 0)
            previousHash = invoiceList.get(invoiceList.size()-1).getHash();
        else
            previousHash = "";

        return previousHash;
    }
    public String getInvoicesByCompanyID(String companyID) {
        try {
            List<Invoice> invoiceList = invoiceRepo.findByCompanyID(companyID);
            List<InvoiceDTO> collect = invoiceList.stream().map(InvoiceMapper::mapListToInvoiceDTO).collect(Collectors.toList());
            return gson.toJson(collect);
        } catch (Exception e) {
            System.out.println("Error in getInvoicesByCompanyID : "+e.getMessage());
            return gson.toJson("Error in getInvoicesByCompanyID : "+e.getMessage());
        }
    }

    public String deleteInvoiceById(long invoice_id) {
        ErrorCustom error = new ErrorCustom();
        String jsonError;
        try {
            Invoice invoice = invoiceRepo.findById(invoice_id).orElse(null);
            if (invoice != null) {
                invoiceRepo.deleteLineItemsByInvoiceId(invoice);
                invoiceRepo.delete(invoice);
                error.setErrorStatus("success");
                error.setError("Invoice deleted successfully!!!");
                jsonError = gson.toJson(error);
                return jsonError;
            } else {
                error.setErrorStatus("error");
                error.setError("Invoice not found");
                jsonError = gson.toJson(error);
                return jsonError;
            }
        } catch (Exception e) {
            System.out.println("Error in deleteInvoiceById: " + e.getMessage());
            return gson.toJson("Error in deleteInvoiceById: " + e.getMessage());
        }
    }

    public String getInvoicesByLocCompanyID(String companyID, String location) {
        try{
            List<Invoice> invoiceList = invoiceRepo.getInvoicesByLocCompanyID(companyID, location);
            List<InvoiceDTO> collect = invoiceList.stream().map(InvoiceMapper::mapListToInvoiceDTO).collect(Collectors.toList());
            return gson.toJson(collect);
        }catch (Exception e){
            System.out.println("Error in getInvoicesByCompanyID : "+e.getMessage());
            return gson.toJson("Error in getInvoicesByCompanyID : "+e.getMessage());
        }
    }

    public String getInvoicesById(String id) {
        try{
            Invoice invoice = invoiceRepo.getInvoicesById(id);
            InvoiceDTO invoiceDTO = InvoiceMapper.mapToInvoiceDTO(invoice);
            return gson.toJson(invoiceDTO);
        }catch (Exception e){
            System.out.println("Error in getInvoicesById : "+e.getMessage());
            return gson.toJson("Error in getInvoicesById : "+e.getMessage());
        }
    }
}