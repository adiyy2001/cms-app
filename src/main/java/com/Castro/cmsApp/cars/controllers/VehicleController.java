package com.Castro.cmsApp.cars.controllers;

import com.Castro.cmsApp.cars.models.Vehicle;
import com.Castro.cmsApp.cars.services.*;
import com.Castro.cmsApp.hr.services.EmployeeService;
import com.Castro.cmsApp.parameters.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VehicleController {
	
	final private VehicleService vehicleService;
	final private VehicleTypeService vehicleTypeService;
	final private VehicleMakeService vehicleMakeService;
	final private VehicleModelService vehicleModelService;
	final private LocationService locationService;
	final private EmployeeService employeeService ;
	final private VehicleStatusService vehicleStatusService;

    @Autowired
    public VehicleController(VehicleService vehicleService,
                             VehicleTypeService vehicleTypeService,
                             VehicleMakeService vehicleMakeService,
                             VehicleModelService vehicleModelService,
                             LocationService locationService,
                             EmployeeService employeeService,
                             VehicleStatusService vehicleStatusService) {
        this.vehicleService = vehicleService;
        this.vehicleTypeService = vehicleTypeService;
        this.vehicleMakeService = vehicleMakeService;
        this.vehicleModelService = vehicleModelService;
        this.locationService = locationService;
        this.employeeService = employeeService;
        this.vehicleStatusService = vehicleStatusService;
    }

	public Model addModelAttributes(Model model){
		model.addAttribute("vehicles", vehicleService.findAll());
		model.addAttribute("vehicleTypes", vehicleTypeService.findAll());
		model.addAttribute("vehicleModels", vehicleModelService.findAll());
		model.addAttribute("vehicleMakes", vehicleMakeService.findAll());
		model.addAttribute("locations", locationService.findAll());
		model.addAttribute("employees", employeeService.findAll());
		model.addAttribute("vehicleStatuses", vehicleStatusService.findAll());
		return model;
	}

	//Get All Vehicles
	@GetMapping("/cars/vehicles")
	public String findAll(Model model){		
		addModelAttributes(model);
		return "/cars/vehicles";
	}

	@GetMapping("/cars/vehicleAdd")
	public String addVehicle(Model model){
		addModelAttributes(model);
		return "cars/vehicleAdd";
	}

	//The op parameter is either Edit or Details
	@GetMapping("/cars/vehicle/{op}/{id}")
	public String editVehicle(@PathVariable Integer id, @PathVariable String op, Model model){
		Vehicle vehicle = vehicleService.findById(id);
		model.addAttribute("vehicle", vehicle);
		addModelAttributes(model);
		return "/cars/vehicle"+ op; //returns vehicleEdit or vehicleDetails
	}

	//Add Vehicle
	@PostMapping("/cars/vehicles")
	public String addNew(Vehicle vehicle) {
		vehicleService.save(vehicle);
		return "redirect:/cars/vehicles";
	}	

	@RequestMapping(value="/cars/vehicle/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Integer id) {
		vehicleService.delete(id);
		return "redirect:/cars/vehicles";
	}
}
