package com.example.einvoicing.helpers;

import com.example.einvoicing.entities.Logo;
import com.example.einvoicing.repositories.LogoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Component
public class LogoHelper {

    @Autowired
    private LogoRepo logoRepo;

    public void uploadLogo(String companyID, MultipartFile file) throws IOException {
        try {
            Logo logo = new Logo();
            logo.setCompanyID(companyID);
            logo.setData(file.getBytes());
            logoRepo.save(logo);
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public byte[] getLogo(String companyID) {
        Optional<Logo> logoOptional = logoRepo.findByCompanyID(companyID);

        if (logoOptional.isPresent()) {
            Logo logo = logoOptional.get();
            return logo.getData();
        }

        return null; // Handle the case when no logo is found for the given companyID
    }
}
