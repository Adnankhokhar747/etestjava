package com.example.einvoicing.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
    public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id")
        private long id;
        private String userId;
        private String name;
        private String email;
        private String password;
        private String location;
        private String companyID;
        private String phone;
        private boolean approve;
        private boolean productSetup;
        private boolean invoiceRaise;
        private boolean paymentReceipt;
        private boolean addCustomer;
        private String loginToken;
        private boolean twoFactorAuthentication;
//        private UserTOTP userTOTP;
//
//    public UserTOTP getUserTOTP() {
//        return userTOTP;
//    }
//
//    public void setUserTOTP(UserTOTP userTOTP) {
//        this.userTOTP = userTOTP;
//    }

    public boolean isTwoFactorAuthentication() {
        return twoFactorAuthentication;
    }

    public void setTwoFactorAuthentication(boolean twoFactorAuthentication) {
        this.twoFactorAuthentication = twoFactorAuthentication;
    }

    public boolean isAddCustomer() {
        return addCustomer;
    }

    public void setAddCustomer(boolean addCustomer) {
        this.addCustomer = addCustomer;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isApprove() {
        return approve;
    }

    public void setApprove(boolean approve) {
        this.approve = approve;
    }

    public boolean isProductSetup() {
        return productSetup;
    }

    public void setProductSetup(boolean productSetup) {
        this.productSetup = productSetup;
    }

    public boolean isInvoiceRaise() {
        return invoiceRaise;
    }

    public void setInvoiceRaise(boolean invoiceRaise) {
        this.invoiceRaise = invoiceRaise;
    }

    public boolean isPaymentReceipt() {
        return paymentReceipt;
    }

    public void setPaymentReceipt(boolean paymentReceipt) {
        this.paymentReceipt = paymentReceipt;
    }

    public String getCompanyID() {
            return companyID;
        }

        public void setCompanyID(String companyID) {
            this.companyID = companyID;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }


        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
