package com.example.einvoicing.repositories;

import com.example.einvoicing.entities.BankAccount;
import com.example.einvoicing.entities.Vat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VatRepo extends JpaRepository<Vat, String> {

    @Query("SELECT vat FROM Vat vat WHERE vat.companyID= :companyID")
    public List<Vat> getVatByCompanyID(@Param("companyID") String companyID);

    @Modifying
    @Query("delete from Vat vat where vat.id=:id")
    void deleteById(@Param("id") String id);
}
