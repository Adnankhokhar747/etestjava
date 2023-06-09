package com.example.einvoicing.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import com.example.einvoicing.entities.Invoice;
import com.example.einvoicing.repositories.InvoiceRepo;
import com.example.einvoicing.util.zakat_qr_code.tag.*;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QRCodeGenerator {

    @Autowired
    static
    InvoiceRepo invoiceRepo;

    public static void generateQRCodeImage(String text, int width, int height, String filePath)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

    }

    public static String generateTextQRCode(String invoiceNo) {
        Invoice invoice = invoiceRepo.getInvoiceByInvoiceNumber(invoiceNo);
        String text = invoice.getCustomerName()+"-"+invoice.getTotalExcludingVAT()+"-"+invoice.getTotalAmountDue();
        return text;
    }

    public static byte[] getQRCodeImage(String text) {
        try{
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);
            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
            byte[] pngData = pngOutputStream.toByteArray();
            return pngData;
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            return null;
        }
    }

    public static byte[] getQRCodeImageZatca(String sellerName, String vatRegNum, String timeStamp , String invoiceTotal, String vatTotal ) {
        try{

            String qrBarcodeHash = QRBarcodeEncoder.encode(
                    new Seller(sellerName),
                    new TaxNumber(vatRegNum),
                    new InvoiceDate(timeStamp),
                    new InvoiceTotalAmount(invoiceTotal),
                    new InvoiceTaxAmount(vatTotal)
            );

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(qrBarcodeHash, BarcodeFormat.QR_CODE, 200, 200);
            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
            byte[] pngData = pngOutputStream.toByteArray();
            return pngData;
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            return null;
        }
    }

    //test QR
    public static byte[] getQRCodeImageZatcaTest(String qrBarcodeHash) {
        try{
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(qrBarcodeHash, BarcodeFormat.QR_CODE, 200, 200);
            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
            byte[] pngData = pngOutputStream.toByteArray();
            return pngData;
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            return null;
        }
    }

}