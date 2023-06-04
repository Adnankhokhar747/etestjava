package com.example.einvoicing.controllers;

import com.example.einvoicing.entities.DebitInvoice;
import com.example.einvoicing.entities.Location;
import com.example.einvoicing.helpers.LocationHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class LocationController {
    @Autowired
    LocationHelper locationHelper;
    @PostMapping("/saveLocation")
    public String saveLocation(@RequestBody Location location) {return locationHelper.saveLocation(location) ;}

    @PostMapping("/updateLocation")
    public String updateLocation(@RequestBody Location location) {return locationHelper.saveLocation(location) ;}

    @PostMapping("/deleteLocationById")
    public String deleteLocationById(@RequestParam String id) {return locationHelper.deleteLocationById(id);}


    @GetMapping("/getLocationByCompanyID")
    public String getLocationByCompanyID(@RequestParam String companyID) {return locationHelper.getLocationByCompanyID(companyID) ;}
}
