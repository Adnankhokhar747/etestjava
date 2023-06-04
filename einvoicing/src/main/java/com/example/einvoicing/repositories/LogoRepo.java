package com.example.einvoicing.repositories;

import com.example.einvoicing.entities.Logo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LogoRepo extends JpaRepository<Logo, Long> {

    Optional<Logo> findByCompanyID(String companyID);

}

