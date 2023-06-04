package com.example.einvoicing.repositories;

import com.example.einvoicing.entities.Translation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranslationRepo extends JpaRepository<Translation, String> {

}
