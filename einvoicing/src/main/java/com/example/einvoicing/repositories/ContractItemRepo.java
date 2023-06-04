package com.example.einvoicing.repositories;

import com.example.einvoicing.entities.ContractItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractItemRepo extends JpaRepository<ContractItem, String> {
}
