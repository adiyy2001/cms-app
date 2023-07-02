package com.Castro.cmsApp.cars.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.Castro.cmsApp.cars.models.VehicleMake;
import com.Castro.cmsApp.cars.services.VehicleMakeService;

import java.util.Optional;

@Controller
public class VehicleMakeController {
	
	@Autowired private VehicleMakeService vehicleMakeService;
	
	public VehicleMakeController(VehicleMakeService vehicleMakeService) {
		this.vehicleMakeService = vehicleMakeService;
	}

	//Get All VehicleMakes
	@GetMapping("cars/vehicleMakes")
	public String findAll(Model model){		
		model.addAttribute("vehicleMakes", vehicleMakeService.findAll());
		return "/cars/vehicleMakes";
	}	
	
	@RequestMapping("/cars/vehicleMakes/{id}")
	@ResponseBody
	public Optional<VehicleMake> findById(@PathVariable Integer id)
	{
		return vehicleMakeService.findById(id);
	}
	
	//Add VehicleMake
	@PostMapping(value="/cars/vehicleMakes")
	public String addNew(VehicleMake vehicleMake) {
		vehicleMakeService.save(vehicleMake);
		return "redirect:/cars/vehicleMakes";
	}
	
	@RequestMapping(value="vehicleMake/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer id) {
		vehicleMakeService.delete(id);
		return "redirect:/cars/vehicleMakes";
	}
}
