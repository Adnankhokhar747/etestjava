package com.example.einvoicing.helpers;

import com.example.einvoicing.entities.BankAccount;
import com.example.einvoicing.entities.ErrorCustom;
import com.example.einvoicing.entities.ProductMain;
import com.example.einvoicing.entities.User;
import com.example.einvoicing.repositories.ProductMainRepo;
import com.example.einvoicing.repositories.UserRepo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserHelper {

    private Gson gson = new Gson();
    @Autowired
    UserRepo userRepo;

    public String saveUser(User user){
        ErrorCustom error = new ErrorCustom();
        String jsonError;
        try{
            userRepo.save(user);
            error.setErrorStatus("success");
            error.setError("User has been added successfully");
            jsonError = gson.toJson(error);
            return jsonError;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return gson.toJson(e.getMessage());
        }
    }

    public String getUserByCompanyID(String companyID) {
        try{
            List<User> UserList = userRepo.getUserByCompanyID(companyID);
            return gson.toJson(UserList);
        }catch (Exception e){
            System.out.println("Error in getUserByCompanyID : "+e.getMessage());
            return gson.toJson("Error in getUserByCompanyID : "+e.getMessage());
        }
    }

    public String deleteUserById(String id) {
        ErrorCustom error = new ErrorCustom();
        String jsonError;
        try{
            userRepo.deleteById(id);
            error.setErrorStatus("success");
            error.setError("User deleted successfully!!!");
            jsonError = gson.toJson(error);
            return jsonError;
        }catch (Exception e){
            error.setErrorStatus("error");
            error.setError("Error In deleting" +e.getMessage());
            jsonError = gson.toJson(error);
            return jsonError;
        }
    }
}
