package com.example.einvoicing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//DataSourceAutoConfiguration.class, 
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class EInvoicingApplication {

	public static void main(String[] args) {
		SpringApplication.run(EInvoicingApplication.class, args);
	}

}
