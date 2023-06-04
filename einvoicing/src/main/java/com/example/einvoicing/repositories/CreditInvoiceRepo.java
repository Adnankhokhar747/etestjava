package com.example.einvoicing.repositories;

import com.example.einvoicing.entities.CreditInvoice;
import com.example.einvoicing.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CreditInvoiceRepo extends JpaRepository<CreditInvoice, Long> {

    List<CreditInvoice> findByCompanyID(String companyID);
    @Query("SELECT invoice FROM CreditInvoice invoice WHERE invoice.companyID= :companyID")
    public List<CreditInvoice> getCreditInvoicesByCompanyID(@Param("companyID") String companyID);

    @Query("SELECT invoice FROM CreditInvoice invoice WHERE invoice.companyID= :companyID and invoice.location= :location")
    public List<CreditInvoice> getCreditInvoicesByLocCompanyID(@Param("companyID") String companyID, @Param("location") String location);

    @Modifying
    @Query("delete from CreditInvoice invoice where invoice.id=:id")
    void deleteById(@Param("id") String id);

    @Query("SELECT invoice FROM CreditInvoice invoice WHERE invoice.id= :id")
    public CreditInvoice getCreditInvoicesById(String id);


}
