package com.example.einvoicing.controllers;

import com.example.einvoicing.entities.CreditInvoice;
import com.example.einvoicing.helpers.CreditInvoiceHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class CreditInvoiceController {
    @Autowired
    CreditInvoiceHelper creditInvoiceHelper;

    @PostMapping("/saveCreditInvoice")
    public String saveCreditInvoice(@RequestBody CreditInvoice creditInvoice) {return creditInvoiceHelper.saveCreditInvoice(creditInvoice) ;}

    @PostMapping("/updateCreditInvoice")
    public String updateCreditInvoice(@RequestBody CreditInvoice creditInvoice) {return creditInvoiceHelper.saveCreditInvoice(creditInvoice) ;}

    @GetMapping("/getCreditInvoicesByCompanyID")
    public String getCreditInvoicesByCompanyID(@RequestParam String companyID) {return creditInvoiceHelper.getCreditInvoicesByCompanyID(companyID) ;}

    @GetMapping("/getCreditInvoicesByLocCompanyID")
    public String getCreditInvoicesByLocCompanyID(@RequestParam String companyID, String location) {return creditInvoiceHelper.getCreditInvoicesByLocCompanyID(companyID, location) ;}

    @GetMapping("/getCreditInvoicesById")
    public String getCreditInvoicesById(@RequestParam String id) {return creditInvoiceHelper.getCreditInvoicesById(id) ;}

    @PostMapping("/deleteCreditInvoiceById")
    public String deleteCreditInvoiceById(@RequestParam long id) {return creditInvoiceHelper.deleteCreditInvoiceById(id);}

    @GetMapping("/getNextCreditInvoiceNoByUserID")
    public String getNextCreditInvoiceNoByUserID(@RequestParam String id) {return creditInvoiceHelper.getNextCreditInvoiceNoByUserID(id) ;}

    @GetMapping("/getNextCompanyCreditInvoiceNo")
    public String getNextCompanyCreditInvoiceNo(@RequestParam String companyID) {return creditInvoiceHelper.getNextCompanyCreditInvoiceNo(companyID) ;}

}
