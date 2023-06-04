package com.example.einvoicing.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String vatRates;
    private String companyID;

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVatRates() {
        return vatRates;
    }

    public void setVatRates(String vatRates) {
        this.vatRates = vatRates;
    }

}
