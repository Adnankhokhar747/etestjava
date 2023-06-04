package com.example.einvoicing.repositories;

import com.example.einvoicing.entities.BankAccount;
import com.example.einvoicing.entities.Customer;
import com.example.einvoicing.entities.ProductMain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BankAccountRepo extends JpaRepository<BankAccount, Long> {

    @Query("SELECT Bank FROM BankAccount Bank WHERE Bank.companyID= :companyID")
    public List<BankAccount> getBankAccountsByCompanyID(@Param("companyID") String companyID);

    @Modifying
    @Query("delete from BankAccount bankAccount where bankAccount.id=:id")
    void deleteById(@Param("id") String id);

    @Query("SELECT bankAccount FROM BankAccount bankAccount WHERE bankAccount.id= :id")
    public BankAccount getBankAccountById(String id);
}
