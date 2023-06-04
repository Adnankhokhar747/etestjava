package com.example.einvoicing.controllers;

import com.example.einvoicing.helpers.ReportsHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class ReportsController {

    @Autowired
    ReportsHelper reportsHelper;

    @GetMapping("/getTopCustomerInvoices")
    public String getTopCustomerInvoices(@RequestParam String companyID)  {
        return reportsHelper.getTopCustomerInvoices(companyID);
    }

    @GetMapping("/getTopSoldProducts")
    public String getTopSoldProducts(@RequestParam String companyID)  {
        return reportsHelper.getTopSoldProducts(companyID);
    }

//    @GetMapping("/getReportFiltersInvoiceB2C")
//    public String getReportFiltersInvoiceB2C(@RequestParam String companyID, String id, String startDate, String endDate){
//        return reportsHelper.getReportFiltersInvoiceB2C(companyID, id, startDate, endDate);
//    }
//
//    @GetMapping("/getReportFilters")
//    public String getReportFilters(@RequestParam String companyID, String id, String customer, String location, String startDate, String endDate){
//        return reportsHelper.getReportFilters(companyID, id, customer, location, startDate, endDate);
//    }
//
//    @GetMapping("/getReportFiltersCompany")
//    public String getReportFiltersCompany(@RequestParam String companyID, String id, String customer, String location, String startDate, String endDate){
//        return reportsHelper.getReportFilters(companyID,"", customer, location, startDate, endDate);
//    }
//
    @GetMapping("/getReportB2CByCompany")
    public String getReportB2CByCompany(@RequestParam String companyID){
        return reportsHelper.getReportB2CByCompany(companyID);
    }

    @GetMapping("/getReportByCompany")
    public String getReportByCompany(@RequestParam String companyID){
        return reportsHelper.getReportByCompany(companyID);
    }

    @GetMapping("/getReportB2CByUser")
    public String getReportB2CByUser(@RequestParam String id){
        return reportsHelper.getReportB2CByUser(id);
    }

    @GetMapping("/getReportByUser")
    public String getReportByUser(@RequestParam String id, String location){
        return reportsHelper.getReportByUser(id, location);
    }

    @GetMapping("/getTopSoldProductsByDate")
    public String getTopSoldProductsByDate(@RequestParam String startDate, String endDate, String companyID) throws ParseException, ParseException {
        return reportsHelper.getTopSoldProductsByDate(startDate, endDate, companyID);
    }

//    @GetMapping("/getTotalSalesByDate")
//    public String getTotalSalesByDate(@RequestParam String startDate, String endDate, String companyID) throws ParseException {
//        return reportsHelper.getTotalSalesByDate(startDate, endDate, companyID);}
//
//    @GetMapping("/getTopCustomerInvoicesByDates")
//    public String getTopCustomerInvoicesByDates(@RequestParam String startDate, String endDate, String companyID) throws ParseException {
//        return reportsHelper.getTopCustomerInvoicesByDates(startDate, endDate, companyID);}
//
//    @GetMapping("/getInvoicesByDuration")
//    public String getInvoicesByDuration(@RequestParam String startDate, String endDate, String companyID) throws ParseException {
//        return reportsHelper.getInvoicesByDuration(startDate, endDate, companyID);}
//
//    @GetMapping("/getInvoicesB2CByDuration")
//    public String getInvoicesB2CByDuration(@RequestParam String startDate, String endDate, String companyID) throws ParseException {
//        return reportsHelper.getInvoicesB2CByDuration(startDate, endDate, companyID);}
}
