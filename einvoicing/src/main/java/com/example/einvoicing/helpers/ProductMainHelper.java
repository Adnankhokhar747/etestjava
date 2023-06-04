package com.example.einvoicing.helpers;

import com.example.einvoicing.entities.ErrorCustom;
import com.example.einvoicing.entities.Logs;
import com.example.einvoicing.entities.ProductMain;
import com.example.einvoicing.repositories.LogsRepo;
import com.example.einvoicing.repositories.ProductMainRepo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMainHelper {
    private Gson gson = new Gson();
    @Autowired
    ProductMainRepo productMainRepo;
    public String saveProductMain(ProductMain productMain){
        ErrorCustom error = new ErrorCustom();
        String jsonError;
        try{
            productMainRepo.save(productMain);
            error.setErrorStatus("success");
            error.setError("Product has been added successfully");
            jsonError = gson.toJson(error);
            return jsonError;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return gson.toJson(e.getMessage());
        }
    }

    public String getProductsByCompanyID(String companyID) {
        try{
            List<ProductMain> productMainList = productMainRepo.getProductMainsByCompanyID(companyID);
            return gson.toJson(productMainList);
        }catch (Exception e){
            System.out.println("Error in getProductsByCompanyID : "+e.getMessage());
            return gson.toJson("Error in getProductsByCompanyID : "+e.getMessage());
        }
    }

    public String deleteProductById(long id) {
        ErrorCustom error = new ErrorCustom();
        String jsonError;
        try{
            productMainRepo.deleteById(id);
            error.setErrorStatus("success");
            error.setError("Product deleted successfully!!!");
            jsonError = gson.toJson(error);
            return jsonError;
        }catch (Exception e){
            System.out.println("Error in getProductsByCompanyID : "+e.getMessage());
            return gson.toJson("Error in getProductsByCompanyID : "+e.getMessage());
        }
    }

    public String getProductsWithArabicByCompanyID(String companyID) {
        try{
            List<ProductMain> productMainList = productMainRepo.getProductMainsByCompanyID(companyID);
            return gson.toJson(mergeArabicName(productMainList));
        }catch (Exception e){
            System.out.println("Error in getProductsByCompanyID : "+e.getMessage());
            return gson.toJson("Error in getProductsByCompanyID : "+e.getMessage());
        }
    }
    private List<ProductMain> mergeArabicName(List<ProductMain> productMainList){
        List<ProductMain> productsList = new ArrayList<>();
        for(ProductMain productMain : productMainList) {
            if (productMain.getNameArabic() != null && !productMain.getNameArabic().isEmpty()) {
                productMain.setProductName(productMain.getProductName() + " - " + productMain.getNameArabic());
            }
            productsList.add(productMain);
        }
        return productsList;
    }

}
