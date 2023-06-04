package com.example.einvoicing.repositories;

import com.example.einvoicing.entities.CreditInvoiceB2C;
import com.example.einvoicing.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CreditInvoiceB2CRepo extends JpaRepository<CreditInvoiceB2C, Long> {

    List<CreditInvoiceB2C> findByCompanyID(String companyID);
    @Query("SELECT invoice FROM CreditInvoiceB2C invoice WHERE invoice.companyID= :companyID")
    public List<CreditInvoiceB2C> getCreditInvoicesB2CByCompanyID(@Param("companyID") String companyID);

    @Query("SELECT invoice FROM CreditInvoiceB2C invoice WHERE invoice.companyID= :companyID and invoice.location= :location")
    public List<CreditInvoiceB2C> getCreditInvoicesB2CByLocCompanyID(@Param("companyID") String companyID, @Param("location") String location);

    @Modifying
    @Query("delete from CreditInvoiceB2C invoice where invoice.id=:id")
    void deleteById(@Param("id") String id);

    @Query("SELECT invoice FROM CreditInvoiceB2C invoice WHERE invoice.id= :id")
    public CreditInvoiceB2C getCreditInvoicesB2CById(String id);
}
