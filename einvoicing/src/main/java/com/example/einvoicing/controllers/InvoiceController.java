package com.example.einvoicing.controllers;

import com.example.einvoicing.entities.BankAccount;
import com.example.einvoicing.entities.Invoice;
import com.example.einvoicing.helpers.InvoiceHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class InvoiceController {
    @Autowired
    InvoiceHelper invoiceHelper;

    @PostMapping("/saveInvoice")
    public String saveInvoice(@RequestBody Invoice invoice) {
        return invoiceHelper.saveInvoice(invoice) ;
    }

    @PostMapping("/deleteInvoiceById")
    public String deleteInvoiceById(@RequestParam long id) {return invoiceHelper.deleteInvoiceById(id);}

    @PostMapping("/updateInvoice")
    public String updateInvoice(@RequestBody Invoice invoice) {return invoiceHelper.saveInvoice(invoice) ;}

    @GetMapping("/getInvoicesByCompanyID")
    public String getInvoicesByCompanyID(@RequestParam String companyID) {return invoiceHelper.getInvoicesByCompanyID(companyID) ;}

    @GetMapping("/getInvoicesByLocCompanyID")
    public String getInvoicesByLocCompanyID(@RequestParam String companyID, String location) {return invoiceHelper.getInvoicesByLocCompanyID(companyID, location) ;}

    @GetMapping("/getInvoicesById")
    public String getInvoicesById(@RequestParam String id) {return invoiceHelper.getInvoicesById(id) ;}

    @GetMapping("/getNextInvoiceNoByUserID")
    public String getNextInvoiceNoByUserID(@RequestParam String id) {return invoiceHelper.getNextInvoiceNoByUserID(id) ;}

    @GetMapping("/getNextCompanyInvoiceNo")
    public String getNextCompanyInvoiceNo(@RequestParam String companyID) {return invoiceHelper.getNextCompanyInvoiceNo(companyID) ;}

}
