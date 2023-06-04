package com.example.einvoicing.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class ContractItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String contractID;
    private String milestone;
    private String amount;
    private Date milestoneDueDate;
    private Date invoiceDate;
    private Date raiseInvoiceDate;
    private boolean status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContractID() {
        return contractID;
    }

    public void setContractID(String contractID) {
        this.contractID = contractID;
    }

    public String getMilestone() {
        return milestone;
    }

    public void setMilestone(String milestone) {
        this.milestone = milestone;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Date getMilestoneDueDate() {
        return milestoneDueDate;
    }

    public void setMilestoneDueDate(Date milestoneDueDate) {
        this.milestoneDueDate = milestoneDueDate;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Date getRaiseInvoiceDate() {
        return raiseInvoiceDate;
    }

    public void setRaiseInvoice(Date raiseInvoice) {
        this.raiseInvoiceDate = raiseInvoice;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
