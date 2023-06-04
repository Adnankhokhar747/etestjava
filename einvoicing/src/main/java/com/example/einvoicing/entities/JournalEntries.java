package com.example.einvoicing.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class JournalEntries {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String entryState;
    private Date entryDate;
    private String customerName;
    private String invoiceNo;
    private String chartOfAccount;
    private String credit = "0";
    private String debit = "0";

    public void setEntryState(String entryState) {
        this.entryState = entryState;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public void setChartOfAccount(String chartOfAccount) {
        this.chartOfAccount = chartOfAccount;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public void setDebit(String debit) {
        this.debit = debit;
    }

    public String getEntryState() {
        return entryState;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public String getChartOfAccount() {
        return chartOfAccount;
    }

    public String getCredit() {
        return credit;
    }

    public String getDebit() {
        return debit;
    }
}
