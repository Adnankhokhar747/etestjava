package com.example.einvoicing.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "logo")
public class Logo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_id")
    private String companyID;

    @Lob
    @Column(name = "data", columnDefinition = "MEDIUMBLOB")
    private byte[] data;

    // Add any additional fields, getters, and setters as needed

    // Constructors

    public Logo() {
    }

    public Logo(String companyID, byte[] data) {
        this.companyID = companyID;
        this.data = data;
    }

    // Getter and Setter methods

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}