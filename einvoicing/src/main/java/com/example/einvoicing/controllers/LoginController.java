package com.example.einvoicing.controllers;


import com.example.einvoicing.entities.Login;
import com.example.einvoicing.helpers.LoginHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class LoginController {
    @Autowired
    LoginHelper loginHelper;

    @PostMapping("/signIn")
    public String signIn(@RequestBody Login login) {
        return loginHelper.signIn(login) ;
    }

}
