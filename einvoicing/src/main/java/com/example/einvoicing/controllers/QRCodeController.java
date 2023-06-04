package com.example.einvoicing.controllers;

import com.example.einvoicing.util.QRCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class QRCodeController {

    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/QRCode.png";

    @GetMapping(value = "/genrateAndDownloadQRCode/{codeText}/{width}/{height}")
    public void download(
            @PathVariable("codeText") String codeText,
            @PathVariable("width") Integer width,
            @PathVariable("height") Integer height)
            throws Exception {
        QRCodeGenerator.generateQRCodeImage(codeText, width, height, QR_CODE_IMAGE_PATH);
    }

    @GetMapping(value = "/generateQRCodeImageTest/{codeText}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> generateQRCodeImageTest(@PathVariable("codeText") String codeText) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(QRCodeGenerator.getQRCodeImageZatcaTest(codeText));
    }

    @GetMapping(value = "/generateQRCodeImage/{codeText}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> generateQRCodeImage(@PathVariable("codeText") String codeText) {
//        String[] params = StringUtils.split(codeText, ",");
        String[] params = codeText.split("&");
        String sellerName =  params[0];
        String vatRegNo   =  params[1];
        String timeStamp  =  params[2];
        String invoiceTotal =  params[3];
        String vatTotal =  params[4];
        return ResponseEntity.status(HttpStatus.OK).body(QRCodeGenerator.getQRCodeImageZatca(sellerName,vatRegNo, timeStamp, invoiceTotal, vatTotal ));
}

    @GetMapping(value = "/genrateQRCode/{invoiceNo}" , produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> generateQRCode(@PathVariable("invoiceNo") String invoiceNo) {
       String text = QRCodeGenerator.generateTextQRCode(invoiceNo);
        return ResponseEntity.status(HttpStatus.OK).body(QRCodeGenerator.getQRCodeImage(text));
    }

}