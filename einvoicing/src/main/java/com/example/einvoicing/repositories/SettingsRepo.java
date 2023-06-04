package com.example.einvoicing.repositories;

import com.example.einvoicing.entities.Settings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingsRepo extends JpaRepository<Settings, String> {

}
