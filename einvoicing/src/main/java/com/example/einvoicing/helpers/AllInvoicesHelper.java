package com.example.einvoicing.helpers;

import com.example.einvoicing.entities.CreditInvoice;
import com.example.einvoicing.entities.DebitInvoice;
import com.example.einvoicing.entities.Invoice;
import com.example.einvoicing.dto.InvoiceDTO;
import com.example.einvoicing.mapper.CreditInvoiceMapper;
import com.example.einvoicing.mapper.DebitInvoiceMapper;
import com.example.einvoicing.mapper.InvoiceMapper;
import com.example.einvoicing.repositories.CreditInvoiceRepo;
import com.example.einvoicing.repositories.DebitInvoiceRepo;
import com.example.einvoicing.repositories.InvoiceRepo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AllInvoicesHelper {
    private Gson gson = new Gson();
    @Autowired
    CreditInvoiceRepo creditInvoiceRepo;
    @Autowired
    DebitInvoiceRepo debitInvoiceRepo;
    @Autowired
    InvoiceRepo invoiceRepo;

    public String getAllInvoicesByCompanyID(String companyID) {
        List<Object> allInvoices = new ArrayList<>();
        try{
            List<Invoice> invoiceList = invoiceRepo.findByCompanyID(companyID);
            List<CreditInvoice> creditInvoiceList = creditInvoiceRepo.getCreditInvoicesByCompanyID(companyID);
            List<DebitInvoice> debitInvoiceList = debitInvoiceRepo.getDebitInvoicesByCompanyID(companyID);

            List<InvoiceDTO> invoiceDTOList = invoiceList.stream().map(InvoiceMapper::mapListToInvoiceDTO).collect(Collectors.toList());
            List<InvoiceDTO> creditInvoiceDTOList = creditInvoiceList.stream().map(CreditInvoiceMapper::mapToInvoiceDTO).collect(Collectors.toList());
            List<InvoiceDTO> debitInvoiceDTOList = debitInvoiceList.stream().map(DebitInvoiceMapper::mapListToInvoiceDTO).collect(Collectors.toList());

            allInvoices.addAll(invoiceDTOList);
            allInvoices.addAll(creditInvoiceDTOList);
            allInvoices.addAll(debitInvoiceDTOList);
            return gson.toJson(allInvoices);
        }catch (Exception e){
            System.out.println("Error in getAllInvoicesByCompanyID : "+e.getMessage());
            return gson.toJson("Error in getAllInvoicesByCompanyID : "+e.getMessage());
        }
    }

    public String getAllInvoicesByLocCompanyID(String companyID, String location) {
        List<Object> allInvoices = new ArrayList<>();
        try{
            List<Invoice> invoiceList = invoiceRepo.getInvoicesByLocCompanyID(companyID, location);
            List<CreditInvoice> creditInvoiceList = creditInvoiceRepo.getCreditInvoicesByLocCompanyID(companyID, location);
            List<DebitInvoice> debitInvoiceList = debitInvoiceRepo.getDebitInvoicesByLocCompanyID(companyID, location);

            List<InvoiceDTO> invoiceDTOList = invoiceList.stream().map(InvoiceMapper::mapListToInvoiceDTO).collect(Collectors.toList());
            List<InvoiceDTO> creditInvoiceDTOList = creditInvoiceList.stream().map(CreditInvoiceMapper::mapToInvoiceDTO).collect(Collectors.toList());
            List<InvoiceDTO> debitInvoiceDTOList = debitInvoiceList.stream().map(DebitInvoiceMapper::mapListToInvoiceDTO).collect(Collectors.toList());

            allInvoices.addAll(invoiceDTOList);
            allInvoices.addAll(creditInvoiceDTOList);
            allInvoices.addAll(debitInvoiceDTOList);
            return gson.toJson(allInvoices);
        }catch (Exception e){
            System.out.println("Error in getAllInvoicesByLocCompanyID : "+e.getMessage());
            return gson.toJson("Error in getAllInvoicesByLocCompanyID : "+e.getMessage());
        }
    }
    public String getAllInvoicesById(String id) {
        try {
            Invoice invoice = invoiceRepo.getInvoicesById(id);
            if (invoice != null) {
                InvoiceDTO invoiceDTO = InvoiceMapper.mapToInvoiceDTO(invoice);
                return gson.toJson(invoiceDTO);
            } else {
                CreditInvoice creditInvoice = creditInvoiceRepo.getCreditInvoicesById(id);
                if (creditInvoice != null) {
                    InvoiceDTO invoiceDTO = CreditInvoiceMapper.mapToInvoiceDTO(creditInvoice);
                    return gson.toJson(invoiceDTO);
                } else {
                    DebitInvoice debitInvoice = debitInvoiceRepo.getDebitInvoicesById(id);
                    if (debitInvoice != null) {
                        InvoiceDTO invoiceDTO = DebitInvoiceMapper.mapToInvoiceDTO(debitInvoice);
                        return gson.toJson(invoiceDTO);
                    } else {
                        return gson.toJson("No Invoice found");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error in getAllInvoicesById: " + e.getMessage());
            return gson.toJson("Error in getAllInvoicesById: " + e.getMessage());
        }
    }

}