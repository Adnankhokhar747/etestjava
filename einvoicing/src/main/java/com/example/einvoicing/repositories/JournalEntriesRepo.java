package com.example.einvoicing.repositories;

import com.example.einvoicing.entities.JournalEntries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalEntriesRepo extends JpaRepository<JournalEntries, String> {
}
