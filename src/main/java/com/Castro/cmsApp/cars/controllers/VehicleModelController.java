package com.Castro.cmsApp.cars.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.Castro.cmsApp.cars.models.VehicleModel;
import com.Castro.cmsApp.cars.services.VehicleModelService;

import java.util.Optional;

@Controller
public class VehicleModelController {

	@Autowired private VehicleModelService vehicleModelService;
	

	public VehicleModelController(VehicleModelService vehicleModelService) {
		this.vehicleModelService = vehicleModelService;
	}
	//Get All VehicleModels
	@GetMapping("cars/vehicleModels")
	public String findAll(Model model){		
		model.addAttribute("vehicleModels", vehicleModelService.findAll());
		return "/cars/vehicleModels";
	}	
	
	@RequestMapping("/cars/vehicleModel/{id}")
	@ResponseBody
	public Optional<VehicleModel> findById(@PathVariable Integer id)
	{
		return vehicleModelService.findById(id);
	}
	
	//Add VehicleModel
	@PostMapping(value="/cars/vehicleModels")
	public String addNew(VehicleModel vehicleModel) {
		vehicleModelService.save(vehicleModel);
		return "redirect:/cars/vehicleModels";
	}
	
	@RequestMapping(value="vehicleModel/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Integer id) {
		vehicleModelService.delete(id);
		return "redirect:/cars/vehicleModels";
	}
}
