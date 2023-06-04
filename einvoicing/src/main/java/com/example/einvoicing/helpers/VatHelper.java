package com.example.einvoicing.helpers;

import com.example.einvoicing.entities.BankAccount;
import com.example.einvoicing.entities.ErrorCustom;
import com.example.einvoicing.entities.ProductMain;
import com.example.einvoicing.entities.Vat;
import com.example.einvoicing.repositories.VatRepo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VatHelper {
    private Gson gson = new Gson();
    @Autowired
    VatRepo vatRepo;

    public String saveVat(Vat vat){
        ErrorCustom error = new ErrorCustom();
        String jsonError;
        try{
            vatRepo.save(vat);
            error.setErrorStatus("success");
            error.setError("VAT: " + vat.getVatRates() + " has been added successfully");
            jsonError = gson.toJson(error);
            return jsonError;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return gson.toJson(e.getMessage());
        }
    }

    public String getVatByCompanyID(String companyID) {
        try{
            List<Vat> VatList = vatRepo.getVatByCompanyID(companyID);
            return gson.toJson(VatList);
        }catch (Exception e){
            System.out.println("Error in getProductsByCompanyID : "+e.getMessage());
            return gson.toJson("Error in getProductsByCompanyID : "+e.getMessage());
        }
    }

    public String deleteVatById(String id) {
        ErrorCustom error = new ErrorCustom();
        String jsonError;
        try{
            vatRepo.deleteById(id);
            error.setErrorStatus("success");
            error.setError(" Vat deleted successfully!!!");
            jsonError = gson.toJson(error);
            return jsonError;
        }catch (Exception e){
            System.out.println("Error in getVatByCompanyID : "+e.getMessage());
            return gson.toJson("Error in getVatByCompanyID : "+e.getMessage());
        }
    }
}
