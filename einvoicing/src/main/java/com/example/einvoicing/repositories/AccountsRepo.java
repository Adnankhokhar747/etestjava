package com.example.einvoicing.repositories;

import com.example.einvoicing.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsRepo extends JpaRepository<Account, String> {


}
