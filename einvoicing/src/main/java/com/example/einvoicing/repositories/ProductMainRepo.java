package com.example.einvoicing.repositories;

import com.example.einvoicing.entities.ProductMain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductMainRepo extends JpaRepository<ProductMain, Long> {

    @Query("SELECT product FROM ProductMain product WHERE product.companyID= :companyID")
    public List<ProductMain> getProductMainsByCompanyID(@Param("companyID") String companyID);

    @Modifying
    @Query("delete from ProductMain productMain where productMain.id=:id")
    void deleteById(@Param("id") String id);

}
