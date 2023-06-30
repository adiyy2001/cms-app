package com.Castro.cmsApp.parameters.controllers;

import com.Castro.cmsApp.hr.services.EmployeeService;
import com.Castro.cmsApp.parameters.models.Department;
import com.Castro.cmsApp.parameters.services.DepartmentService;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private EmployeeService employeeService;

    //Get All VehicleTypes
    @GetMapping("/parameters/departments")
    public String findAll(Model model){
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("employees", employeeService.findAll());
        return "/parameters/departments";
    }

    @RequestMapping("/parameters/departments/findById")
    @ResponseBody
    public Department findById(Integer id)
    {
        return departmentService.findById(id);
    }

    //Add Department
    @PostMapping(value="/parameters/departments/addNew")
    public String addNew(Department department) {
        departmentService.save(department);
        return "redirect:/parameters/departments";
    }

    @RequestMapping(value="/parameters/departments/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(Department department) {
        departmentService.save(department);
        return "redirect:/parameters/departments";
    }

    @RequestMapping(value="parameters/departments/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(Integer id) {
        departmentService.delete(id);
        return "redirect:/parameters/departments";
    }
}