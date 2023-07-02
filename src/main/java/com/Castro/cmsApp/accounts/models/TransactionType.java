package com.Castro.cmsApp.accounts.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.Castro.cmsApp.parameters.models.CommonObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class TransactionType extends CommonObject {
}
