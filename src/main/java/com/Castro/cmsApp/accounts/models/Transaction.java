package com.Castro.cmsApp.accounts.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.Castro.cmsApp.hr.models.Employee;
import com.Castro.cmsApp.parameters.models.Client;
import com.Castro.cmsApp.parameters.models.Contact;
import com.Castro.cmsApp.parameters.models.Supplier;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private double amount;
    private String purpose;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date transactionDate;

    @ManyToOne
    @JoinColumn(name="transactionstatusid", insertable=false, updatable=false)
    private TransactionStatus transactionStatus;
    private Integer transactionstatusid;

    @ManyToOne
    @JoinColumn(name="transactiontypeid", insertable=false, updatable=false)
    private TransactionType transactionType;
    private Integer transactiontypeid;

    @ManyToOne
    @JoinColumn(name="contactid", insertable=false, updatable=false)
    private Contact contact;
    private Integer contactid;

    @ManyToOne
    @JoinColumn(name="supplierid", insertable=false, updatable=false)
    private Supplier supplier;
    private Integer supplierid;

    @ManyToOne
    @JoinColumn(name="clientid", insertable=false, updatable=false)
    private Client client;
    private Integer clientid;

    @ManyToOne
    @JoinColumn(name="employeeid", insertable=false, updatable=false)
    private Employee approvedBy;
    private Integer employeeid;

    private String remarks;

}
