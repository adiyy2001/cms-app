package com.Castro.cmsApp.cars.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.Castro.cmsApp.cars.models.VehicleStatus;
import com.Castro.cmsApp.cars.services.VehicleStatusService;

import java.util.Optional;

@Controller
public class VehicleStatusController {
	
	@Autowired private VehicleStatusService vehicleStatusService;
	
	public VehicleStatusController(VehicleStatusService vehicleStatusService) {
		this.vehicleStatusService = vehicleStatusService;
	}

	//Get All VehicleStatuss
	@GetMapping("/cars/vehicleStatuses")
	public String findAll(Model model){		
		model.addAttribute("vehicleStatuses", vehicleStatusService.findAll());
		return "/cars/vehicleStatuses";
	}	
	
	@RequestMapping("/cars/vehicleStatus/{id}")
	@ResponseBody
	public Optional<VehicleStatus> findById(@PathVariable Integer id)
	{
		return vehicleStatusService.findById(id);
	}
	
	//Add VehicleStatus
	@PostMapping(value="/cars/vehicleStatuses")
	public String addNew(VehicleStatus vehicleStatus) {
		vehicleStatusService.save(vehicleStatus);
		return "redirect:/cars/vehicleStatuses";
	}	

	@RequestMapping(value="cars/vehicleStatus/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Integer id) {
		vehicleStatusService.delete(id);
		return "redirect:/cars/vehicleStatuses";
	}
}
