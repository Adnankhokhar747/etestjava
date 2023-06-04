package com.example.einvoicing.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Settings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String companyID;
    private boolean approvalAsPerAssignedFinancialLimits;
    private boolean approvalAsPerRefortipLine;
    private boolean approvalBasedLocationOfSuperivisorProspectiveOfAmount;
    private boolean anyOneHavingRights;
    private boolean approvalBasedLocationOfSuperivisorAndFinancialLimits;

}
