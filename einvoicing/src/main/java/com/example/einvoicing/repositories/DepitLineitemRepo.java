package com.example.einvoicing.repositories;

import com.example.einvoicing.entities.CreditLineItem;
import com.example.einvoicing.entities.DebitLineItem;
import com.example.einvoicing.entities.LineItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepitLineitemRepo extends JpaRepository<DebitLineItem, String> {

}
