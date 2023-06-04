package com.example.einvoicing.controllers;

import com.example.einvoicing.entities.BankAccount;
import com.example.einvoicing.entities.Contract;
import com.example.einvoicing.helpers.BankAccountHelper;
import com.example.einvoicing.helpers.ContractHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class ContractController {
    @Autowired
    ContractHelper contractHelper;

    @PostMapping("/saveContract")
    public String saveContract(@RequestBody Contract contract) {return contractHelper.saveContract(contract) ;}
}

