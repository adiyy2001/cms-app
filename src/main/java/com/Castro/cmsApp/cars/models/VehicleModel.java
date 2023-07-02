package com.Castro.cmsApp.cars.models;

import com.Castro.cmsApp.parameters.models.CommonObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class VehicleModel extends CommonObject {

}
