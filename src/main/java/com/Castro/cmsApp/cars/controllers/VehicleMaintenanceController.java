package com.Castro.cmsApp.cars.controllers;

import com.Castro.cmsApp.cars.models.VehicleMaintenance;
import com.Castro.cmsApp.cars.services.VehicleMaintenanceService;
import com.Castro.cmsApp.cars.services.VehicleService;
import com.Castro.cmsApp.parameters.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VehicleMaintenanceController {

	@Autowired
	private VehicleMaintenanceService vehicleMaintenanceService;
	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private SupplierService supplierService;

	@Autowired
	public VehicleMaintenanceController(VehicleMaintenanceService vehicleMaintenanceService,
			VehicleService vehicleService,
			SupplierService supplierService) {
		this.vehicleMaintenanceService = vehicleMaintenanceService;
		this.vehicleService = vehicleService;
		this.supplierService = supplierService;
	}

	public Model addModelAttributes(Model model) {
		model.addAttribute("vehicles", vehicleService.findAll());
		model.addAttribute("suppliers", supplierService.findAll());
		return model;
	}

	// Get All VehicleMaintenances
	@GetMapping("/cars/maintenances")
	public String findAll(Model model) {
		model.addAttribute("maintenances", vehicleMaintenanceService.findAll());
		return "/cars/maintenances";
	}

	@GetMapping("/cars/maintenanceAdd")
	public String addMaintenance(Model model) {
		addModelAttributes(model);
		return "/cars/maintenanceAdd";
	}

	@GetMapping("/cars/maintenance/{op}/{id}")
	public String editMaintenance(Model model, @PathVariable Integer id, @PathVariable String op) {
		VehicleMaintenance maintenance = vehicleMaintenanceService.findById(id);
		model.addAttribute("maintenance", maintenance);
		addModelAttributes(model);
		return "/cars/maintenance" + op;
	}

	// Add VehicleMaintenance
	@PostMapping("/cars/maintenances")
	public String addNew(VehicleMaintenance vehicleMaintenance) {
		vehicleMaintenanceService.save(vehicleMaintenance);
		return "redirect:/cars/maintenances";
	}

	@RequestMapping(value = "cars/maintenance/delete/{id}", method = { RequestMethod.DELETE, RequestMethod.GET })
	public String delete(@PathVariable Integer id) {
		vehicleMaintenanceService.delete(id);
		return "redirect:/cars/maintenances";
	}

}
