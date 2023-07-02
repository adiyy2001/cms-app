package com.Castro.cmsApp.cars.controllers;

import com.Castro.cmsApp.cars.models.VehicleHire;
import com.Castro.cmsApp.cars.services.VehicleHireService;
import com.Castro.cmsApp.cars.services.VehicleService;
import com.Castro.cmsApp.parameters.services.ClientService;
import com.Castro.cmsApp.parameters.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VehicleHireController {
	
	@Autowired private VehicleHireService vehicleHireService;
	@Autowired private ClientService clientService;
	@Autowired private LocationService locationService;
	@Autowired private VehicleService vehicleService;

public VehicleHireController(
        VehicleHireService vehicleHireService,
        ClientService clientService,
        LocationService locationService,
        VehicleService vehicleService
) {
    this.vehicleHireService = vehicleHireService;
    this.clientService = clientService;
    this.locationService = locationService;
    this.vehicleService = vehicleService;
}

	public Model addModelAttributes(Model model){
		model.addAttribute("clients", clientService.findAll());
		model.addAttribute("locations", locationService.findAll());
		model.addAttribute("vehicles", vehicleService.findAll());
		return model;
	}

	//Get All VehicleHires
	@GetMapping("/cars/hires")
	public String findAll(Model model){		
		model.addAttribute("hires", vehicleHireService.findAll());
		return "/cars/hires";
	}

	@GetMapping("/cars/hireAdd")
	public String addHire(Model model){
		addModelAttributes(model);
		return "/cars/hireAdd";
	}

	@GetMapping("/cars/hire/{op}/{id}")
	public String editHire(Model model, @PathVariable Integer id, @PathVariable String op){
		VehicleHire hire = vehicleHireService.findById(id);
		model.addAttribute("hire", hire);
		addModelAttributes(model);
		return "/cars/hire"+op;
	}

	//Add VehicleHire
	@PostMapping("/cars/hires")
	public String addNew(VehicleHire vehicleHire) {
		vehicleHireService.save(vehicleHire);
		return "redirect:/cars/hires";
	}
	
	@RequestMapping(value="cars/hire/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Integer id) {
		vehicleHireService.delete(id);
		return "redirect:/cars/hires";
	}

}
