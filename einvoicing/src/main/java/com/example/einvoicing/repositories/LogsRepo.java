package com.example.einvoicing.repositories;

import com.example.einvoicing.entities.Logs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogsRepo extends JpaRepository<Logs, String> {

}
