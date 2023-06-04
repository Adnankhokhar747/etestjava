package com.example.einvoicing.repositories;

import com.example.einvoicing.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.einvoicing.entities.Company;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompanyRepo extends JpaRepository<Company, Long> {
    @Query("SELECT company FROM Company company WHERE company.email = :email and company.password= :password")
    public Company getUserByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    @Query("SELECT company FROM Company company WHERE company.companyID= :companyID")
    public Company getCompanyByCompanyID(@Param("companyID") String companyID);

    @Modifying
    @Query("delete from Company company where company.id=:id")
    void deleteById(@Param("id") long id);

    @Query("SELECT company FROM Company company WHERE company.email= :email")
    public Company getCompanyByEmail(String email);
}
