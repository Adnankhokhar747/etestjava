package com.example.einvoicing.helpers;

import com.example.einvoicing.entities.BankAccount;
import com.example.einvoicing.entities.ErrorCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.einvoicing.entities.Company;
import com.example.einvoicing.repositories.CompanyRepo;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyHelper {
	
	@Autowired
	private CompanyRepo companyRepo;
	private Gson gson = new Gson();
//    private Logger logger = (Logger) LoggerFactory.getLogger(CompanyHelper.class);
	
	public String saveCompany(Company company) {
		ErrorCustom error = new ErrorCustom();
		String jsonError;
		try {
			companyRepo.save(company);
			error.setErrorStatus("success");
			error.setError("Company deleted successfully!!!");
			jsonError = gson.toJson(error);
			return jsonError;
		}catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			return gson.toJson(e.getMessage());
		}
	}

    public String getCompanyByCompanyID(String companyID) {
		try{
			Company company = companyRepo.getCompanyByCompanyID(companyID);
			return gson.toJson(company);
		}catch (Exception e){
			System.out.println("Error in getCompanyByCompanyID : "+e.getMessage());
			return gson.toJson("Error in getCompanyByCompanyID : "+e.getMessage());
		}
	}

    public String deleteCompanyById(long id) {
		ErrorCustom error = new ErrorCustom();
		String jsonError;
		try{
			companyRepo.deleteById(id);
			error.setErrorStatus("success");
			error.setError("Company deleted successfully!!!");
			jsonError = gson.toJson(error);
			return jsonError;
		}catch (Exception e){
			System.out.println("Error in getProductsByCompanyID : "+e.getMessage());
			return gson.toJson("Error in getProductsByCompanyID : "+e.getMessage());
		}
	}
}
