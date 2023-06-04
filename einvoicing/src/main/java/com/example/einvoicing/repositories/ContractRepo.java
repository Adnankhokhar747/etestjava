package com.example.einvoicing.repositories;

import com.example.einvoicing.entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepo extends JpaRepository<Contract, String> {

}
