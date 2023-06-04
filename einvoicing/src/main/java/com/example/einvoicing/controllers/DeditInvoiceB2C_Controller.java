package com.example.einvoicing.controllers;

import com.example.einvoicing.entities.CreditInvoiceB2C;
import com.example.einvoicing.entities.DebitInvoiceB2C;
import com.example.einvoicing.helpers.DebitInvoiceB2CHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class DeditInvoiceB2C_Controller {
    @Autowired
    DebitInvoiceB2CHelper debitInvoiceB2CHelper;
    @PostMapping("/saveDebitInvoiceB2C")
    public String saveDebitInvoiceB2C(@RequestBody DebitInvoiceB2C debitInvoiceB2C) {return debitInvoiceB2CHelper.saveDeditInvoiceB2C(debitInvoiceB2C) ;}
    @PostMapping("/updateDebitInvoiceB2C")
    public String updateDebitInvoiceB2C(@RequestBody DebitInvoiceB2C debitInvoiceB2C) {return debitInvoiceB2CHelper.saveDeditInvoiceB2C(debitInvoiceB2C) ;}
    @GetMapping("/getDebitInvoicesB2CByCompanyID")
    public String getCreditInvoicesB2CByCompanyID(@RequestParam String companyID) {return debitInvoiceB2CHelper.getDeditInvoicesByCompanyID(companyID) ;}
    @GetMapping("/getDebitInvoicesB2CByLocCompanyID")
    public String getDebitInvoicesB2CByLocCompanyID(@RequestParam String companyID, String location) {return debitInvoiceB2CHelper.getDebitInvoicesB2CByLocCompanyID(companyID, location) ;}
    @GetMapping("/getDebitInvoicesB2CById")
    public String getDebitInvoicesB2CById(@RequestParam String id) {return debitInvoiceB2CHelper.getDebitInvoicesB2CById(id) ;}
    @PostMapping("/deleteDebitInvoiceB2CById")
    public String deleteDebitInvoiceB2CById(@RequestParam long id) {return debitInvoiceB2CHelper.deleteDebitInvoiceB2CById(id);}
    @GetMapping("/getNextDebitInvoiceB2CNoByUserID")
    public String getNextDebitInvoiceB2CNoByUserID(@RequestParam String id) {return debitInvoiceB2CHelper.getNextDebitInvoiceB2CNoByUserID(id) ;}
    @GetMapping("/getNextCompanyDebitInvoiceB2CNo")
    public String getNextCompanyDebitInvoiceB2CNo(@RequestParam String companyID) {return debitInvoiceB2CHelper.getNextCompanyDebitInvoiceB2CNo(companyID) ;}

}
