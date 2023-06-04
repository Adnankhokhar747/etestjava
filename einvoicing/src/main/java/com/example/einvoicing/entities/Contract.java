package com.example.einvoicing.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String companyID;
    private String projectName;
    private String projectLocation;
    private String projectDescription;
    private String projectManager;
    private String customerName;
    private String projectType;
    private String projectFeeTotal;
    private String contractCurrency;
    private String retentionMoney;
    private String paymentTerms;
    private String addExtracKey;
    private String textField;
    private String Percentageinp;
    private Date durationstartdate;
    private Date durationenddate;
    private String billingfrequency;
    private String advancepercentage;
    private String advanceretentionMoney;
    private String advancesettlement;
    private String retentionpercentageproject;
    private String retentionmoneyproject;
    private boolean isInvoiceRaised;
    private String customerId;
    @Transient
    private List<ContractItem> contractItems;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public String getProjectFeeTotal() {
        return projectFeeTotal;
    }

    public void setProjectFeeTotal(String projectFeeTotal) {
        this.projectFeeTotal = projectFeeTotal;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public String getAddExtracKey() {
        return addExtracKey;
    }

    public void setAddExtracKey(String addExtracKey) {
        this.addExtracKey = addExtracKey;
    }

    public String getTextField() {
        return textField;
    }

    public void setTextField(String textField) {
        this.textField = textField;
    }

    public String getPercentageinp() {
        return Percentageinp;
    }

    public void setPercentageinp(String percentageinp) {
        Percentageinp = percentageinp;
    }

    public Date getDurationstartdate() {
        return durationstartdate;
    }

    public void setDurationstartdate(Date durationstartdate) {
        this.durationstartdate = durationstartdate;
    }

    public Date getDurationenddate() {
        return durationenddate;
    }

    public void setDurationenddate(Date durationenddate) {
        this.durationenddate = durationenddate;
    }

    public String getBillingfrequency() {
        return billingfrequency;
    }

    public void setBillingfrequency(String billingfrequency) {
        this.billingfrequency = billingfrequency;
    }

    public String getAdvancepercentage() {
        return advancepercentage;
    }

    public void setAdvancepercentage(String advancepercentage) {
        this.advancepercentage = advancepercentage;
    }

    public String getAdvanceretentionMoney() {
        return advanceretentionMoney;
    }

    public void setAdvanceretentionMoney(String advanceretentionMoney) {
        this.advanceretentionMoney = advanceretentionMoney;
    }

    public String getAdvancesettlement() {
        return advancesettlement;
    }

    public void setAdvancesettlement(String advancesettlement) {
        this.advancesettlement = advancesettlement;
    }

    public String getRetentionpercentageproject() {
        return retentionpercentageproject;
    }

    public void setRetentionpercentageproject(String retentionpercentageproject) {
        this.retentionpercentageproject = retentionpercentageproject;
    }

    public String getRetentionmoneyproject() {
        return retentionmoneyproject;
    }

    public void setRetentionmoneyproject(String retentionmoneyproject) {
        this.retentionmoneyproject = retentionmoneyproject;
    }

    public boolean isInvoiceRaised() {
        return isInvoiceRaised;
    }

    public void setInvoiceRaised(boolean invoiceRaised) {
        isInvoiceRaised = invoiceRaised;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectLocation() {
        return projectLocation;
    }

    public void setProjectLocation(String projectLocation) {
        this.projectLocation = projectLocation;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getContractCurrency() {
        return contractCurrency;
    }

    public void setContractCurrency(String contractCurrency) {
        this.contractCurrency = contractCurrency;
    }

    public String getRetentionMoney() {
        return retentionMoney;
    }

    public void setRetentionMoney(String retentionMoney) {
        this.retentionMoney = retentionMoney;
    }

    public List<ContractItem> getContractItems() {
        return contractItems;
    }

    public void setContractItems(List<ContractItem> contractItems) {
        this.contractItems = contractItems;
    }
}
