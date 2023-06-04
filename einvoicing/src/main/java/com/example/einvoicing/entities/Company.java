package com.example.einvoicing.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Company {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String companyID;
    private String firstName;
    private String lastName;
    private String companyName;
    private String email;
    private String password;
    private String address1;
    private String address2;
    private String cellNo;
    private String notes;
    private String website;
    private String currency;
    private String country;
    private String state;
    private String city;
    private String districtNeighborhood;
    private String postalCode;
    private String userType;
    private String vatNumber_Company;
    private String additionalNumber_Company;
    private String otherSellerid_Company;
    private String loginToken;
    private String limitUsers;
    private String limitInvoices;
    private Date creationDate;
    private boolean twoFactorAuthentication;

    private String companyNameInArabic;
    private String companyadressoneInArabic;
    private String companyadresstwoInArabic;
    private String companycountryInArabic;
    private String companystateInArabic;
    private String companycityInArabic;
    private String postalCodeArabic;
//    //added for multiple accounts
//    private String email1;
//    private String password1;
//    private String loginToken1;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCellNo() {
        return cellNo;
    }

    public void setCellNo(String cellNo) {
        this.cellNo = cellNo;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrictNeighborhood() {
        return districtNeighborhood;
    }

    public void setDistrictNeighborhood(String districtNeighborhood) {
        this.districtNeighborhood = districtNeighborhood;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getVatNumber_Company() {
        return vatNumber_Company;
    }

    public void setVatNumber_Company(String vatNumber_Company) {
        this.vatNumber_Company = vatNumber_Company;
    }

    public String getAdditionalNumber_Company() {
        return additionalNumber_Company;
    }

    public void setAdditionalNumber_Company(String additionalNumber_Company) {
        this.additionalNumber_Company = additionalNumber_Company;
    }

    public String getOtherSellerid_Company() {
        return otherSellerid_Company;
    }

    public void setOtherSellerid_Company(String otherSellerid_Company) {
        this.otherSellerid_Company = otherSellerid_Company;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public String getLimitUsers() {
        return limitUsers;
    }

    public void setLimitUsers(String limitUsers) {
        this.limitUsers = limitUsers;
    }

    public String getLimitInvoices() {
        return limitInvoices;
    }

    public void setLimitInvoices(String limitInvoices) {
        this.limitInvoices = limitInvoices;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isTwoFactorAuthentication() {
        return twoFactorAuthentication;
    }

    public void setTwoFactorAuthentication(boolean twoFactorAuthentication) {
        this.twoFactorAuthentication = twoFactorAuthentication;
    }

    public String getCompanyNameInArabic() {
        return companyNameInArabic;
    }

    public void setCompanyNameInArabic(String companyNameInArabic) {
        this.companyNameInArabic = companyNameInArabic;
    }

    public String getCompanyadressoneInArabic() {
        return companyadressoneInArabic;
    }

    public void setCompanyadressoneInArabic(String companyadressoneInArabic) {
        this.companyadressoneInArabic = companyadressoneInArabic;
    }

    public String getCompanyadresstwoInArabic() {
        return companyadresstwoInArabic;
    }

    public void setCompanyadresstwoInArabic(String companyadresstwoInArabic) {
        this.companyadresstwoInArabic = companyadresstwoInArabic;
    }

    public String getCompanycountryInArabic() {
        return companycountryInArabic;
    }

    public void setCompanycountryInArabic(String companycountryInArabic) {
        this.companycountryInArabic = companycountryInArabic;
    }

    public String getCompanystateInArabic() {
        return companystateInArabic;
    }

    public void setCompanystateInArabic(String companystateInArabic) {
        this.companystateInArabic = companystateInArabic;
    }

    public String getCompanycityInArabic() {
        return companycityInArabic;
    }

    public void setCompanycityInArabic(String companycityInArabic) {
        this.companycityInArabic = companycityInArabic;
    }

    public String getPostalCodeArabic() {
        return postalCodeArabic;
    }

    public void setPostalCodeArabic(String postalCodeArabic) {
        this.postalCodeArabic = postalCodeArabic;
    }
}