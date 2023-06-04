package com.example.einvoicing.controllers;

import com.example.einvoicing.entities.DebitInvoice;
import com.example.einvoicing.entities.Invoice;
import com.example.einvoicing.entities.InvoiceB2C;
import com.example.einvoicing.helpers.InvoiceB2C_Helper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class InvoiceB2C_Controller {
    @Autowired
    InvoiceB2C_Helper invoiceB2C_helper;

    @PostMapping("/saveInvoiceB2C")
    public String saveInvoiceB2C(@RequestBody InvoiceB2C invoiceB2C) {return invoiceB2C_helper.saveInvoiceB2C(invoiceB2C) ;}
    @PostMapping("/deleteInvoiceB2CById")
    public String deleteInvoiceB2CById(@RequestParam long invoice_id) {return invoiceB2C_helper.deleteInvoiceB2CById(invoice_id);}
    @PostMapping("/updateInvoiceB2C")
    public String updateInvoiceB2C(@RequestBody InvoiceB2C invoice) {return invoiceB2C_helper.saveInvoiceB2C(invoice) ;}
    @GetMapping("/getInvoicesB2CByCompanyID")
    public String getInvoicesB2CByCompanyID(@RequestParam String companyID) {return invoiceB2C_helper.getInvoicesB2CByCompanyID(companyID) ;}
    @GetMapping("/getInvoicesB2CByLocCompanyID")
    public String getInvoicesB2CByLocCompanyID(@RequestParam String companyID, String location) {return invoiceB2C_helper.getInvoicesB2CByLocCompanyID(companyID, location) ;}
    @GetMapping("/getInvoicesB2CById")
    public String getInvoicesB2CById(@RequestParam String id) {return invoiceB2C_helper.getInvoicesB2CById(id) ;}
    @GetMapping("/getNextInvoiceB2CNoByUserID")
    public String getNextInvoiceB2CNoByUserID(@RequestParam String id) {return invoiceB2C_helper.getNextInvoiceB2CNoByUserID(id) ;}
    @GetMapping("/getNextCompanyInvoiceB2CNo")
    public String getNextCompanyInvoiceB2CNo(@RequestParam String companyID) {return invoiceB2C_helper.getNextCompanyInvoiceB2CNo(companyID) ;}

}
