package com.Castro.cmsApp.cars.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.Castro.cmsApp.cars.models.VehicleType;
import com.Castro.cmsApp.cars.services.VehicleTypeService;

import java.util.Optional;

@Controller
public class VehicleTypeController {

	@Autowired
	private VehicleTypeService vehicleTypeService;

	public VehicleTypeController(VehicleTypeService vehicleTypeService) {
		this.vehicleTypeService = vehicleTypeService;
	}

	// Get All VehicleTypes
	@GetMapping("/cars/vehicleTypes")
	public String findAll(Model model) {
		model.addAttribute("vehicleTypes", vehicleTypeService.findAll());
		return "/cars/vehicleTypes";
	}

	@RequestMapping("/cars/vehicleType/{id}")
	@ResponseBody
	public Optional<VehicleType> findById(@PathVariable Integer id) {
		return vehicleTypeService.findById(id);
	}

	// Add VehicleType
	@PostMapping(value = "/cars/vehicleTypes")
	public String addNew(VehicleType vehicleType) {
		vehicleTypeService.save(vehicleType);
		return "redirect:/cars/vehicleTypes";
	}

	@RequestMapping(value = "cars/vehicleType/delete/{id}", method = { RequestMethod.DELETE, RequestMethod.GET })
	public String delete(@PathVariable Integer id) {
		vehicleTypeService.delete(id);
		return "redirect:/cars/vehicleTypes";
	}

}
