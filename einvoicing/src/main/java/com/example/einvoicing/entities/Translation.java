package com.example.einvoicing.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Translation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String arabic;
    private String english;
//    private String loggedInID;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getArabic() {
        return arabic;
    }

    public void setArabic(String arabic) {
        this.arabic = arabic;
    }

//    public String getLoggedInID() {
//        return loggedInID;
//    }
//
//    public void setLoggedInID(String loggedInID) {
//        this.loggedInID = loggedInID;
//    }
}
