package com.example.einvoicing.repositories;

import com.example.einvoicing.entities.CreditB2CLineItem;
import com.example.einvoicing.entities.DebitB2CLineItem;
import com.example.einvoicing.entities.LineItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DebitB2CLineitemRepo extends JpaRepository<DebitB2CLineItem, String> {

}

