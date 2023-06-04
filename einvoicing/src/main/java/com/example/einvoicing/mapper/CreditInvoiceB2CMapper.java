package com.example.einvoicing.mapper;

import com.example.einvoicing.dto.InvoiceDTO;
import com.example.einvoicing.dto.LineItemDTO;
import com.example.einvoicing.entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CreditInvoiceB2CMapper {
    public static InvoiceDTO mapListToInvoiceDTO(CreditInvoiceB2C invoice){
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.id = invoice.getId();
        invoiceDTO.serialNo = invoice.getSerialNo();
        invoiceDTO.paymentType = invoice.getPaymentType();
        invoiceDTO.type = invoice.getType();
        invoiceDTO.supplyDate = invoice.getSupplyDate();
        invoiceDTO.dateTime = invoice.getDateTime();
        invoiceDTO.companyID = invoice.getCompanyID();
        invoiceDTO.invoiceName = invoice.getInvoiceName();
        invoiceDTO.billTo = invoice.getBillTo();
        invoiceDTO.invoiceNumber = invoice.getInvoiceNumber();
        invoiceDTO.invoiceDate = invoice.getInvoiceDate();
        invoiceDTO.paymentDue = invoice.getPaymentDue();
        invoiceDTO.total = invoice.getTotal();
        invoiceDTO.discount = invoice.getDiscount();
        invoiceDTO.totalTaxableAmount = invoice.getTotalTaxableAmount();
        invoiceDTO.totalVat = invoice.getTotalVat();
        invoiceDTO.totalAmountDue = invoice.getTotalAmountDue();
        invoiceDTO.userId = invoice.getUserId();
        invoiceDTO.currency = invoice.getCurrency();
        invoiceDTO.customerName = invoice.getCustomerName();
        invoiceDTO.totalExcludingVAT = invoice.getTotalExcludingVAT();
        invoiceDTO.serviceLocation = invoice.getServiceLocation();
        invoiceDTO.totalNetAmount = invoice.getTotalNetAmount();
        invoiceDTO.location = invoice.getLocation();
        invoiceDTO.amountSAR = invoice.getAmountSAR();
        //set reamining here
        invoiceDTO.lineItems = invoice.getLineItemList().stream().map(CreditInvoiceB2CMapper::mapToLineItemDTO).collect(Collectors.toList());
        return invoiceDTO;
    }

    public static LineItemDTO mapToLineItemDTO(CreditB2CLineItem lineItem){
        LineItemDTO lineItemDTO = new LineItemDTO();
        lineItemDTO.id = lineItem.getId();
        lineItemDTO.itemSubTotal = lineItem.getItemSubTotal();
        lineItemDTO.quantity = lineItem.getQuantity();
        lineItemDTO.taxableAmount = lineItem.getTaxableAmount();
        lineItemDTO.taxAmount = lineItem.getTaxAmount();
        lineItemDTO.amountBeforeTax = lineItem.getAmountBeforeTax();
        lineItemDTO.description = lineItem.getDescription();
        lineItemDTO.discount = lineItem.getDiscount();
        lineItemDTO.price = lineItem.getPrice();
        lineItemDTO.vat = lineItem.getVat();
        lineItemDTO.invoice = lineItem.getPrice();
        lineItemDTO.productId = lineItem.getProductId();
        lineItemDTO.productName = lineItem.getProductName();
        //set reamining here
        return lineItemDTO;
    }

    public static InvoiceDTO mapToInvoiceDTO(CreditInvoiceB2C invoice) {
        List<CreditInvoiceB2C> invoiceList = new ArrayList<>();
        invoiceList.add(invoice);
        List<InvoiceDTO> collect = invoiceList.stream().map(CreditInvoiceB2CMapper::mapListToInvoiceDTO).collect(Collectors.toList());
        return collect.get(0);
    }
}
