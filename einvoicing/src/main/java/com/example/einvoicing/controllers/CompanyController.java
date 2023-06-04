package com.example.einvoicing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.einvoicing.entities.Company;
import com.example.einvoicing.helpers.CompanyHelper;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class CompanyController {
	@Autowired
	CompanyHelper companyHelper;
	
	@PostMapping("/saveCompany")
	public String saveCompany(@RequestBody Company company) {
		return companyHelper.saveCompany(company) ;
	}

	@PostMapping("/updateCompany")
	public String updateCompany(@RequestBody Company company) {
		return companyHelper.saveCompany(company) ;
	}

	@PostMapping("/deleteCompanyById")
	public String deleteCompanyById(@RequestParam long id) {return companyHelper.deleteCompanyById(id);}

	@GetMapping("/getCompanyByCompanyID")
	public String getCompanyByCompanyID(@RequestParam String companyID) {return companyHelper.getCompanyByCompanyID(companyID) ;}
	
}
