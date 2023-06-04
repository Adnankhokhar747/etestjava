package com.example.einvoicing.repositories;

import com.example.einvoicing.entities.Customer;
import com.example.einvoicing.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepo extends JpaRepository<Report, Long> {

    public Customer findByCustomerName(String customerName);
}
