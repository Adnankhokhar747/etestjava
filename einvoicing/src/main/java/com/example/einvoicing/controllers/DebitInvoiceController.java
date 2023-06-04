package com.example.einvoicing.controllers;

import com.example.einvoicing.entities.DebitInvoice;
import com.example.einvoicing.helpers.DebitInvoiceHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class DebitInvoiceController {
    @Autowired
    DebitInvoiceHelper debitInvoiceHelper;
    @PostMapping("/saveDebitInvoice")
    public String saveDebitInvoice(@RequestBody DebitInvoice debitInvoice) {return debitInvoiceHelper.saveDeditInvoice(debitInvoice) ;}

    @PostMapping("/updateDebitInvoice")
    public String updateDebitInvoice(@RequestBody DebitInvoice debitInvoice) {return debitInvoiceHelper.saveDeditInvoice(debitInvoice) ;}

    @GetMapping("/getDeditInvoicesByCompanyID")
    public String getDeditInvoicesByCompanyID(@RequestParam String companyID) {return debitInvoiceHelper.getDeditInvoicesByCompanyID(companyID) ;}

    @GetMapping("/getDebitInvoicesByLocCompanyID")
    public String getDebitInvoicesByLocCompanyID(@RequestParam String companyID, String location) {return debitInvoiceHelper.getDebitInvoicesByLocCompanyID(companyID, location) ;}

    @GetMapping("/getDebitInvoicesById")
    public String getDebitInvoicesById(@RequestParam String id) {return debitInvoiceHelper.getDebitInvoicesById(id) ;}

    @PostMapping("/deleteDebitInvoiceById")
    public String deleteDebitInvoiceById(@RequestParam long id) {return debitInvoiceHelper.deleteDebitInvoiceById(id);}

    @GetMapping("/getNextDebitInvoiceNoByUserID")
    public String getNextDebitInvoiceNoByUserID(@RequestParam String id) {return debitInvoiceHelper.getNextDebitInvoiceNoByUserID(id) ;}

    @GetMapping("/getNextCompanyDebitInvoiceNo")
    public String getNextCompanyDebitInvoiceNo(@RequestParam String companyID) {return debitInvoiceHelper.getNextCompanyDebitInvoiceNo(companyID) ;}

}
