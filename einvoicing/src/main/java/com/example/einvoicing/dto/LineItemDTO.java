package com.example.einvoicing.dto;

import jakarta.persistence.*;

public class LineItemDTO {

        public long id;
        public String productId;
        public String productName;
        public String price;
        public String quantity;
        public String taxableAmount;
        public String discount;
        public String vat;
        public String taxAmount;
        public String itemSubTotal;
        public String invoice;
        public String amountBeforeTax;
        public String description;
        public String vatchange;

    }