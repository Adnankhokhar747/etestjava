package com.example.einvoicing.dto;

import java.util.ArrayList;
import java.util.List;

public class InvoiceDTO {
    //        @Id
//        @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    public String serialNo;
    public String hash;
    public String previousHash;
    public String paymentType;
    public String originalRefNo;
    public String type;
    public String supplyDate;
    public String dateTime;
    public String reasonChangingStandard;
    public String invoiceNumber;
    //    public String creditNote;
//    public String debitNote;
    public String invoiceName;
    public String invoiceDescription;
    public String posoNumber;
    public String billTo;
    public String invoiceDate;
    public String paymentDue;
    public String total;
    public String discount;
    public String totalTaxableAmount;
    public String totalVat;
    public String totalAmountDue;
    public String userId;
    public String notes;
    public String status;
    public String currency;
    public String companyID;
    public String customerName;
    public String totalExcludingVAT;
    public String serviceLocation;
    public String downPayment;
    public String retention;
    public String totalNetAmount;
    public String recordPayment;
    public String bankAccount;
    public String referenceField;
    public String projectCode;
    public String location;
    public List<LineItemDTO> lineItems = new ArrayList<>();
    public String amountSAR;
    public String paymentTerm;
    public String customer;
    public String company;

}