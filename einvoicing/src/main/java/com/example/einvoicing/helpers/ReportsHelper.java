package com.example.einvoicing.helpers;

import com.example.einvoicing.dto.TopCustomersInvoices;
import com.example.einvoicing.entities.*;
import com.example.einvoicing.mapper.InvoiceMapper;
import com.example.einvoicing.repositories.*;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ReportsHelper {
    @Autowired
    InvoiceRepo invoiceRepo;
    @Autowired
    InvoiceB2CRepo invoiceRepoB2C;
//    @Autowired
//    LineItemRepo repository;
//    @Autowired
//    InvoiceHelper invoiceHelper;
//    @Autowired
//    UserRepo userRepository;
    @Autowired
    ReportRepo reportRepo;
//    @Autowired
//    CompanyRepo companyRepo;
    @Autowired
    CustomerRepo customerRepo;
    Gson gson = new Gson();
    private List<Invoice> invoiceListMain;
    private int year;
    private Logger logger = LoggerFactory.getLogger(ReportsHelper.class);

    public void saveReport(Invoice invoice) {
        try {
            Report report = new Report();
            report.setLocation(invoice.getLocation());
            report.setBillToEnglish(invoice.getBillTo());
            report.setCustomerName(invoice.getCustomerName());
            report.setInvoiceDate(invoice.getInvoiceDate());
            report.setInvoiceNumber(invoice.getInvoiceNumber());
            report.setTotalVat(invoice.getTotalVat());
            report.setTotalNetAmount(invoice.getTotalNetAmount());
            report.setTotalAmountDue(invoice.getTotalAmountDue());
            report.setTotalExcludingVAT(invoice.getTotalExcludingVAT());
            report.setCustomerVatNo(reportRepo.findByCustomerName(invoice.getCustomerName()).getVatNumber_Customer());
            reportRepo.save(report);
        }catch (Exception exception){
            logger.warn("Exception Occurred in saving Reports :"+exception.getMessage());
        }
    }

    //product sales year wise

    public String getTopSoldProductsByDate(String startDate, String endDate, String companyID) throws ParseException {
        List<LineItem> topLineItemsList = new ArrayList<>();
        try {
            List<Invoice> invoiceListMain = invoiceRepo.findByCompanyID(companyID);
            if (invoiceListMain != null && !invoiceListMain.isEmpty())
                filterInvoiceDuration(invoiceListMain, startDate, endDate);
            for (Invoice invoice : invoiceListMain)
                topLineItemsList.addAll(invoice.getLineItemList());
            topLineItemsList = mergeAndCompute(topLineItemsList);
        } catch (Exception ex) {
            logger.info("Error in get Top Sold Products:" + ex.getMessage());
            System.out.println("Error in get Top Sold Products:" + ex.getMessage());
            return gson.toJson(ex.getMessage());
        }
        ////        DateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
////        startDate = startDate+" 00:00:00";
////        endDate = endDate+" 23:59:59";
////        Date startDateFinal = df.parse(startDate);
////        Date endDateFinal = df.parse(endDate);
//        List<LineItem> lineItemList = null;
//        List<LineItem> topLineItemsList = new ArrayList<LineItem>();
//        try{
//            lineItemList = mongoOperation.findAll(LineItem.class);
////            invoiceHelper.getInvoicesByCompany(companyID);
////                        Aggregation agg = new Aggregation(
////                    project("dateTime")
////                            .and(ComparisonOperators.Gte.valueOf(DateOperators.DateFromString.fromStringOf("dateTime").withFormat("%Y/%m/%d"))
////                                    .greaterThanEqualToValue(startDate))
////                            .as("matches1")
////                            .and(ComparisonOperators.Lte.valueOf(DateOperators.DateFromString.fromStringOf("dateTime").withFormat("%Y/%m/%d"))
////                                    .lessThanEqualToValue(endDate))
////                            .as("matches2"),
////                    Aggregation.match(where("matches1").is(true)),
////                    Aggregation.match(where("matches2").is(true))
////            );
////            AggregationResults<Invoice> results = mongoOperation.aggregate(agg, "persons", Invoice.class);
//            List<Invoice> invoiceList =  mongoOperation.find(new Query(where("companyID").is(companyID)
////                    .and("dateTime").gt(startDateFinal).lt(endDateFinal)
//                    .and("status").is(Constants.STATUS_APPROVED)), Invoice.class);
//            int count = 0;
//            if(invoiceList != null && !invoiceList.isEmpty())
//                filterInvoiceDuration(invoiceList, startDate, endDate);
//            while (lineItemList.size() != 0){
//                for(Invoice invoice : invoiceList){
//                    for(int k=0; k<invoice.getLineItemList().size(); k++){
//                        if(lineItemList.get(count).getProductName().equals(invoice.getLineItemList().get(k).getProductName()))
//                            topLineItemsList.add(lineItemList.get(count));
//                    }
//                }
//                lineItemList.remove(count);
//                count = 0;
//            }
//            topLineItemsList = mergeAndCompute(topLineItemsList);
//        }catch(Exception ex){
//            logger.info("Error in get invoices:"+ ex.getMessage());
//            System.out.println("Error in get invoices:"+ ex.getMessage());
//            return gson.toJson(ex.getMessage());
//        }
        return gson.toJson(InvoiceMapper.mapListLineItemDto(topLineItemsList));
    }

    //Product Sales
    public String getTopSoldProducts(String companyID){
        List<LineItem> topLineItemsList = new ArrayList<>();
        try{
            List<Invoice> invoiceListMain = invoiceRepo.findByCompanyID(companyID);
            for(Invoice invoice : invoiceListMain)
                topLineItemsList.addAll(invoice.getLineItemList());
            topLineItemsList = mergeAndCompute(topLineItemsList);
        }catch(Exception ex){
            logger.info("Error in get Top Sold Products:"+ ex.getMessage());
            System.out.println("Error in get Top Sold Products:"+ ex.getMessage());
            return gson.toJson(ex.getMessage());
        }
        return gson.toJson(InvoiceMapper.mapListLineItemDto(topLineItemsList).stream().distinct().collect(Collectors.toList()));
    }
////    old
////    public String getTopSoldProducts(String companyID){
////        List<LineItem> lineItemList = null;
////        List<LineItem> topLineItemsList = new ArrayList<LineItem>();
////        try{
////            lineItemList = mongoOperation.findAll(LineItem.class);
////            List<Invoice> invoiceList = mongoOperation.find(new Query(where("companyID").is(companyID)
////                    .and("status").is(Constants.STATUS_APPROVED)), Invoice.class);
////            int count = 0;
////            while (lineItemList.size() != 0){
////                for(Invoice invoice : invoiceList){
////                    logger.info("Access: "+ invoice.getId());
////                    for(int k=0; k<invoice.getLineItemList().size(); k++){
////                        logger.info("Line Item: "+lineItemList.get(count).getProductName()+", Invoice Item: "+invoice.getInvoiceNumber(),invoice.getId());
////                        if(lineItemList.get(count).getProductId().equals(invoice.getLineItemList().get(k).getProductId()))
////                            topLineItemsList.add(lineItemList.get(count));
////                    }
////                }
////                lineItemList.remove(count);
////                count = 0;
////            }
////           topLineItemsList = mergeAndCompute(topLineItemsList);
////        }catch(Exception ex){
////            logger.info("Error in get Top Sold Products:"+ ex.getMessage());
////            System.out.println("Error in get Top Sold Products:"+ ex.getMessage());
////            return gson.toJson(ex.getMessage());
////        }
////        return gson.toJson(topLineItemsList);
////    }
//
//    //Product Sales
    private List<LineItem> mergeAndCompute(List<LineItem> topLineItemsList) {
        for(int i=0; i<topLineItemsList.size(); i++) {
//            logger.info("Line Item i: "+topLineItemsList.get(i).getProductName()+",Quantity: "+topLineItemsList.get(i).getQuantity());
            for (int j = 1; j < topLineItemsList.size() - 1; j++) {
                try {
                    if (topLineItemsList.get(i).getProductName().equals(topLineItemsList.get(j).getProductName())){
//                        logger.info("Line Item j: " + topLineItemsList.get(j).getProductName() + ",Quantity: " + topLineItemsList.get(j).getQuantity());
                        double quantity = Double.parseDouble(topLineItemsList.get(i).getQuantity()) + Double.parseDouble(topLineItemsList.get(j).getQuantity());
                        double price = Double.parseDouble(topLineItemsList.get(i).getAmountBeforeTax()) + Double.parseDouble(topLineItemsList.get(j).getAmountBeforeTax());
                        //                        logger.info("Sum: " + quantity);
                        topLineItemsList.get(i).setQuantity(String.valueOf(quantity));
                        topLineItemsList.get(i).setAmountBeforeTax(String.valueOf(price));
                        topLineItemsList.remove(j);
                    }
                }catch (Exception exception){
                    logger.info("Exception in Merge and Compute Method: "+exception.getMessage());
                }
            }
        }
        return topLineItemsList;
    }
//                                    //Product Sales end
//    //yearWIse Sales
//
////    ToDo
////    public String getInvoicesByYear(String companyID){
////        List<TopCustomersInvoices> topCustomersInvoicesList = new ArrayList<TopCustomersInvoices> ();
////        TopCustomersInvoices topCustomersInvoices = new TopCustomersInvoices();
////        try{
////            invoiceListMain = mongoOperation.find(new Query(Criteria.where("companyID").is(companyID)
////                    .and("status").is(Constants.STATUS_APPROVED)), Invoice.class);
////            year = Calendar.getInstance().get(Calendar.YEAR);
////            while (invoiceListMain.size() != 0){
////                TopCustomersInvoices topInvoices = computeInvoicesTotal();
////                if(topInvoices != null)
////                    topCustomersInvoicesList.add(topInvoices);
////            }
////        }catch(Exception ex){
////            System.out.println("Error in get invoices:"+ ex);
////        }
////        return gson.toJson(topCustomersInvoicesList);
////    }
//
//    //ToDo
////    private TopCustomersInvoices computeInvoicesTotal() {
////        Collections.reverse(invoiceListMain);
////        year = getYear(invoiceListMain.get(0).getInvoiceDate());
////        int sum = 0,count = 0;
////        while (count < invoiceListMain.size()){
////            int invoiceYear = getYear(invoiceListMain.get(count).getInvoiceDate());
////            if(year == invoiceYear) {
////                sum += Integer.parseInt(invoiceListMain.get(count).getTotalAmountDue());
////                invoiceListMain.remove(invoiceListMain.get(count));
////                count = 0;
////            }
////            else
////                count++;
////        }
////        TopCustomersInvoices topCustomersInvoices = new TopCustomersInvoices();
////        String yearDur = year + " - " + ++year;
////        topCustomersInvoices.setCustomerName(yearDur);
////        topCustomersInvoices.setInvoiceTotal(String.valueOf(sum));
////        return topCustomersInvoices;
////    }
//
//            GetTopCustomerInvoices

    public String getTopCustomerInvoices(String companyID){
        List<TopCustomersInvoices> topCustomersInvoicesList = new ArrayList<TopCustomersInvoices> ();
        List<Customer> allCustomers = customerRepo.getCustomerByCompanyID(companyID);
        try{
            for (Customer customer : allCustomers){
                List<Invoice> invoiceList = invoiceRepo.findByCustomerName(customer.getCustomer());
                TopCustomersInvoices topCustomersInvoices = new TopCustomersInvoices();
                if(invoiceList != null && !invoiceList.isEmpty())
                    topCustomersInvoices = computeInvoiceSum(invoiceList);
                if(topCustomersInvoices != null)
                    topCustomersInvoicesList.add(topCustomersInvoices);
            }
        }catch(Exception ex){
            System.out.println("Error in get invoices:"+ ex.getMessage());
            logger.info("Error in get invoices:"+ ex.getMessage());
            return gson.toJson(ex.getMessage());
        }
        if(topCustomersInvoicesList != null && !topCustomersInvoicesList.isEmpty())
            overAllSum(topCustomersInvoicesList);
        return gson.toJson(topCustomersInvoicesList);
    }

    private void overAllSum(List<TopCustomersInvoices> topCustomersInvoicesList){
        double sum = 0;
        for(TopCustomersInvoices topCustomersInvoices : topCustomersInvoicesList) {
            try {
                sum += Double.parseDouble(topCustomersInvoices.getInvoiceTotal());
            }catch (Exception e){
                logger.info("Customer: "+topCustomersInvoices.getCustomerName()+", Exception: "+e.getMessage());
            }
        }
        TopCustomersInvoices invoicesSum = new TopCustomersInvoices();
        invoicesSum.setInvoiceTotal(String.valueOf(sum));
        invoicesSum.setCustomerName("Total");
        topCustomersInvoicesList.add(invoicesSum);
    }

    private TopCustomersInvoices computeInvoiceSum(List<Invoice> invoiceList) {
        TopCustomersInvoices topCustomersInvoices = new TopCustomersInvoices();
        double sum = 0;
        for(Invoice invoice:invoiceList){
            try {
                if(invoice.getTotalAmountDue() != null)
                    sum += Double.parseDouble(invoice.getTotalAmountDue());
                else
                    sum += Double.parseDouble(invoice.getTotalVat()) + Double.parseDouble(invoice.getTotalExcludingVAT());
            }catch (Exception e){
                logger.info("Exception occurred in Invoice No: "+invoice.getInvoiceNumber()+ ", Exception: "+e.getMessage());
            }
        }
        topCustomersInvoices.setCustomerName(invoiceList.get(0).getCustomerName());
        topCustomersInvoices.setInvoiceTotal(String.valueOf(sum));
        return topCustomersInvoices;
    }

//    public String getInvoicesB2CByDuration(String startDate, String endDate, String companyID) throws ParseException {
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        Date startDateFinal = df.parse(startDate);
//        Date endDateFinal = df.parse(endDate);
//        List<InvoiceB2C> invoicesList;
//        try{
//            invoicesList = mongoOperation.find(new Query(where("companyID").is(companyID)
//                    .and("invoiceDate").gte(startDateFinal).lte(endDateFinal)), InvoiceB2C.class);
//        } catch(Exception ex){
//            logger.info("Error in get invoicesB2C:"+ ex.getMessage());
//            System.out.println("Error in get invoicesB2C:"+ ex.getMessage());
//            return gson.toJson(ex.getMessage());
//        }
//        return gson.toJson(invoicesList);
//    }
//
//     public String getInvoicesByDuration(String startDate, String endDate, String companyID) throws ParseException {
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        Date startDateFinal = df.parse(startDate);
//        Date endDateFinal = df.parse(endDate);
//        List<Invoice> invoicesList;
//        try{
//            invoicesList = mongoOperation.find(new Query(where("companyID").is(companyID)
//                    .and("invoiceDate").gte(startDateFinal).lte(endDateFinal)
//                    .and("status").is(Constants.STATUS_APPROVED)), Invoice.class);
//        } catch(Exception ex){
//            logger.info("Error in get invoices:"+ ex.getMessage());
//            System.out.println("Error in get invoices:"+ ex.getMessage());
//            return gson.toJson(ex.getMessage());
//        }
//        return gson.toJson(invoicesList);
//    }
//
//    public String getTopCustomerInvoicesByDates(String startDate, String endDate, String companyID) throws ParseException {
////        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
////        Date startDateFinal = df.parse(startDate);
////        Date endDateFinal = df.parse(endDate);
//        List<TopCustomersInvoices> topCustomersInvoicesList = new ArrayList<TopCustomersInvoices> ();
//        try{
//            List<Invoice> invoiceList = null;
//            invoiceListMain = mongoOperation.find(new Query(where("companyID").is(companyID)
////                    .and("invoiceDate").gte(startDateFinal).lte(endDateFinal)
//                    .and("status").is(Constants.STATUS_APPROVED)), Invoice.class);
//            while (invoiceListMain.size() != 0){
//                invoiceList = null;
//                invoiceList = mongoOperation.find(new Query(where("customerName").is(invoiceListMain.get(0).getCustomerName())
////                        .and("companyID").is(companyID).and("invoiceDate").gte(startDateFinal).lte(endDateFinal)
//                        .and("status").is(Constants.STATUS_APPROVED)), Invoice.class);
//                if(invoiceList != null && !invoiceList.isEmpty())
//                    filterInvoiceDuration(invoiceList, startDate, endDate);
//                TopCustomersInvoices topCustomersInvoices = computeInvoiceSum(invoiceList);
//                if(topCustomersInvoices != null)
//                    topCustomersInvoicesList.add(topCustomersInvoices);
//            }
//        }catch(Exception ex){
//            logger.info("Error in get invoices:"+ ex.getMessage());
//            System.out.println("Error in get invoices:"+ ex.getMessage());
//            return gson.toJson(ex.getMessage());
//        }
//        if(topCustomersInvoicesList.size() > 1)
//            overAllSum(topCustomersInvoicesList);
//        return gson.toJson(topCustomersInvoicesList);
//    }
//
//    private Date addHoursToDate(Date startDateFinal, int hoursToAdd) {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(startDateFinal);
//        cal.add(Calendar.HOUR_OF_DAY, hoursToAdd);      // adds one hour
//        startDateFinal = cal.getTime();
//        return startDateFinal;
//    }
//    //GetTopCustomerInvoicesByYear End---
//
////    private void checkRemoveExisting(List<Invoice> invoiceList){
////        for(Invoice invoiceToRemove : invoiceList){
////            for(int i=0; i< invoiceListMain.size(); i++){
////                Invoice inv = invoiceListMain.get(i);
////                if(inv.getId().equals(invoiceToRemove.getId()))
////                    invoiceListMain.remove(inv);
////
////            }
////        }
////    }
//
//    //yearWIse Sales End
//
//    private int getYear(Date date){
//        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//        int year  = localDate.getYear();
//        return year;
//    }
//
//    public String getTotalSalesByDate(String startDate, String endDate, String companyID) throws ParseException {
////        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
////        Date startDateFinal = df.parse(startDate);
////        Date endDateFinal = df.parse(endDate);
//        TopCustomersInvoices topCustomersInvoice = new TopCustomersInvoices ();
//        try{
//            invoiceListMain = mongoOperation.find(new Query(where("companyID").is(companyID)
////                    .and("invoiceDate").gte(startDateFinal).lte(endDateFinal)
//                    .and("status").is(Constants.STATUS_APPROVED)), Invoice.class);
//                double sum = 0;
//                if(invoiceListMain != null && !invoiceListMain.isEmpty())
//                    filterInvoiceDuration(invoiceListMain, startDate, endDate);
//                for(Invoice invoice:invoiceListMain){
//                    if(invoice.getTotalAmountDue() != null)
//                        sum += Double.parseDouble(invoice.getTotalAmountDue());
//                    else
//                        sum += Double.parseDouble(invoice.getTotalVat()) + Double.parseDouble(invoice.getTotalExcludingVAT());
//                }
//                topCustomersInvoice.setInvoiceTotal(String.valueOf(sum));
//                topCustomersInvoice.setCustomerName(startDate + " - " + endDate);
//        }catch(Exception ex){
//            logger.info("Error in get invoices:"+ ex.getMessage());
//            System.out.println("Error in get invoices:"+ ex.getMessage());
//        }
//        return gson.toJson(topCustomersInvoice);
//    }
//
//    //get Reports InvoiceB2C by All Filters
//    public String getReportFiltersInvoiceB2C(String companyID, String userID, String startDate, String endDate){
//        List<Report> reports = new ArrayList<>();
//        try{
//            Query query = filterCriterias(companyID, userID, null, null, startDate, endDate);
//            setInvoiceB2CReports(startDate, endDate, reports, query);
//            setCreditInvoiceReports(startDate, endDate, reports, query);
//            setDebitInvoiceReports(startDate, endDate, reports, query);
//        }catch (Exception ex){
//            logger.info("Exception in get Report Filters: "+ex.getMessage());
//            return gson.toJson(ex.getMessage());
//        }
//        return gson.toJson(reports);
//    }
//
//    //get Reports by All Filters
//    public String getReportFilters(String companyID, String id, String customer, String location, String startDate, String endDate){
//        List<Report> reports = new ArrayList<>();
//        try{
//            Query query = filterCriterias(companyID, id, customer, location, startDate, endDate);
//            setInvoiceReports(startDate, endDate, reports, query);
//            setCreditInvoiceReports(startDate, endDate, reports, query);
//            setDebitInvoiceReports(startDate, endDate, reports, query);
//        }catch (Exception ex){
//            logger.info("Exception in get Report Filters: "+ex.getMessage());
//            return gson.toJson(ex.getMessage());
//        }
//        return gson.toJson(reports);
//    }
//
//    private void setInvoiceReports(String startDate, String endDate, List<Report> reports, Query query) throws ParseException {
//        List<Invoice> invoiceList = mongoOperation.find(query, Invoice.class);
//        if(!startDate.isEmpty() || !endDate.isEmpty())
//            filterInvoiceDuration(invoiceList, startDate, endDate);
//        for(Invoice invoice:invoiceList) {
//            try {
//                if(invoice.getCustomer() == null)
//                    invoice.setCustomer(mongoOperation.findById(invoice.getBillTo(), Customer.class));
//                reports.add(setInvoiceToReport(invoice));
//            }catch (Exception exception) {
//                logger.info("customer does not exists");
//            }
//        }
//    }
//
//    private void setInvoiceB2CReports(String startDate, String endDate, List<Report> reports, Query query) throws ParseException {
//        List<InvoiceB2C> invoiceList = mongoOperation.find(query, InvoiceB2C.class);
//        if(!startDate.isEmpty() || !endDate.isEmpty())
//            filterInvoiceB2CDuration(invoiceList, startDate, endDate);
//        for(InvoiceB2C invoiceB2C:invoiceList)
//            reports.add(setInvoiceB2CToReport(invoiceB2C));
//    }
//
//    private void setCreditInvoiceReports(String startDate, String endDate, List<Report> reports, Query query) throws ParseException {
//        List<CreditInvoice> invoiceList = mongoOperation.find(query, CreditInvoice.class);
//        if(!startDate.isEmpty() || !endDate.isEmpty())
//            filterCreditInvoiceDuration(invoiceList, startDate, endDate);
//        for(CreditInvoice creditInvoice:invoiceList) {
//            try {
//                if(creditInvoice.getCustomer() == null)
//                    creditInvoice.setCustomer(mongoOperation.findById(creditInvoice.getBillTo(), Customer.class));
//                reports.add(setCreditInvoiceToReport(creditInvoice));
//            }catch (Exception ex){
//                logger.info("Customer does not exist");
//            }
//        }
//    }
//
//    private void setDebitInvoiceReports(String startDate, String endDate, List<Report> reports, Query query) throws ParseException {
//        List<DebitInvoice> invoiceList = mongoOperation.find(query, DebitInvoice.class);
//        if(!startDate.isEmpty() || !endDate.isEmpty())
//            filterDebitInvoiceDuration(invoiceList, startDate, endDate);
//        for(DebitInvoice debitInvoice:invoiceList) {
//            try {
//                if(debitInvoice.getCustomer() == null)
//                    debitInvoice.setCustomer(mongoOperation.findById(debitInvoice.getBillTo(), Customer.class));
//                reports.add(setDebitInvoiceToReport(debitInvoice));
//            }catch (Exception ex){
//                logger.info("Customer does not exist");
//            }
//        }
//    }
//
//    private void filterCreditInvoiceDuration(List<CreditInvoice> creditInvoiceList, String startDate, String endDate) throws ParseException {
//        if(creditInvoiceList == null && creditInvoiceList.isEmpty())
//            creditInvoiceList = mongoOperation.findAll(CreditInvoice.class);
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
////        startDate = startDate+" 00:00:00";
////        endDate = endDate+" 23:59:59";
//        Date startDateFinal = df.parse(startDate);
//        Date endDateFinal = df.parse(endDate);
//        int count = 0;
//        if(!startDate.isEmpty()){
//            while(count < creditInvoiceList.size()){
////                DateFormat formatter = new SimpleDateFormat("uuuu-mm-dd");
//                Date invoiceDate = (Date)df.parse(creditInvoiceList.get(count).getDateTime());
//                if(invoiceDate.before(startDateFinal))
//                    creditInvoiceList.remove(creditInvoiceList.get(count));
//                else
//                    count++;
//            }
//        }
//
//        if(!endDate.isEmpty()){
//            count = 0;
//            while(count < creditInvoiceList.size()){
//                Date invoiceDate = (Date)df.parse(creditInvoiceList.get(count).getDateTime());
//                if(invoiceDate.after(endDateFinal))
//                    creditInvoiceList.remove(creditInvoiceList.get(count));
//                else
//                    count++;
//            }
//        }
//
//    }
//
//    private void filterDebitInvoiceDuration(List<DebitInvoice> debitInvoiceList, String startDate, String endDate) throws ParseException {
//        if(debitInvoiceList == null && debitInvoiceList.isEmpty())
//            debitInvoiceList = mongoOperation.findAll(DebitInvoice.class);
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
////        startDate = startDate+" 00:00:00";
////        endDate = endDate+" 23:59:59";
//        Date startDateFinal = df.parse(startDate);
//        Date endDateFinal = df.parse(endDate);
//        int count = 0;
//        if(!startDate.isEmpty()){
//            while(count < debitInvoiceList.size()){
//                Date invoiceDate = (Date)df.parse(debitInvoiceList.get(count).getDateTime());
//                if(invoiceDate.before(startDateFinal))
//                    debitInvoiceList.remove(debitInvoiceList.get(count));
//                else
//                    count++;
//            }
//        }
//
//        if(!endDate.isEmpty()){
//            count = 0;
//            while(count < debitInvoiceList.size()){
//                Date invoiceDate = (Date)df.parse(debitInvoiceList.get(count).getDateTime());
//                if(invoiceDate.after(endDateFinal))
//                    debitInvoiceList.remove(debitInvoiceList.get(count));
//                else
//                     count++;
//            }
//        }
//
//    }
//
//    //Invoice B2C filter
//    private void filterInvoiceB2CDuration(List<InvoiceB2C> invoiceList, String startDate, String endDate) throws ParseException {
//        if(invoiceList == null && invoiceList.isEmpty())
//            invoiceList = mongoOperation.findAll(InvoiceB2C.class);
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        Date startDateFinal = df.parse(startDate);
//        Date endDateFinal = df.parse(endDate);
//        int count = 0;
//        if(!startDate.isEmpty()){
//            while(count < invoiceList.size()){
//                Date date = (Date)df.parse(invoiceList.get(count).getDateTime());
//                if(date.before(startDateFinal))
//                    invoiceList.remove(invoiceList.get(count));
//                else
//                    count++;
//            }
//        }
//        if(!endDate.isEmpty()){
//            count = 0;
//            while(count < invoiceList.size()){
//                Date date = (Date)df.parse(invoiceList.get(count).getDateTime());
//                if(date.after(endDateFinal))
//                    invoiceList.remove(invoiceList.get(count));
//                else
//                    count++;
//            }
//        }
//    }

    private void filterInvoiceDuration(List<Invoice> invoiceList, String startDate, String endDate) throws ParseException {
        if(invoiceList == null && invoiceList.isEmpty())
            invoiceList = invoiceRepo.findAll();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        startDate = startDate+" 00:00:00";
//        endDate = endDate+" 23:59:59";
        Date startDateFinal = df.parse(startDate);
        Date endDateFinal = df.parse(endDate);
        int count = 0;
        if(!startDate.isEmpty()){
            while(count < invoiceList.size()){
//                Calendar cal1 = Calendar.getInstance();
//                Calendar cal2 = Calendar.getInstance();
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm:ss", Locale.ENGLISH);
//                LocalDate date = LocalDate.parse(invoiceList.get(count).getDateTime(), formatter);
//                Date date1 = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
//                cal1.setTime(date1);
//                cal2.setTime(startDateFinal);
                DateFormat formatter = new SimpleDateFormat("uuuu-mm-dd");
                Date invoiceDate = (Date)df.parse(invoiceList.get(count).getDateTime());
//                Calendar cal = Calendar.getInstance();
//                cal.setTime(date);
//                String formatedDate = cal.get(Calendar.DATE) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" +         cal.get(Calendar.YEAR);
//                date = df.parse(formatedDate);
                if(invoiceDate.before(startDateFinal))
                    invoiceList.remove(invoiceList.get(count));
                else
                count++;
            }
        }
        if(!endDate.isEmpty()){
            count = 0;
            while(count < invoiceList.size()){
                Date invoiceDate = (Date)df.parse(invoiceList.get(count).getDateTime());
//                Calendar cal = Calendar.getInstance();
//                cal.setTime(date);
//                String formatedDate = cal.get(Calendar.DATE) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" +         cal.get(Calendar.YEAR);
//                date = df.parse(formatedDate);
                if(invoiceDate.after(endDateFinal))
                    invoiceList.remove(invoiceList.get(count));
                else
                count++;
            }
        }
    }

//    private Query filterCriterias(String companyID, String id, String customer, String location,
//                                  String startDate, String endDate){
//        final List<Criteria> criteria = new ArrayList<>();
//        if(companyID != null && !companyID.isEmpty())
//            criteria.add(where("companyID").is(companyID));
//        if(id != null && !id.isEmpty())
//            criteria.add(where("userId").is(id));
//        if(customer != null && !customer.isEmpty())
//            criteria.add(where("customerName").is(customer));
//        if(location != null && !location.isEmpty())
//            criteria.add(where("location").is(location));
////        if(startDate != null && !startDate.isEmpty())
////            criteria.add(where("dateTime").gt(startDate));
////        if(!endDate.isEmpty() && endDate != null )
////            criteria.add(where("dateTime").lt(endDate));
//        Query query = new Query(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
//        return query;
//    }
//
    //get Reports by CompanyID
    public String getReportB2CByCompany(String companyID){
        List<Report> reports = new ArrayList<>();
        try{
            List<InvoiceB2C> invoiceList = invoiceRepoB2C.findByCompanyID(companyID);
            for(InvoiceB2C invoiceB2C:invoiceList)
                reports.add(setInvoiceB2CToReport(invoiceB2C));
        }catch (Exception ex){
            logger.info(ex.getMessage());
            return gson.toJson("Exception in getReportB2CByCompany "+ex.getMessage());
        }
        return gson.toJson(reports);
    }
//
//    //get Reports by CompanyID
    public String getReportByCompany(String companyID){
        List<Report> reports = new ArrayList<>();
        try{
            List<Invoice> invoiceList = invoiceRepo.findByCompanyID(companyID);
            for(Invoice invoice:invoiceList) {
                try{
//                    if(invoice.getCustomerName() == null)
//                        invoice.setCustomer(customerRepo.findById(invoice.getCustomer().getId()));
                    reports.add(setInvoiceToReport(invoice));
                }catch (Exception ex){
                    logger.info("Customer does not exist "+ex.getMessage());
                }
            }
        }catch (Exception ex){
            logger.info(ex.getMessage());
            return gson.toJson("Exception in getReportByCompany "+ex.getMessage());
        }
        return gson.toJson(reports);
    }

    public String getReportB2CByUser(String userid){
        List<Report> reports = new ArrayList<>();
        try{
            List<InvoiceB2C> invoiceList = invoiceRepoB2C.getInvoiceB2CByUserId(userid);
            for(InvoiceB2C invoiceB2C:invoiceList)
                reports.add(setInvoiceB2CToReport(invoiceB2C));
        }catch (Exception ex){
            logger.info("Exception in getReportB2CByUser "+ex.getMessage());
            return gson.toJson(ex.getMessage());
        }
        return gson.toJson(reports);
    }

    //get Reports by user's ID and location
    public String getReportByUser(String id, String location){
        List<Report> reports = new ArrayList<>();
        try{
            List<Invoice> invoiceList = invoiceRepo.getInvoiceB2CByUserIdAAndLocation(id, location);
            for(Invoice invoice:invoiceList) {
                try {
                    if(invoice.getCustomer() == null)
                        invoice.setCustomer(customerRepo.getCustomerByCustomerName(invoice.getCustomerName()).get(0));
                    reports.add(setInvoiceToReport(invoice));
                }catch (Exception ex){
                    logger.info("Customer does not exist: "+ex.getMessage());
                }
            }
        }catch (Exception ex){
            logger.info("Exception in getReportByUser "+ex.getMessage());
            return gson.toJson(ex.getMessage());
        }
        return gson.toJson(reports);
    }

    private Report setInvoiceToReport(Invoice invoice) {
        Report report = new Report();
        try {
            report.setCustomerName(invoice.getCustomerName());
            //report.setCustomerVatNo(invoice.getCustomer().getVatNumber_Customer());
            report.setInvoiceDate(invoice.getDateTime());
            report.setInvoiceNumber(invoice.getInvoiceNumber());
            report.setTotalExcludingVAT(invoice.getTotalExcludingVAT());
//        report.setTotalTaxableAmount(invoice.getTotalTaxableAmount());
            report.setTotalVat(invoice.getTotalVat());
            report.setTotalAmountDue(invoice.getTotalAmountDue());
            report.setTotalNetAmount(invoice.getTotalNetAmount());
            report.setLocation(invoice.getLocation());
        }catch (NullPointerException exception){
            logger.info("Null pointer exception: "+exception.getMessage());
        }
        return report;
    }

    //for invoice b2c
    private Report setInvoiceB2CToReport(InvoiceB2C invoiceB2C) {
        Report report = new Report();
//        Customer customer = mongoOperation.findOne(new Query(where("customer").is(invoiceB2C.getBillToEnglish())), Customer.class);
        report.setBillToEnglish(invoiceB2C.getCustomerName());
        report.setCustomerVatNo("No Customer details");
        report.setInvoiceDate(invoiceB2C.getDateTime());
        report.setInvoiceNumber(invoiceB2C.getInvoiceNumber());
        report.setTotalExcludingVAT(invoiceB2C.getTotalExcludingVAT());
        //report.setTotalTaxableAmount(invoice.getTotalTaxableAmount());
        report.setTotalVat(invoiceB2C.getTotalVat());
       report.setTotalAmountDue(invoiceB2C.getTotalAmountDue());
//        report.setTotalNetAmount(invoiceB2C.getTotalNetAmount());
//        report.setLocation(invoiceB2C.getLocation());
        return report;
    }

//    private Report setCreditInvoiceToReport(CreditInvoice creditInvoice) {
//        Report report = new Report();
////        Customer customer = mongoOperation.findById(creditInvoice.getBillTo(), Customer.class);
////        Customer customer = mongoOperation.findOne(new Query(where("customer").is(creditInvoice.getCustomerName())), Customer.class);
//        report.setCustomerName(creditInvoice.getCustomer().getCustomer());
//        report.setCustomerVatNo(creditInvoice.getCustomer().getVatNumber_Customer());
//        report.setInvoiceDate(creditInvoice.getDateTime());
//        report.setInvoiceNumber(creditInvoice.getInvoiceNumber());
//        report.setTotalExcludingVAT(creditInvoice.getTotalExcludingVAT());
//        report.setTotalVat(creditInvoice.getTotalVat());
//        report.setTotalNetAmount(creditInvoice.getTotalNetAmount());
//        report.setLocation(creditInvoice.getLocation());
//        return report;
//    }
//
//    private Report setDebitInvoiceToReport(DebitInvoice debitInvoice) {
//        Report report = new Report();
////        Customer customer = mongoOperation.findOne(new Query(where("customer").is(debitInvoice.getCustomerName())), Customer.class);
//        report.setCustomerName(debitInvoice.getCustomer().getCustomer());
//        report.setCustomerVatNo(debitInvoice.getCustomer().getVatNumber_Customer());
//        report.setInvoiceDate(debitInvoice.getDateTime());
//        report.setInvoiceNumber(debitInvoice.getInvoiceNumber());
//        report.setTotalExcludingVAT(debitInvoice.getTotalExcludingVAT());
//        //report.setTotalTaxableAmount(debitInvoice.getTotalTaxableAmount());
//        report.setTotalVat(debitInvoice.getTotalVat());
//        //report.setTotalAmountDue(debitInvoice.getTotalAmountDue());
//        report.setTotalNetAmount(debitInvoice.getTotalNetAmount());
//        report.setLocation(debitInvoice.getLocation());
//        return report;
//    }


}
