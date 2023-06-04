package com.example.einvoicing.repositories;

import com.example.einvoicing.entities.CreditInvoiceB2C;
import com.example.einvoicing.entities.DebitInvoiceB2C;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DebitInvoiceB2CRepo extends JpaRepository<DebitInvoiceB2C, Long> {

    List<DebitInvoiceB2C> findByCompanyID(String companyID);

    @Query("SELECT invoice FROM DebitInvoiceB2C invoice WHERE invoice.companyID= :companyID and invoice.location= :location")
    public List<DebitInvoiceB2C> getDebitInvoicesB2CByLocCompanyID(@Param("companyID") String companyID, @Param("location") String location);

    @Modifying
    @Query("delete from DebitInvoiceB2C invoice where invoice.id=:id")
    void deleteById(@Param("id") String id);

    @Query("SELECT invoice FROM DebitInvoiceB2C invoice WHERE invoice.id= :id")
    public DebitInvoiceB2C getDebitInvoicesB2CById(String id);

}
