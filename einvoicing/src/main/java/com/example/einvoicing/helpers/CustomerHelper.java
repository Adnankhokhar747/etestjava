package com.example.einvoicing.helpers;

import com.example.einvoicing.entities.CreditInvoice;
import com.example.einvoicing.entities.Customer;
import com.example.einvoicing.entities.ErrorCustom;
import com.example.einvoicing.entities.User;
import com.example.einvoicing.repositories.CustomerRepo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerHelper {
    private Gson gson = new Gson();
    @Autowired
    CustomerRepo customerRepo;

    public String saveCustomer(Customer customer) {
        ErrorCustom error = new ErrorCustom();
        String jsonError;
        try {
            customerRepo.save(customer);
            error.setErrorStatus("success");
            error.setError("Customer has been added successfully");
            jsonError = gson.toJson(error);
            return jsonError;
        }catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return gson.toJson(e.getMessage());
        }
    }

    public String getCustomerByCompanyID(String companyID) {
        try{
            List<Customer> CustomerList = customerRepo.getCustomerByCompanyID(companyID);
            return gson.toJson(CustomerList);
        }catch (Exception e){
            System.out.println("Error in getCustomerByCompanyID : "+e.getMessage());
            return gson.toJson("Error in getCustomerByCompanyID : "+e.getMessage());
        }
    }

    public String deleteCustomerById(String id) {
        ErrorCustom error = new ErrorCustom();
        String jsonError;
        try{
            customerRepo.deleteById(id);
            error.setErrorStatus("success");
            error.setError("Customer deleted successfully!!!");
            jsonError = gson.toJson(error);
            return jsonError;
        }catch (Exception e){
            System.out.println("Error in getCustomerById : "+e.getMessage());
            return gson.toJson("Error in getCustomerById : "+e.getMessage());
        }
    }

    public String getCustomerById(String id) {
        try{
            Customer customer = customerRepo.getCustomerById(id);
            return gson.toJson(customer);
        }catch (Exception e){
            System.out.println("Error in getCustomerByID : "+e.getMessage());
            return gson.toJson("Error in getCustomerByID : "+e.getMessage());
        }
    }
}
