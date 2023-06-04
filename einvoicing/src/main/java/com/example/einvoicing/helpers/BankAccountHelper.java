package com.example.einvoicing.helpers;

import com.example.einvoicing.entities.*;
import com.example.einvoicing.repositories.BankAccountRepo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BankAccountHelper {
    private Gson gson = new Gson();
    @Autowired
    BankAccountRepo bankAccountRepo;

    public String saveBankAccount(BankAccount bankAccount) {
        ErrorCustom error = new ErrorCustom();
        String jsonError;
        try {
            bankAccountRepo.save(bankAccount);
            error.setErrorStatus("success");
            error.setError("Bank Name: " + bankAccount.getBankName() + " has been added successfully");
            jsonError = gson.toJson(error);
            return jsonError;
        }catch (Exception e) {
            error.setErrorStatus("error");
            error.setError(e.getLocalizedMessage());
            jsonError = gson.toJson(error);
            return jsonError;
        }
    }

    public String getBankAccountByCompanyID(String companyID) {
        try{
            List<BankAccount> BankAccountList = bankAccountRepo.getBankAccountsByCompanyID(companyID);
            return gson.toJson(BankAccountList);
        }catch (Exception e){
            System.out.println("Error in getProductsByCompanyID : "+e.getMessage());
            return gson.toJson("Error in getProductsByCompanyID : "+e.getMessage());
        }
    }

    public String deleteBankAccountById(String id) {
        ErrorCustom error = new ErrorCustom();
        String jsonError;
        try{
            bankAccountRepo.deleteById(id);
            error.setErrorStatus("success");
            error.setError("Bank Account deleted successfully!!!");
            jsonError = gson.toJson(error);
            return jsonError;
        }catch (Exception e){
            error.setErrorStatus("error");
            error.setError(e.getMessage());
            jsonError = gson.toJson(error);
            return jsonError;
        }
    }

    public String getBankAccountById(String id) {
        try{
            BankAccount bankAccount = bankAccountRepo.getBankAccountById(id);
            return gson.toJson(bankAccount);
        }catch (Exception e){
            System.out.println("Error in getBankAccountById : "+e.getMessage());
            return gson.toJson("Error in getBankAccountById : "+e.getMessage());
        }
    }
}
