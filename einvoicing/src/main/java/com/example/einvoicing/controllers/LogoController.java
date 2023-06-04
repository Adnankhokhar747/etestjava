package com.example.einvoicing.controllers;

import com.example.einvoicing.entities.Logo;
import com.example.einvoicing.helpers.LogoHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class LogoController {
    @Autowired
    private LogoHelper logoHelper;

    @PostMapping("/uploadLogo")
    public String uploadLogo(@RequestParam("file") MultipartFile file, @RequestParam("companyID") String companyID) {
        try {
            // Call the service method to handle logo upload
            logoHelper.uploadLogo(companyID, file);
            return "Logo uploaded successfully";
        } catch (Exception e) {
            return "Failed to upload logo: " + e.getMessage();
        }
    }

    @GetMapping("/getLogo")
    public ResponseEntity<Resource> getLogo(@RequestParam String companyID) {
        try {
            // Call the service method to retrieve the logo
            byte[] logoData = logoHelper.getLogo(companyID);

            if (logoData != null) {
                // Create a resource from the logo data
                ByteArrayResource resource = new ByteArrayResource(logoData);

                // Set the response headers
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG);
                headers.setContentLength(logoData.length);

                // Return the resource as a response entity
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .headers(headers)
                        .body(resource);
            } else {
                // Return a response indicating that the logo was not found
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

//    @GetMapping("/getLogo")
//    public ResponseEntity<byte[]> getLogo(@RequestParam("companyID") String companyID) {
//        try {
//            // Call the service method to retrieve the logo
//            byte[] logoData = logoHelper.getLogo(companyID);
//
//            // Set the response headers
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.IMAGE_JPEG);
//
//            // Return the logo data as a response entity
//            return new ResponseEntity<>(logoData, headers, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


}