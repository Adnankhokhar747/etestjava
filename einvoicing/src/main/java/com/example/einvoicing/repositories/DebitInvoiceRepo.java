package com.example.einvoicing.repositories;

import com.example.einvoicing.entities.DebitInvoice;
import com.example.einvoicing.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DebitInvoiceRepo extends JpaRepository<DebitInvoice, Long> {

    List<DebitInvoice> findByCompanyID(String companyID);
    @Query("SELECT invoice FROM DebitInvoice invoice WHERE invoice.companyID= :companyID")
    public List<DebitInvoice> getDebitInvoicesByCompanyID(@Param("companyID") String companyID);

    @Query("SELECT invoice FROM DebitInvoice invoice WHERE invoice.companyID= :companyID and invoice.location= :location")
    public List<DebitInvoice> getDebitInvoicesByLocCompanyID(@Param("companyID") String companyID, @Param("location") String location);

    @Query("SELECT invoice FROM DebitInvoice invoice WHERE invoice.id= :id")
    public DebitInvoice getDebitInvoicesById(String id);

    @Modifying
    @Query("delete from DebitInvoice invoice where invoice.id=:id")
    void deleteById(@Param("id") String id);

}
