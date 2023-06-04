package com.example.einvoicing.repositories;

import com.example.einvoicing.entities.PaymentCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentCardRepo extends JpaRepository<PaymentCard, String> {

}
