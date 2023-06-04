package com.example.einvoicing.controllers;

import com.example.einvoicing.entities.CreditInvoice;
import com.example.einvoicing.entities.CreditInvoiceB2C;
import com.example.einvoicing.helpers.CreditInvoiceB2CHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class CreditInvoiceB2C_Controller {
    @Autowired
    CreditInvoiceB2CHelper creditInvoiceB2CHelper;

    @PostMapping("/saveCreditInvoiceB2C")
    public String saveCreditInvoiceB2C(@RequestBody CreditInvoiceB2C creditInvoiceB2C) {return creditInvoiceB2CHelper.saveCreditInvoiceB2C(creditInvoiceB2C) ;}
    @PostMapping("/updateCreditInvoiceB2C")
    public String updateCreditInvoiceB2C(@RequestBody CreditInvoiceB2C creditInvoiceB2C) {return creditInvoiceB2CHelper.saveCreditInvoiceB2C(creditInvoiceB2C) ;}
    @GetMapping("/getCreditInvoicesB2CByCompanyID")
    public String getCreditInvoicesB2CByCompanyID(@RequestParam String companyID) {return creditInvoiceB2CHelper.getCreditInvoicesByCompanyID(companyID) ;}
    @GetMapping("/getCreditInvoicesB2CByLocCompanyID")
    public String getCreditInvoicesB2CByLocCompanyID(@RequestParam String companyID, String location) {return creditInvoiceB2CHelper.getCreditInvoicesB2CByLocCompanyID(companyID, location) ;}
    @GetMapping("/getCreditInvoicesB2CById")
    public String getCreditInvoicesB2CById(@RequestParam String id) {return creditInvoiceB2CHelper.getCreditInvoicesB2CById(id) ;}
    @PostMapping("/deleteCreditInvoiceB2CById")
    public String deleteCreditInvoiceB2CById(@RequestParam long id) {return creditInvoiceB2CHelper.deleteCreditInvoiceB2CById(id);}
    @GetMapping("/getNextCreditInvoiceB2CNoByUserID")
    public String getNextCreditInvoiceB2CNoByUserID(@RequestParam String id) {return creditInvoiceB2CHelper.getNextCreditInvoiceB2CNoByUserID(id) ;}
    @GetMapping("/getNextCompanyCreditInvoiceB2CNo")
    public String getNextCompanyCreditInvoiceB2CNo(@RequestParam String companyID) {return creditInvoiceB2CHelper.getNextCompanyCreditInvoiceB2CNo(companyID) ;}

}
