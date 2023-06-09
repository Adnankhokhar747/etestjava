package com.example.einvoicing.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ProductMain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String productName;
    private String description;
    private String price;
    private String code;
    private String assignedChartofAccounts;
    private String userId;
    private String companyID;
    private String nameArabic;
    private String descriptionArabic;

    public String getDescriptionArabic() {
        return descriptionArabic;
    }

    public void setDescriptionArabic(String descriptionArabic) {
        this.descriptionArabic = descriptionArabic;
    }

    public String getNameArabic() {
        return nameArabic;
    }

    public void setNameArabic(String nameArabic) {
        this.nameArabic = nameArabic;
    }

    public ProductMain(long id, String productName, String description, String price, String code, String assignedChartofAccounts, String userId, String companyID) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.code = code;
        this.assignedChartofAccounts = assignedChartofAccounts;
        this.userId = userId;
        this.companyID = companyID;
    }

    public ProductMain() {

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAssignedChartofAccounts() {
        return assignedChartofAccounts;
    }

    public void setAssignedChartofAccounts(String assignedChartofAccounts) {
        this.assignedChartofAccounts = assignedChartofAccounts;
    }
}
