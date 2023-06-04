package com.example.einvoicing.util;

import com.example.einvoicing.entities.Company;
import com.example.einvoicing.entities.User;
import com.example.einvoicing.repositories.CompanyRepo;
import com.example.einvoicing.repositories.UserRepo;
import com.posadskiy.currencyconverter.CurrencyConverter;
import com.posadskiy.currencyconverter.config.ConfigBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class Utility {

    @Autowired
    EmailSender emailSender;
    @Autowired
    static
    CompanyRepo companyRepo;
    @Autowired
    static
    UserRepo userRepo;
    private static String hexIP = null;
    private static final java.security.SecureRandom SEEDER = new java.security.SecureRandom();
    private final Logger logger = LoggerFactory.getLogger(Utility.class);

    private static byte[] getSHA(String text) throws NoSuchAlgorithmException
    {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(text.getBytes(StandardCharsets.UTF_8));
    }

    public static String encrypt(String text) throws NoSuchAlgorithmException {
        byte[] hash = getSHA(text);
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 32)
        {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }

    private static int getInt(byte[] bytes)
    {
        int i = 0;
        int j = 24;
        for (int k = 0; j >= 0; k++)
        {
            int l = bytes[k] & 0xff;
            i += l << j;
            j -= 8;
        }
        return i;
    }

    private static String hexFormat(int i, int j)
    {
        String s = Integer.toHexString(i);
        return padHex(s, j) + s;
    }

    private static String padHex(String s, int i)
    {
        StringBuffer tmpBuffer = new StringBuffer();
        if (s.length() < i)
        {
            for (int j = 0; j < i - s.length(); j++)
            {
                tmpBuffer.append('0');
            }
        }
        return tmpBuffer.toString();
    }

    public static String getRandomNumber(){
        Random rand = new Random();
        String random = String.valueOf(rand.nextInt(10000));
        return random;
    }

    public static String getUserName(String id){
        User user = userRepo.getById(id);
        if(user == null)
            user = userRepo.getUserByUserID(id);
        if(user != null)
            return user.getName();
        else
            return "No user found";
    }

    public static String getCompanyName(String companyID) {
        Company company = companyRepo.getCompanyByCompanyID(companyID);
        if(company != null)
            return company.getCompanyName();
        else
            return "No company found";
    }

    public static int getAttachedNo(String invoiceNo, String format) {
        int num = 1;
        String[] inv = StringUtils.split(invoiceNo, format);
        num = Integer.parseInt(inv[inv.length - 1]);
        return num;
    }

    public static String[] getSplitString(String value) {
        String[] inv = StringUtils.split(value, " ");
        return inv;
    }

    public void sendEmailToTeam(String subject, String message){
//        emailSender.sendEmail("junaidp@gmail.com", subject, message);
//        emailSender.sendEmail("amoqeet43@gmail.com", subject, message);
////        emailSender.sendEmail("mfaheempiracha@gmail.com", subject, message);
//        emailSender.sendEmail("abdurrafe7211@gmail.com", subject, message);
//        emailSender.sendEmail("adnankhokhar747@gmail.com", subject, message);
    }

    public static boolean isNumeric(String text){
        try{
            int num = Integer.parseInt(text);
            return true;
        }catch(Exception ex){
            return false;
        }
    }

    public String getCurrencyRateSAR(String currency){
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
            logger.warn("Error in getting currency rate:" + ex.getMessage());
            return "";
        }
    }

    public static String getDate(String date) throws ParseException {
        String []time= date.split(" ");
        return time[0];
    }

    public static String getTime(String date) throws ParseException {
          String []time= date.split(" ");
        return time[1];
    }

//    public static void updateInvoiceXML() throws IOException {
//        Path path = Paths.get(Constants.INVOICE_XML_PATH);
//        List<String> fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
//        for (int i = 0; i < fileContent.size(); i++) {
//            if (fileContent.get(i).equals("<Invoice>")) {
//                fileContent.set(i, Constants.INVOICE_XML_ATTRIBUTES);
//                fileContent.remove(i-1);
//                break;
//            }
//        }
//        Files.write(path, fileContent, StandardCharsets.UTF_8);
//    }

    public boolean emailExists(String email){
        Company company = companyRepo.getCompanyByEmail(email);
        if(company != null)
            return true;
        User user = userRepo.getUserByEmail(email);
       if(user != null)
           return true;
       else
            return false;
    }

    public String convertStringDateToAnotherStringDate(String stringdate, String stringdateformat, String returndateformat){

        try {
            Date date = new SimpleDateFormat(stringdateformat).parse(stringdate);
            String returndate = new SimpleDateFormat(returndateformat).format(date);
            return returndate;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return e.getMessage();
        }

    }

}

