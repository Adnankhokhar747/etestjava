package com.example.einvoicing.controllers;

import com.example.einvoicing.entities.ProductMain;
import com.example.einvoicing.entities.User;
import com.example.einvoicing.helpers.UserHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class UserController {
    @Autowired
    UserHelper userHelper;
    @PostMapping("/saveUser")
    public String saveUser(@RequestBody User user) {return userHelper.saveUser(user) ;}

    @PostMapping("/updateUser")
    public String updateUser(@RequestBody User user) {return userHelper.saveUser(user) ;}

    @PostMapping("/deleteUserById")
    public String deleteUserById(@RequestParam String id) {return userHelper.deleteUserById(id);}

    @GetMapping("/getUserByCompanyID")
    public String getUserByCompanyID(@RequestParam String companyID) {return userHelper.getUserByCompanyID(companyID) ;}
}
