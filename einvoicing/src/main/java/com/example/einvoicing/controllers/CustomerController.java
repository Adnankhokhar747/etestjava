package com.example.einvoicing.controllers;

import com.example.einvoicing.entities.CreditInvoice;
import com.example.einvoicing.entities.Customer;
import com.example.einvoicing.helpers.CreditInvoiceHelper;
import com.example.einvoicing.helpers.CustomerHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class CustomerController {
    @Autowired
    CustomerHelper customerHelper;

    @PostMapping("/saveCustomer")
    public String saveCustomer(@RequestBody Customer customer) {return customerHelper.saveCustomer(customer) ;}

    @PostMapping("/updateCustomer")
    public String updateCustomer(@RequestBody Customer customer) {return customerHelper.saveCustomer(customer) ;}

    @PostMapping("/deleteCustomerById")
    public String deleteCustomerById(@RequestParam String id) {return customerHelper.deleteCustomerById(id);}

    @GetMapping("/getCustomerByCompanyID")
    public String getCustomerByCompanyID(@RequestParam String companyID) {return customerHelper.getCustomerByCompanyID(companyID) ;}

    @GetMapping("/getCustomerById")
    public String getCustomerById(@RequestParam String id) {return customerHelper.getCustomerById(id) ;}
}
