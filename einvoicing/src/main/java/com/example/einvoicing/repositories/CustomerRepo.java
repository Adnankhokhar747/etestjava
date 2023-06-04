package com.example.einvoicing.repositories;

import com.example.einvoicing.entities.Customer;
import com.example.einvoicing.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer, String> {

    @Query("SELECT customer FROM Customer customer WHERE customer.companyID= :companyID")
    public List<Customer> getCustomerByCompanyID(@Param("companyID") String companyID);

    @Query("SELECT customer FROM Customer customer WHERE customer.customer= :customer")
    public List<Customer> getCustomerByCustomerName(@Param("customer") String customer);

    @Modifying
    @Query("delete from Customer customer where customer.id=:id")
    void deleteById(@Param("id") String id);

    @Query("SELECT customer FROM Customer customer WHERE customer.id= :id")
    public Customer getCustomerById(String id);

    Customer findById(long id);
}
