package com.example.einvoicing.controllers;

import com.example.einvoicing.util.Utility;
import com.google.gson.Gson;
import com.posadskiy.currencyconverter.CurrencyConverter;
import com.posadskiy.currencyconverter.config.ConfigBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class CurrencyController {

    @GetMapping("/getCurrencyRateSAR")
    public String getCurrencyRateSAR(@RequestParam String currency) {
        String currency1 = new Gson().toJson(getCurrencyRate(currency));
        if(currency.charAt(0) == '\"' && currency.charAt(currency.length() - 1) == '\"')
            currency = currency.replaceAll("^\"|\"$", "");
        Utility util = new Utility();
        return util.getCurrencyRateSAR(currency);
    }

    public String getCurrencyRate(String currency){
        try {
            final String CURRENCY_CONVERTER_API_API_KEY = "147a4faa04c94fd4aa4b0f10e83e8273"; //PURCHASED
            CurrencyConverter converter = new CurrencyConverter(
                    new ConfigBuilder()
                            .currencyConverterApiApiKey(CURRENCY_CONVERTER_API_API_KEY)
                            .build()
            );
            Double usdToEuroRate = converter.rate(currency, "SAR");
            return usdToEuroRate.toString();
        }catch(Exception ex){
            System.out.println("Error in getting currency rate:" + ex.getMessage());
            return "";
        }
    }


}
