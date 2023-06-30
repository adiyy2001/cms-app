package com.Castro.cmsApp.hr.controllers;

import com.Castro.cmsApp.hr.models.Employee;
import com.Castro.cmsApp.hr.models.EmployeeType;
import com.Castro.cmsApp.hr.models.JobTitle;
import com.Castro.cmsApp.hr.services.EmployeeService;
import com.Castro.cmsApp.hr.services.EmployeeTypeService;
import com.Castro.cmsApp.hr.services.JobTitleService;
import com.Castro.cmsApp.parameters.models.Country;
import com.Castro.cmsApp.parameters.models.State;
import com.Castro.cmsApp.parameters.services.CountryService;
import com.Castro.cmsApp.parameters.services.StateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("EmployeeController Tests")
class EmployeeControllerTests {

    @Mock
    private EmployeeService employeeService;

    @Mock
    private StateService stateService;

    @Mock
    private JobTitleService jobTitleService;

    @Mock
    private EmployeeTypeService employeeTypeService;

    @Mock
    private CountryService countryService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("addModelAttributes should add model attributes")
    void addModelAttributes_ShouldAddModelAttributes() {
        // Given
        Model model = mock(Model.class);
        List<Country> countries = new ArrayList<>();
        List<State> states = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        List<JobTitle> jobTitles = new ArrayList<>();
        List<EmployeeType> employeeTypes = new ArrayList<>();

        when(countryService.findAll()).thenReturn(countries);
        when(stateService.findAll()).thenReturn(states);
        when(employeeService.findAll()).thenReturn(employees);
        when(jobTitleService.findAll()).thenReturn(jobTitles);
        when(employeeTypeService.findAll()).thenReturn(employeeTypes);

        // When
        Model result = employeeController.addModelAttributes(model);

        // Then
        assertEquals(model, result);
    }

    @Test
    @DisplayName("findAll should return the employees view")
    void findAll_ShouldReturnEmployeesView() {
        // Given
        Model model = mock(Model.class);

        // When
        String result = employeeController.findAll(model);

        // Then
        assertEquals("/hr/employees", result);
    }

    @Test
    @DisplayName("addEmployee should return the employeeAdd view")
    void addEmployee_ShouldReturnEmployeeAddView() {
        // Given
        Model model = mock(Model.class);

        // When
        String result = employeeController.addEmployee(model);

        // Then
        assertEquals("/hr/employeeAdd", result);
    }

    @Test
    @DisplayName("editEmployee should return the employeeEdit view")
    void editEmployee_ShouldReturnEmployeeEditView() {
        // Given
        Integer id = 1;
        String op = "Edit";
        Model model = mock(Model.class);
        Employee employee = new Employee(); // Create a dummy employee object

        when(employeeService.findById(id)).thenReturn(employee);

        // When
        String result = employeeController.editEmployee(id, op, model);

        // Then
        assertEquals("/hr/employeeEdit", result);
    }

    @Test
    @DisplayName("addNew should redirect to /hr/employees")
    void addNew_ShouldRedirectToEmployeesView() {
        // Given
        Employee employee = new Employee(); // Create a dummy employee object

        // When
        String result = employeeController.addNew(employee);

        // Then
        assertEquals("redirect:/hr/employees", result);
    }

    @Test
    @DisplayName("delete should redirect to /hr/employees")
    void delete_ShouldRedirectToEmployeesView() {
        // Given
        Integer id = 1;

        // When
        String result = employeeController.delete(id);

        // Then
        assertEquals("redirect:/hr/employees", result);
    }

@Test
@DisplayName("uploadFile should return ResponseEntity with OK status")
void uploadFile_ShouldReturnResponseEntityWithOKStatus() throws IOException {
    // Given
    MultipartFile file = mock(MultipartFile.class);

    when(file.getOriginalFilename()).thenReturn("filename");
    when(file.getBytes()).thenReturn(new byte[0]);

    // When
    ResponseEntity<Object> response = employeeController.uploadFile(file);

    // Then
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("File uploaded successfully", response.getBody());
}


    @Test
    @DisplayName("uploadFile2 should redirect to /employees")
    void uploadFile2_ShouldRedirectToEmployeesView() throws IOException {
        // Given
        MultipartFile file = mock(MultipartFile.class);
        Principal principal = mock(Principal.class);

        when(principal.getName()).thenReturn("username");

        // When
        String result = employeeController.uploadFile2(file, principal);

        // Then
        assertEquals("redirect:/employees", result);
    }

    @Test
    @DisplayName("profile should return the profile view")
    void profile_ShouldReturnProfileView() {
        // Given
        Model model = mock(Model.class);
        Principal principal = mock(Principal.class);
        String username = "username";
        Employee employee = new Employee(); // Create a dummy employee object

        when(principal.getName()).thenReturn(username);
        when(employeeService.findByUsername(username)).thenReturn(employee);

        // When
        String result = employeeController.profile(model, principal);

        // Then
        assertEquals("profile", result);
    }

}
