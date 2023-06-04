package com.example.einvoicing.helpers;

import com.example.einvoicing.entities.Company;
import com.example.einvoicing.entities.ErrorCustom;
import com.example.einvoicing.entities.Login;
import com.example.einvoicing.entities.User;
import com.example.einvoicing.repositories.CompanyRepo;
import com.example.einvoicing.repositories.UserRepo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Component
public class LoginHelper {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CompanyRepo companyRepo;
    private Gson gson = new Gson();

    public String signIn(Login login)  {
        ErrorCustom error = new ErrorCustom();
        String jsonError;
        try {
            List<Company> companyList = new ArrayList<>();
            List<User> userList = new ArrayList<>();
//            logger.info("Inside SignIn for: " + login.getEmail());
            Company loginCompany = getLoginCompany(login);
            if (loginCompany != null) {
//                if(login.getEmail().equals(loginCompany.getEmail()))
//                    saveCompanyTokenAndEmailDefault(loginCompany);
//                if(login.getEmail().equals(loginCompany.getEmail1()))
//                    saveCompanyTokenAndEmailUser1(loginCompany);
                companyList.add(loginCompany);
//                companyList.add(companyHelper.getCompanyArabic(loginCompany));
//                logsHelper.save(new Logs("SingIn request for " + loginCompany.getCompanyName(), "A taken has been sent to " + loginCompany.getEmail()));
//                logger.info("SingIn request for " + loginCompany.getCompanyName());
                return gson.toJson(companyList);
            }
            User savedUser = getLoginUser(login);
            if (savedUser != null) {
//                saveTokenAndEmail(savedUser);
                userList.add(savedUser);
//                logsHelper.save(new Logs("SingIn request for " + savedUser.getName(), " A taken has been sent to " + savedUser.getEmail()));
//                logger.info("SingIn request for " + savedUser.getName());
                return gson.toJson(userList);
            } else
                error.setErrorStatus("Error");
                error.setError("Invalid Credentials");
                jsonError = gson.toJson(error);
                return jsonError;
        }catch(Exception ex)
        {
            error.setErrorStatus("Error");
            error.setError("Erros in signIn");
            jsonError = gson.toJson(error);
            return jsonError;
        }
    }

    private Company getLoginCompany(Login login) {

        Company loginCompany = companyRepo.getUserByEmailAndPassword(login.getEmail(), login.getPassword());
        return loginCompany;
    }
    private User getLoginUser(Login login) {
        User user = userRepo.getUserByEmailAndPassword(login.getEmail(), login.getPassword());
        return user;
    }

    //User
//    private void saveTokenAndEmail(User savedUser) {
//        // Do not send Token to user's email if two factor authentication is activated.
//        if(savedUser.isTwoFactorAuthentication()) return;
//        String randomNumber = Utility.getRandomNumber();
//        savedUser.setLoginToken(randomNumber);
//        userHelper.updateUserForToken(savedUser);
//        emailSender.sendEmail(savedUser.getEmail(), "Gofatoorah Login Verification", "Login Token :" + randomNumber);
//        logsHelper.save(new Logs("User Token sent", savedUser.getEmail() + "Gofatoorah Verification Code has been sent"));
//    }
//
//    //CompanyXML
//    private void saveCompanyTokenAndEmailDefault(Company company) {
//        // Do not send Token to user's email if two factor authentication is activated.
//        if(company.isTwoFactorAuthentication()) return;
//        String randomNumber = Utility.getRandomNumber();
//        company.setLoginToken(randomNumber);
//        companyHelper.updateCompanyForToken(company);
//        emailSender.sendEmail(company.getEmail(), "Gofatoorah Login Verification", "Login Token :" + randomNumber);
//    }
//
//    //for User1 on company
//    private void saveCompanyTokenAndEmailUser1(Company company) {
//        // Do not send Token to user's email if two factor authentication is activated.
//        if(company.isTwoFactorAuthentication()) return;
//        String randomNumber = Utility.getRandomNumber();
//        company.setLoginToken1(randomNumber);
//        companyHelper.updateCompanyForToken(company);
//        emailSender.sendEmail(company.getEmail1(), "Gofatoorah Login Verification", "Login Token :" + randomNumber);
//    }
//
//    public String getLoginToken(String email, String token) {
//        List<Company> companyList = new ArrayList<>();
//        List<User> userList = new ArrayList<>();
//        User user = mongoOperation.findOne(new Query(Criteria.where("email").is(email)), User.class);
//        if (user != null && token.equals(user.getLoginToken())) {
//            userList.add(user);
//            //ToDO arabic
//            logsHelper.save(new Logs("SingIn token validation for "+user.getName(),"Token Authenticated against "+user.getEmail()));
//            logger.info("SingIn token validation for "+user.getName());
//            return gson.toJson(userList);
//        }
//        Company company = mongoOperation.findOne(new Query(new Criteria().orOperator(Criteria.where("email").is(email),
//                Criteria.where("email1").is(email))), Company.class);
//        if (company != null && (token.equals(company.getLoginToken()) || token.equals(company.getLoginToken1()))) {
//            companyList.add(company);
//            companyList.add(companyHelper.getCompanyArabic(company));
//            logger.info("SingIn token validation for "+company.getCompanyName());
//            logsHelper.save(new Logs("SingIn token validation for "+company.getCompanyName(),"Token Authenticated against "+company.getEmail()));
//            return gson.toJson(companyList);
//        } else return gson.toJson("Wrong email/token entered");
//    }
//
//    //forget password
//    public String forgetPassword(Login login) {
//        Criteria criteria = new Criteria();
//        criteria.orOperator(Criteria.where("email").is(login.getEmail()), Criteria.where("email1").is(login.getEmail()));
//        Company loginCompany = mongoOperation.findOne(new Query(criteria), Company.class);
//        if (loginCompany != null) {
//            if(login.getEmail().equals(loginCompany.getEmail()))
//                saveCompanyTokenAndEmailDefault(loginCompany);
//            else if(login.getEmail().equals(loginCompany.getEmail1()))
//                saveCompanyTokenAndEmailUser1(loginCompany);
//            return gson.toJson("A code has been sent to your email.");
//        }
//        User savedUser = mongoOperation.findOne(new Query(Criteria.where("email").is(login.getEmail())), User.class);
//        if (savedUser != null) {
//            saveTokenAndEmail(savedUser);
//            return gson.toJson("A code has been sent to your email.");
//        } else
//            return gson.toJson("Invalid Email address");
//    }
//
//    //update password
//    public String validateUpdatePassword(Login login) {
//        Criteria criteria = new Criteria();
//        criteria.orOperator(Criteria.where("email").is(login.getEmail()).and("loginToken").is(login.getPassword()),
//                Criteria.where("email1").is(login.getEmail()).and("loginToken1").is(login.getPassword()));
//        Company loginCompany = mongoOperation.findOne(new Query(criteria), Company.class);
//        if (loginCompany != null) {
//            validated = true;
//            logsHelper.save(new Logs("Forget Password request validated for "+loginCompany.getCompanyName()," against "+loginCompany.getEmail()));
//            return gson.toJson("Validation Successfull");
//        }
//        User savedUser = mongoOperation.findOne(new Query(Criteria.where("email").is(login.getEmail()).and("loginToken").is(login.getPassword())), User.class);
//        if (savedUser != null) {
//            validated = true;
//            logsHelper.save(new Logs("Forget Password request validated for "+savedUser.getName()," against "+savedUser.getEmail()));
//            return gson.toJson("Validation Successfull");
//        } else
//            return gson.toJson("Invalid Token");
//    }
//
//    //update password
//    public String resetPassword(Login login) {
//        Criteria criteria = new Criteria();
//        criteria.orOperator(Criteria.where("email").is(login.getEmail()),
//                Criteria.where("email1").is(login.getEmail()));
//        Company loginCompany = mongoOperation.findOne(new Query(criteria), Company.class);
//        if (loginCompany != null && validated) {
//            logsHelper.save(new Logs("Forget Password request for "+loginCompany.getCompanyName()," and token has been sent to "+loginCompany.getEmail()));
//            logger.info("Password reset request for "+loginCompany.getCompanyName());
//            if(login.getEmail().equals(loginCompany.getEmail()))
//                return companyHelper.resetCompanyPassword(login);
//            else if(login.getEmail().equals(loginCompany.getEmail1()))
//                return companyHelper.resetCompanyPassword1(login);
//        }
//        User savedUser = mongoOperation.findOne(new Query(Criteria.where("email").is(login.getEmail())), User.class);
//        if (savedUser != null && validated) {
//            logsHelper.save(new Logs("Forget Password request for "+Utility.getUserName(savedUser.getUserId(), mongoOperation)," nd token has been sent to "+savedUser.getEmail()));
//            logger.info("Password reset request for "+savedUser.getName());
//            return userHelper.resetUserPassword(login);
//        } else
//            return gson.toJson("Invalid Credentials");
//    }

}
