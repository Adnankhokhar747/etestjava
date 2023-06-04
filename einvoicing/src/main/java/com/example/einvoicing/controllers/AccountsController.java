package com.example.einvoicing.controllers;

import com.example.einvoicing.entities.Account;
import com.example.einvoicing.entities.Company;
import com.example.einvoicing.helpers.AccountHelper;
import com.example.einvoicing.helpers.CompanyHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class AccountsController {
    @Autowired
    AccountHelper accountHelper;

    @PostMapping("/saveAccount")
    public String saveAccount(@RequestBody Account account) {
        return accountHelper.saveAccount(account) ;
    }
}
