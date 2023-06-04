package com.example.einvoicing;

import com.example.einvoicing.entities.User;
import com.example.einvoicing.repositories.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.einvoicing.entities.Company;
import com.example.einvoicing.repositories.CompanyRepo;

@SpringBootTest
class EInvoicingApplicationTests {
	
	@Autowired
	CompanyRepo companyRepo;
	@Autowired
	UserRepo userRepo;

//	@Test
	void saveCompany() {
		Company company = new Company();
//		company.setCompanyID("test");
		companyRepo.save(company);
	}

//	@Test
	void saveUser() {
		User user = new User();
		user.setEmail("test@email.com");
		user.setPassword("12345");
		userRepo.save(user);
	}

} 
