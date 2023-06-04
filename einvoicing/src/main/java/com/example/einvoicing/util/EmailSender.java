package com.example.einvoicing.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class EmailSender {
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    public void sendEmail(String toAddress, String subject, String emailBody){
//        try {
//            SimpleMailMessage msg = new SimpleMailMessage();
//            msg.setTo(toAddress);
//            msg.setSubject(subject);
//            msg.setText(emailBody);
//            mailSender.send(msg);
//            System.out.println("Email Sent to: " + toAddress);
//        }catch(Exception ex){
//            System.out.println("Error " +ex.getMessage());
//        }
//    }
//
//    @Bean
//    public JavaMailSender getJavaMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("smtp.gmail.com");
//        mailSender.setPort(587);
//        //ya update kr ok
//        mailSender.setUsername("my.gmail@gmail.com");
//        mailSender.setPassword("password");
//        Properties props = mailSender.getJavaMailProperties();
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.debug", "true");
//        return mailSender;
//    }
//

}
