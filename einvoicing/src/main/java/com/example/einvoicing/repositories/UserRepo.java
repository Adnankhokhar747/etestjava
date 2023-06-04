package com.example.einvoicing.repositories;

import com.example.einvoicing.entities.BankAccount;
import com.example.einvoicing.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepo extends JpaRepository<User, String> {

    @Query("SELECT u FROM User u WHERE u.email = :email and u.password= :password")
    public User getUserByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    @Query("SELECT user FROM User user WHERE user.companyID= :companyID")
    public List<User> getUserByCompanyID(@Param("companyID") String companyID);

    @Query("SELECT user FROM User user WHERE user.userId= :userId")
    public User getUserByUserID(@Param("userId") String userId);

    @Modifying
    @Query("delete from User user where user.id=:id")
    void deleteById(@Param("id") String id);

    @Query("SELECT user FROM User user WHERE user.email= :email")
    public User getUserByEmail(String email);
}
