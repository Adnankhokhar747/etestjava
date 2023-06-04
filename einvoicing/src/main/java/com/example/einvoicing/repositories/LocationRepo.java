package com.example.einvoicing.repositories;

import com.example.einvoicing.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocationRepo extends JpaRepository<Location, String> {

    @Query("SELECT location FROM Location location WHERE location.companyID= :companyID")
    public List<Location> getLocationByCompanyID(@Param("companyID") String companyID);

    @Modifying
    @Query("delete from Location location where location.id=:id")
    void deleteById(@Param("id") String id);
}
