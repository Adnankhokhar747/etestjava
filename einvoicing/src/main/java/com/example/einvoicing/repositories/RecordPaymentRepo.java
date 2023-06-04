package com.example.einvoicing.repositories;

import com.example.einvoicing.entities.RecordPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordPaymentRepo extends JpaRepository<RecordPayment, String> {

}
