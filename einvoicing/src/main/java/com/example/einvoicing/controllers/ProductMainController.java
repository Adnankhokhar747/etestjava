package com.example.einvoicing.controllers;

import com.example.einvoicing.entities.ProductMain;
import com.example.einvoicing.helpers.ProductMainHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class ProductMainController {
    @Autowired
    ProductMainHelper productMainHelper;
    @PostMapping("/saveProductMain")
    public String saveProductMain(@RequestBody ProductMain productMain) {return productMainHelper.saveProductMain(productMain) ;}

    @PostMapping("/updateProductMain")
    public String updateProductMain(@RequestBody ProductMain productMain) {return productMainHelper.saveProductMain(productMain) ;}

    @PostMapping("/deleteProductById")
    public String deleteProductById(@RequestParam long id) {return productMainHelper.deleteProductById(id);}

    @GetMapping("/getProductsByCompanyID")
    public String getProductsByCompanyID(@RequestParam String companyID) {return productMainHelper.getProductsByCompanyID(companyID) ;}
    @GetMapping("/getProductsWithArabicByCompanyID")
    public String getProductsWithArabicByCompanyID(@RequestParam String companyID) {return productMainHelper.getProductsWithArabicByCompanyID(companyID) ;}

}
