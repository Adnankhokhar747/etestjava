package com.example.einvoicing.repositories;

import com.example.einvoicing.entities.Invoice;
import com.example.einvoicing.entities.InvoiceB2C;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvoiceRepo extends JpaRepository<Invoice, Long> {
//    @Query("SELECT invoice FROM Invoice invoice WHERE invoice.companyID = :companyID")
//    public List<Invoice> getInvoicesByCompanyID(@Param("companyID") String companyID);z
    List<Invoice> findByCompanyID(String companyID);

    @Query("SELECT invoice FROM InvoiceB2C invoice WHERE invoice.userId= :userId AND invoice.location= :location")
    public List<Invoice> getInvoiceB2CByUserIdAAndLocation(@Param("userId") String userId, @Param("location") String location);


    @Query("SELECT invoice FROM Invoice invoice JOIN FETCH invoice.lineItems lineItem WHERE invoice.invoiceNumber= :invoiceNumber")
    public Invoice getInvoiceByInvoiceNumber(@Param("invoiceNumber") String invoiceNumber);

    @Query("SELECT invoice FROM Invoice invoice JOIN FETCH invoice.lineItems lineItem WHERE invoice.companyID= :companyID and invoice.location= :location")
    public List<Invoice> getInvoicesByLocCompanyID(@Param("companyID") String companyID, @Param("location") String location);

    @Transactional
    @Modifying
    @Query("DELETE FROM LineItem li WHERE li.invoice = :id")
    void deleteLineItemsByInvoiceId(@Param("id") Invoice id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Invoice i WHERE i.id = :id")
    void deleteInvoiceById(@Param("id") long id);

    @Query("SELECT invoice FROM Invoice invoice JOIN FETCH invoice.lineItems lineItem WHERE invoice.id= :id")
    public Invoice getInvoicesById(String id);

    public List<Invoice> findByCustomerName(String customer);
}
