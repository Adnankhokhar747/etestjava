package com.example.einvoicing.repositories;

import com.example.einvoicing.entities.InvoiceB2C;
import com.example.einvoicing.dto.InvoiceDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvoiceB2CRepo extends JpaRepository<InvoiceB2C, Long> {
    List<InvoiceB2C> findByCompanyID(String companyID);

    @Query("SELECT invoice FROM InvoiceB2C invoice JOIN FETCH invoice.lineItems WHERE invoice.invoiceNumber= :invoiceNumber")
    public InvoiceDTO getInvoiceB2CByInvoiceNumber(@Param("invoiceNumber") String invoiceNumber);
    @Query("SELECT invoice FROM InvoiceB2C invoice WHERE invoice.userId= :userId")
    public List<InvoiceB2C> getInvoiceB2CByUserId(@Param("userId") String userId);

    @Query("SELECT invoice FROM InvoiceB2C invoice JOIN FETCH invoice.lineItems lineItem WHERE invoice.companyID= :companyID and invoice.location= :location")
    public List<InvoiceB2C> getInvoicesB2CByLocCompanyID(@Param("companyID") String companyID, @Param("location") String location);

    @Transactional
    @Modifying
    @Query("DELETE FROM LineItemB2C li WHERE li.invoice = :id")
    void deleteLineItemsByInvoiceId(@Param("id") InvoiceB2C id);

    @Transactional
    @Modifying
    @Query("DELETE FROM InvoiceB2C i WHERE i.id = :id")
    void deleteInvoiceB2CById(@Param("id") long id);

    @Query("SELECT invoice FROM InvoiceB2C invoice JOIN FETCH invoice.lineItems lineItem WHERE invoice.id= :id")
    public InvoiceB2C getInvoicesB2CById(String id);


}
