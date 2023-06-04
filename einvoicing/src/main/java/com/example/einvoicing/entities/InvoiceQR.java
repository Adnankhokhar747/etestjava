package com.example.einvoicing.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class InvoiceQR {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String vatNumberCompany;
    private String companyName;
    private String exclusiveVAT;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVatNumberCompany() {
        return vatNumberCompany;
    }

    public void setVatNumberCompany(String vatNumberCompany) {
        this.vatNumberCompany = vatNumberCompany;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getExclusiveVAT() {
        return exclusiveVAT;
    }

    public void setExclusiveVAT(String exclusiveVAT) {
        this.exclusiveVAT = exclusiveVAT;
    }
}
