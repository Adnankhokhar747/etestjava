package com.example.einvoicing.helpers;

import com.example.einvoicing.dto.InvoiceDTO;
import com.example.einvoicing.entities.*;
import com.example.einvoicing.mapper.*;
import com.example.einvoicing.repositories.CreditInvoiceB2CRepo;
import com.example.einvoicing.repositories.DebitInvoiceB2CRepo;
import com.example.einvoicing.repositories.InvoiceB2CRepo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AllInvoicesB2CHelper {
    private Gson gson = new Gson();
    @Autowired
    CreditInvoiceB2CRepo creditInvoiceB2CRepo;
    @Autowired
    DebitInvoiceB2CRepo debitInvoiceB2CRepo;
    @Autowired
    InvoiceB2CRepo invoiceB2CRepo;

    public String getAllInvoicesB2CByCompanyID(String companyID) {
        List<Object> allInvoices = new ArrayList<>();
        try{
            List<InvoiceB2C> invoiceList = invoiceB2CRepo.findByCompanyID(companyID);
            List<CreditInvoiceB2C> creditInvoiceList = creditInvoiceB2CRepo.findByCompanyID(companyID);
            List<DebitInvoiceB2C> debitInvoiceList = debitInvoiceB2CRepo.findByCompanyID(companyID);

            List<InvoiceDTO> invoiceDTOList = invoiceList.stream().map(InvoiceB2CMapper::mapListToInvoiceDTO).collect(Collectors.toList());
            List<InvoiceDTO> creditInvoiceDTOList = creditInvoiceList.stream().map(CreditInvoiceB2CMapper::mapToInvoiceDTO).collect(Collectors.toList());
            List<InvoiceDTO> debitInvoiceDTOList = debitInvoiceList.stream().map(DebitInvoiceB2CMapper::mapListToInvoiceDTO).collect(Collectors.toList());

            allInvoices.addAll(invoiceDTOList);
            allInvoices.addAll(creditInvoiceDTOList);
            allInvoices.addAll(debitInvoiceDTOList);
            return gson.toJson(allInvoices);
        }catch (Exception e){
            System.out.println("Error in getAllInvoicesB2CByCompanyID : "+e.getMessage());
            return gson.toJson("Error in getAllInvoicesB2CByCompanyID : "+e.getMessage());
        }
    }

    public String getAllInvoicesB2CByLocCompanyID(String companyID, String location) {
        List<Object> allInvoices = new ArrayList<>();
        try{
            List<InvoiceB2C> invoiceList = invoiceB2CRepo.getInvoicesB2CByLocCompanyID(companyID, location);
            List<CreditInvoiceB2C> creditInvoiceList = creditInvoiceB2CRepo.getCreditInvoicesB2CByLocCompanyID(companyID, location);
            List<DebitInvoiceB2C> debitInvoiceList = debitInvoiceB2CRepo.getDebitInvoicesB2CByLocCompanyID(companyID, location);

            List<InvoiceDTO> invoiceDTOList = invoiceList.stream().map(InvoiceB2CMapper::mapListToInvoiceDTO).collect(Collectors.toList());
            List<InvoiceDTO> creditInvoiceDTOList = creditInvoiceList.stream().map(CreditInvoiceB2CMapper::mapToInvoiceDTO).collect(Collectors.toList());
            List<InvoiceDTO> debitInvoiceDTOList = debitInvoiceList.stream().map(DebitInvoiceB2CMapper::mapListToInvoiceDTO).collect(Collectors.toList());

            allInvoices.addAll(invoiceDTOList);
            allInvoices.addAll(creditInvoiceDTOList);
            allInvoices.addAll(debitInvoiceDTOList);
            return gson.toJson(allInvoices);
        }catch (Exception e){
            System.out.println("Error in getAllInvoicesB2CByLocCompanyID : "+e.getMessage());
            return gson.toJson("Error in getAllInvoicesB2CByLocCompanyID : "+e.getMessage());
        }
    }
    public String getAllInvoicesById(String id) {
        try {
            InvoiceB2C invoice = invoiceB2CRepo.getInvoicesB2CById(id);
            if (invoice != null) {
                InvoiceDTO invoiceDTO = InvoiceB2CMapper.mapToInvoiceDTO(invoice);
                return gson.toJson(invoiceDTO);
            } else {
                CreditInvoiceB2C creditInvoice = creditInvoiceB2CRepo.getCreditInvoicesB2CById(id);
                if (creditInvoice != null) {
                    InvoiceDTO invoiceDTO = CreditInvoiceB2CMapper.mapToInvoiceDTO(creditInvoice);
                    return gson.toJson(invoiceDTO);
                } else {
                    DebitInvoiceB2C debitInvoice = debitInvoiceB2CRepo.getDebitInvoicesB2CById(id);
                    if (debitInvoice != null) {
                        InvoiceDTO invoiceDTO = DebitInvoiceB2CMapper.mapToInvoiceDTO(debitInvoice);
                        return gson.toJson(invoiceDTO);
                    } else {
                        return gson.toJson("No Invoice found");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error in getAllInvoicesB2CById: " + e.getMessage());
            return gson.toJson("Error in getAllInvoicesB2CById: " + e.getMessage());
        }
    }

}
