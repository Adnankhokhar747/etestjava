package com.example.einvoicing.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "invoice")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String uuid;
    private String serialNo;
    private String hash;
    private String previousHash;
    private String paymentType;
    private String originalRefNo;
    private String type;
    private String supplyDate;
    private String dateTime;
    private String reasonChangingStandard;
    private String invoiceNumber;
    private String invoiceName;
    private String invoiceDescription;
    private String posoNumber;
    private String billTo;
    private String invoiceDate;
    private String paymentDue;
    private String total;
    private String discount;
    private String totalTaxableAmount;
    private String totalVat;
    private String totalAmountDue;
    private String userId;
    private String notes;
    private String status;
    private String currency;
    private String companyID;
    private String customerName;
    private String totalExcludingVAT;
    private String serviceLocation;
    private String downPayment;
    private String retention;
    private String totalNetAmount;
    private String recordPayment;
    private String bankAccount;
    private String referenceField;
    private String projectCode;
    private String location;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<LineItem> lineItems = new ArrayList<>();
    private String amountSAR;
    private String paymentTerm;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Customer customer;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Company company;
//    private String totalExcludingVATOthercurrency;
//    private String totalDiscountOthercurrency;
//    private String totaltaxableAmountOthercurrency;
//    private String totalVATOthercurrency;
//    private String totalAmountDueOthercurrency;
//    private String totalNetAmountOthrcurrency;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public long getId() {
        return id;
    }

    public void setId(long invoice_id) {
        this.id = invoice_id;
    }

    public List<LineItem> getLineItemList() {
        return lineItems;
    }

    public void setLineItemList(List<LineItem> lineItemList) {
        this.lineItems = lineItemList;
    }

//    public Company getCompany() {
//        return company;
//    }
//
//    public void setCompany(Company company) {
//        this.company = company;
//    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(String paymentTerm) {
        this.paymentTerm = paymentTerm;
    }
    public String getAmountSAR() {
        return amountSAR;
    }

    public void setAmountSAR(String amountSAR) {
        this.amountSAR = amountSAR;
    }
//    public String getTotalVATOthercurrency() {
//        return totalVATOthercurrency;
//    }
//
//    public void setTotalVATOthercurrency(String totalVATOthercurrency) {
//        this.totalVATOthercurrency = totalVATOthercurrency;
//    }
//private MultipartFile file;
//
//    public MultipartFile getFile() {
//        return file;
//    }
//
//    public void setFile(MultipartFile file) {
//        this.file = file;
//    }


//    public String getCreditNote() {
//        return creditNote;
//    }
//
//    public void setCreditNote(String creditNote) {
//        this.creditNote = creditNote;
//    }
//
//    public String getDebitNote() {
//        return debitNote;
//    }
//
//    public void setDebitNote(String debitNote) {
//        this.debitNote = debitNote;
//    }
//public String getTotalExcludingVATOthercurrency() {
//    return totalExcludingVATOthercurrency;
//}
//
//    public void setTotalExcludingVATOthercurrency(String totalExcludingVATOthercurrency) {
//        this.totalExcludingVATOthercurrency = totalExcludingVATOthercurrency;
//    }
//
//    public String getTotalDiscountOthercurrency() {
//        return totalDiscountOthercurrency;
//    }
//
//    public void setTotalDiscountOthercurrency(String totalDiscountOthercurrency) {
//        this.totalDiscountOthercurrency = totalDiscountOthercurrency;
//    }
//
//    public String getTotaltaxableAmountOthercurrency() {
//        return totaltaxableAmountOthercurrency;
//    }
//
//    public void setTotaltaxableAmountOthercurrency(String totaltaxableAmountOthercurrency) {
//        this.totaltaxableAmountOthercurrency = totaltaxableAmountOthercurrency;
//    }
//
//    public String getTotalAmountDueOthercurrency() {
//        return totalAmountDueOthercurrency;
//    }
//
//    public void setTotalAmountDueOthercurrency(String totalAmountDueOthercurrency) {
//        this.totalAmountDueOthercurrency = totalAmountDueOthercurrency;
//    }
//
//    public String getTotalNetAmountOthrcurrency() {
//        return totalNetAmountOthrcurrency;
//    }
//
//    public void setTotalNetAmountOthrcurrency(String totalNetAmountOthrcurrency) {
//        this.totalNetAmountOthrcurrency = totalNetAmountOthrcurrency;
//    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getReferenceField() {
        return referenceField;
    }

    public void setReferenceField(String referenceField) {
        this.referenceField = referenceField;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getServiceLocation() {
        return serviceLocation;
    }

    public void setServiceLocation(String serviceLocation) {
        this.serviceLocation = serviceLocation;
    }

    public String getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(String downPayment) {
        this.downPayment = downPayment;
    }

    public String getRecordPayment() {
        return recordPayment;
    }

    public void setRecordPayment(String recordPayment) {
        this.recordPayment = recordPayment;
    }

    public String getRetention() {
        return retention;
    }

    public void setRetention(String retention) {
        this.retention = retention;
    }

    public String getTotalNetAmount() {
        return totalNetAmount;
    }

    public void setTotalNetAmount(String totalNetAmount) {
        this.totalNetAmount = totalNetAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getInvoiceName() {
        return invoiceName;
    }

    public void setInvoiceName(String invoiceName) {
        this.invoiceName = invoiceName;
    }

    public String getInvoiceDescription() {
        return invoiceDescription;
    }

    public void setInvoiceDescription(String invoiceDescription) {
        this.invoiceDescription = invoiceDescription;
    }

    public String getTotalExcludingVAT() {
        return totalExcludingVAT;
    }

    public void setTotalExcludingVAT(String totalExcludingVAT) {
        this.totalExcludingVAT = totalExcludingVAT;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

//    public List<LineItem> getLineItemList() {
//        return lineItemList;
//    }
//
//    public void setLineItemList(List<LineItem> lineItemList) {
//        this.lineItemList = lineItemList;
//    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBillTo() {
        return billTo;
    }

    public void setBillTo(String billTo) {
        this.billTo = billTo;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getTotalTaxableAmount() {
        return totalTaxableAmount;
    }

    public void setTotalTaxableAmount(String totalTaxableAmount) {
        this.totalTaxableAmount = totalTaxableAmount;
    }

    public String getTotalVat() {
        return totalVat;
    }

    public void setTotalVat(String totalVat) {
        this.totalVat = totalVat;
    }

    public String getTotalAmountDue() {
        return totalAmountDue;
    }

    public void setTotalAmountDue(String totalAmountDue) {
        this.totalAmountDue = totalAmountDue;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getPosoNumber() {
        return posoNumber;
    }

    public void setPosoNumber(String posoNumber) {
        this.posoNumber = posoNumber;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getPaymentDue() {
        return paymentDue;
    }

    public void setPaymentDue(String paymentDue) {

        this.paymentDue = paymentDue;

    }

    public String getOriginalRefNo() {
        return originalRefNo;
    }

    public void setOriginalRefNo(String originalRefNo) {
        this.originalRefNo = originalRefNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSupplyDate() {

        return supplyDate;
//        TemporalAccessor ta = DateTimeFormatter.ISO_INSTANT.parse(date);
//        Instant i = Instant.from(ta);
//        this.supplyDate = Date.from(i);
//        return supplyDate;
    }

    public void setSupplyDate(String supplyDate) {
//        TemporalAccessor ta = DateTimeFormatter.ISO_INSTANT.parse(supplyDate);
//        Instant i = Instant.from(ta);
//        this.supplyDate = Date.from(i);
        //return supplyDate;
        this.supplyDate = supplyDate;
    }

    public String getReasonChangingStandard() {
        return reasonChangingStandard;
    }

    public void setReasonChangingStandard(String reasonChangingStandard) {
        this.reasonChangingStandard = reasonChangingStandard;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }

}
