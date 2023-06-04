package com.example.einvoicing.controllers;

import com.example.einvoicing.helpers.AllInvoicesB2CHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class AllInvoicesB2C_Controller {
    @Autowired
    private AllInvoicesB2CHelper allInvoicesB2CHelper;

    @GetMapping("/getAllInvoicesB2CByCompanyID")
    public String getAllInvoicesB2CByCompanyID(@RequestParam String companyID) {return allInvoicesB2CHelper.getAllInvoicesB2CByCompanyID(companyID) ;}

    @GetMapping("/getAllInvoicesB2CByLocCompanyID")
    public String getAllInvoicesByLocCompanyID(@RequestParam String companyID, String location) {return allInvoicesB2CHelper.getAllInvoicesB2CByLocCompanyID(companyID, location) ;}

    @GetMapping("/getAllInvoicesB2CById")
    public String getAllInvoicesById(@RequestParam String id) {return allInvoicesB2CHelper.getAllInvoicesById(id) ;}
}
