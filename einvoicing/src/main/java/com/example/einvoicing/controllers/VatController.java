package com.example.einvoicing.controllers;

import com.example.einvoicing.entities.ProductMain;
import com.example.einvoicing.entities.User;
import com.example.einvoicing.entities.Vat;
import com.example.einvoicing.helpers.VatHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class VatController {
    @Autowired
    VatHelper vatHelper;
    @PostMapping("/saveVat")
    public String saveVat(@RequestBody Vat vat) {return vatHelper.saveVat(vat) ;}

    @PostMapping("/updateVat")
    public String updateVat(@RequestBody Vat vat) {return vatHelper.saveVat(vat) ;}

    @PostMapping("/deleteVatById")
    public String deleteVatById(@RequestParam String id) {return vatHelper.deleteVatById(id);}

    @GetMapping("/getVatByCompanyID")
    public String getVatByCompanyID(@RequestParam String companyID) {return vatHelper.getVatByCompanyID(companyID) ;}
}
