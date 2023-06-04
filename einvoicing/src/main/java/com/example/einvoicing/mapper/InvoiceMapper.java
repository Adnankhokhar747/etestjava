package com.example.einvoicing.mapper;

import com.example.einvoicing.entities.Invoice;
import com.example.einvoicing.dto.InvoiceDTO;
import com.example.einvoicing.entities.LineItem;
import com.example.einvoicing.dto.LineItemDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InvoiceMapper {

    public static InvoiceDTO mapListToInvoiceDTO(Invoice invoice){
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.id = invoice.getId();
        invoiceDTO.serialNo = invoice.getSerialNo();
        invoiceDTO.paymentType = invoice.getPaymentType();
        invoiceDTO.originalRefNo = invoice.getOriginalRefNo();
        invoiceDTO.type = invoice.getType();
        invoiceDTO.supplyDate = invoice.getSupplyDate();
        invoiceDTO.dateTime = invoice.getDateTime();
        invoiceDTO.companyID = invoice.getCompanyID();
        invoiceDTO.invoiceName = invoice.getInvoiceName();
        invoiceDTO.invoiceDescription = invoice.getInvoiceDescription();
        invoiceDTO.posoNumber = invoice.getPosoNumber();
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
        invoiceDTO.notes = invoice.getNotes();
        invoiceDTO.status = invoice.getStatus();
        invoiceDTO.currency = invoice.getCurrency();
        invoiceDTO.customerName = invoice.getCustomerName();
        invoiceDTO.totalExcludingVAT = invoice.getTotalExcludingVAT();
        invoiceDTO.serviceLocation = invoice.getServiceLocation();
        invoiceDTO.downPayment = invoice.getDownPayment();
        invoiceDTO.retention = invoice.getRetention();
        invoiceDTO.totalNetAmount = invoice.getTotalNetAmount();
        invoiceDTO.recordPayment = invoice.getRecordPayment();
        invoiceDTO.bankAccount = invoice.getBankAccount();
        invoiceDTO.referenceField = invoice.getReferenceField();
        invoiceDTO.projectCode = invoice.getProjectCode();
        invoiceDTO.location = invoice.getLocation();
        invoiceDTO.amountSAR = invoice.getAmountSAR();
        invoiceDTO.paymentTerm = invoice.getPaymentTerm();
        //set reamining here
        invoiceDTO.lineItems = invoice.getLineItemList().stream().map(InvoiceMapper::mapToLineItemDTO).collect(Collectors.toList());
        return invoiceDTO;
    }

    public static List<LineItemDTO> mapListLineItemDto(List<LineItem> lineItemList){
        List<LineItemDTO> lineItemDTOS = new ArrayList<>();
        for(LineItem lineItem : lineItemList)
            lineItemDTOS.add(mapToLineItemDTO(lineItem));
        return lineItemDTOS;
    }

    public static LineItemDTO mapToLineItemDTO(LineItem lineItem){
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

    public static InvoiceDTO mapToInvoiceDTO(Invoice invoice) {
        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoice);
        List<InvoiceDTO> collect = invoiceList.stream().map(InvoiceMapper::mapListToInvoiceDTO).collect(Collectors.toList());
        return collect.get(0);
    }
}
