package com.example.einvoicing.controllers;

import com.example.einvoicing.entities.Account;
import com.example.einvoicing.entities.BankAccount;
import com.example.einvoicing.helpers.AccountHelper;
import com.example.einvoicing.helpers.BankAccountHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class BankAccountController {
    @Autowired
    BankAccountHelper bankAccountHelper;

    @PostMapping("/saveBankAccount")
    public String saveBankAccount(@RequestBody BankAccount bankAccount) {return bankAccountHelper.saveBankAccount(bankAccount) ;}

    @PostMapping("/updateBankAccount")
    public String updateBankAccount(@RequestBody BankAccount bankAccount) {return bankAccountHelper.saveBankAccount(bankAccount) ;}

    @PostMapping("/deleteBankAccountById")
    public String deleteBankAccountById(@RequestParam String id) {return bankAccountHelper.deleteBankAccountById(id);}

    @GetMapping("/getBankAccountByCompanyID")
    public String getBankAccountByCompanyID(@RequestParam String companyID) {return bankAccountHelper.getBankAccountByCompanyID(companyID) ;}

    @GetMapping("/getBankAccountById")
    public String getBankAccountById(@RequestParam String id) {return bankAccountHelper.getBankAccountById(id) ;}
}
