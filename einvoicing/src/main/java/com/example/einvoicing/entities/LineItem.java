package com.example.einvoicing.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@Table(name = "line_item")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    public class LineItem {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;
        private String productId;
        private String productName;
        private String price;
        private String quantity;
        private String taxableAmount;
        private String discount;
        private String vat;
        private String taxAmount;
        private String itemSubTotal;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "invoice_id")
        private Invoice invoice;
        private String amountBeforeTax;
        private String description;
        private String vatchange;

        public String getVatchange() {
            return vatchange;
        }

        public void setVatchange(String vatchange) {
            this.vatchange = vatchange;
        }



        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }


        public String getTaxableAmount() {
            return taxableAmount;
        }

        public void setTaxableAmount(String taxableAmount) {
            this.taxableAmount = taxableAmount;
        }

        public Invoice getInvoiceId() {
            return invoice;
        }

        public void setInvoiceId(Invoice invoiceId) {
            this.invoice = invoiceId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getVat() {
            return vat;
        }

        public void setVat(String vat) {
            this.vat = vat;
        }

        public String getTaxAmount() {
            return taxAmount;
        }

        public void setTaxAmount(String taxAmount) {
            this.taxAmount = taxAmount;
        }

        public String getItemSubTotal() {
            return itemSubTotal;
        }

        public void setItemSubTotal(String itemSubTotal) {
            this.itemSubTotal = itemSubTotal;
        }

        public String getAmountBeforeTax() {
            return amountBeforeTax;
        }

        public void setAmountBeforeTax(String amountBeforeTax) {
            this.amountBeforeTax = amountBeforeTax;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }


    }
