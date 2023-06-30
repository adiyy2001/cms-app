package com.Castro.cmsApp.cars.controllers;

import com.Castro.cmsApp.cars.models.VehicleMovement;
import com.Castro.cmsApp.cars.services.VehicleMovementService;
import com.Castro.cmsApp.cars.services.VehicleService;
import com.Castro.cmsApp.parameters.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VehicleMovementController {
	
	@Autowired private VehicleMovementService vehicleMovementService;
	@Autowired private LocationService locationService;
	@Autowired private VehicleService vehicleService;

	@Autowired
	public VehicleMovementController(VehicleMovementService vehicleMovementService,
	                                 LocationService locationService,
	                                 VehicleService vehicleService) {
		this.vehicleMovementService = vehicleMovementService;
		this.locationService = locationService;
		this.vehicleService = vehicleService;
	}

	public Model addModelAttributes(Model model){
		model.addAttribute("locations1", locationService.findAll());
		model.addAttribute("locations2", locationService.findAll());
		model.addAttribute("vehicles", vehicleService.findAll());
		return  model;
	}
	//Get All VehicleMovements
	@GetMapping("cars/movements")
	public String findAll(Model model){
		model.addAttribute("movements", vehicleMovementService.findAll());
		return "cars/movements";
	}

	@GetMapping("/cars/movementAdd")
	public String addMovement(Model model){
		addModelAttributes(model);
		return "/cars/movementAdd";
	}

	@GetMapping("/cars/movement/{op}/{id}")
	public String editMovement(Model model, @PathVariable Integer id, @PathVariable String op){
		VehicleMovement movement = vehicleMovementService.findById(id);
		model.addAttribute("movement", movement);
		addModelAttributes(model);
		return "/cars/movement"+op;
	}

	//Add VehicleMovement
	@PostMapping("/cars/movements")
	public String addNew(VehicleMovement vehicleMovement) {
		vehicleMovementService.save(vehicleMovement);
		return "redirect:/cars/movements";
	}
	
	@RequestMapping(value="/cars/movements/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Integer id) {
		vehicleMovementService.delete(id);
		return "redirect:/cars/movements";
	}


}
