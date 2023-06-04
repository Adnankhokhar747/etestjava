package com.example.einvoicing.helpers;

import com.example.einvoicing.entities.ErrorCustom;
import com.example.einvoicing.entities.InvoiceB2C;
import com.example.einvoicing.entities.Location;
import com.example.einvoicing.entities.Vat;
import com.example.einvoicing.repositories.InvoiceB2CRepo;
import com.example.einvoicing.repositories.LocationRepo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LocationHelper {
    private Gson gson = new Gson();
    @Autowired
    LocationRepo locationRepo;

    public String saveLocation(Location location) {
        ErrorCustom error = new ErrorCustom();
        String jsonError;
        try {
            locationRepo.save(location);
            error.setErrorStatus("success");
            error.setError("Location: " + location.getLocationName() + " has been added successfully");
            jsonError = gson.toJson(error);
            return jsonError;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return gson.toJson(e.getMessage());
        }
    }

    public String getLocationByCompanyID(String companyID) {
        try{
            List<Location> LocationList = locationRepo.getLocationByCompanyID(companyID);
            return gson.toJson(LocationList);
        }catch (Exception e){
            System.out.println("Error in getProductsByCompanyID : "+e.getMessage());
            return gson.toJson("Error in getProductsByCompanyID : "+e.getMessage());
        }
    }

    public String deleteLocationById(String id) {
        ErrorCustom error = new ErrorCustom();
        String jsonError;
        try{
            locationRepo.deleteById(id);
            error.setErrorStatus("success");
            error.setError("Deleted successfully");
            jsonError = gson.toJson(error);
            return jsonError;
        }catch (Exception e){
            System.out.println("Error in getProductsByCompanyID : "+e.getMessage());
            return gson.toJson("Error in getProductsByCompanyID : "+e.getMessage());
        }
    }
}
