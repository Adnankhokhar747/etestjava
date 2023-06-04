package com.example.einvoicing.helpers;

import com.example.einvoicing.entities.Account;
import com.example.einvoicing.repositories.AccountsRepo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountHelper {
    private Gson gson = new Gson();
    @Autowired
    AccountsRepo accountsRepo;

    public String saveAccount(Account account) {
        try {
            accountsRepo.save(account);
            return gson.toJson(account);
        }catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return gson.toJson(e.getMessage());
        }
    }

}
