package com.example.einvoicing.helpers;

import com.example.einvoicing.entities.Company;
import com.example.einvoicing.entities.Contract;
import com.example.einvoicing.entities.Invoice;
import com.example.einvoicing.entities.User;
import com.example.einvoicing.repositories.CompanyRepo;
import com.example.einvoicing.repositories.ContractRepo;
import com.example.einvoicing.repositories.UserRepo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContractHelper {

    private Gson gson = new Gson();
    @Autowired
    ContractRepo contractRepo;

    public String saveContract(Contract contract) {
        try {
            contractRepo.save(contract);
            return gson.toJson(contract);
        }catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return gson.toJson(e.getMessage());
        }
    }

}
