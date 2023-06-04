package com.example.einvoicing.controllers;

import com.example.einvoicing.helpers.AllInvoicesHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class AllInvoicesController {
    @Autowired
    private AllInvoicesHelper allInvoicesHelper;

    @GetMapping("/getAllInvoicesByCompanyID")
    public String getAllInvoicesByCompanyID(@RequestParam String companyID) {return allInvoicesHelper.getAllInvoicesByCompanyID(companyID) ;}

    @GetMapping("/getAllInvoicesByLocCompanyID")
    public String getAllInvoicesByLocCompanyID(@RequestParam String companyID, String location) {return allInvoicesHelper.getAllInvoicesByLocCompanyID(companyID, location) ;}

    @GetMapping("/getAllInvoicesById")
    public String getAllInvoicesById(@RequestParam String id) {return allInvoicesHelper.getAllInvoicesById(id) ;}

}
