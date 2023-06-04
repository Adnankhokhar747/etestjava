package com.example.einvoicing.helpers;

import com.example.einvoicing.entities.Logs;
import com.example.einvoicing.repositories.LogsRepo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogsHelper {
    private Gson gson = new Gson();
    @Autowired
    LogsRepo logsRepo;

    public String saveLogs(Logs logs){
        try{
            logsRepo.save(logs);
            return gson.toJson(logs);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return gson.toJson(e.getMessage());
        }
    }

}
