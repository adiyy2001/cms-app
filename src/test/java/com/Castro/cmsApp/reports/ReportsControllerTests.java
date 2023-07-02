package com.Castro.cmsApp.reports;

import com.Castro.cmsApp.accounts.repositories.TransactionRepository;
import com.Castro.cmsApp.hr.repositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ReportsControllerTests {

    private ReportsController reportsController;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        transactionRepository = Mockito.mock(TransactionRepository.class);
        employeeRepository = Mockito.mock(EmployeeRepository.class);
        reportsController = new ReportsController(transactionRepository, employeeRepository);

    }

    @Test
    @DisplayName("Test vehicles() method")
    public void testVehicles() {
        // Given

        // When
        String viewName = reportsController.vehicles();

        // Then
        assertEquals("/reports/vehicles", viewName);
    }

    @Test
    @DisplayName("Test accounts() method")
    public void testAccounts() {
        // Given

        // When
        String viewName = reportsController.accounts(model);

        // Then
        assertEquals("/reports/accounts", viewName);
        verify(model, times(1)).addAttribute("transactions", transactionRepository.findAll());
        verify(model, times(1)).addAttribute("employeeCount", employeeRepository.getCountByCountry());
    }

    @Test
    @DisplayName("Test hires() method")
    public void testHires() {
        // Given

        // When
        String viewName = reportsController.hires();

        // Then
        assertEquals("/reports/hires", viewName);
    }

    @Test
    @DisplayName("Test helpdesk() method")
    public void testHelpdesk() {
        // Given

        // When
        String viewName = reportsController.helpdesk();

        // Then
        assertEquals("/reports/helpdesk", viewName);
    }

    @Test
    @DisplayName("Test maintenance() method")
    public void testMaintenance() {
        // Given

        // When
        String viewName = reportsController.maintenance();

        // Then
        assertEquals("/reports/maintenance", viewName);
    }
}
