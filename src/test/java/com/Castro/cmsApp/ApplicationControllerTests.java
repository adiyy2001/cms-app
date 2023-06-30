package com.Castro.cmsApp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationControllerTests {

    @Test
    @DisplayName("Test goHome() method")
    public void testGoHome() {
        // Given
        ApplicationController controller = new ApplicationController();

        // When
        String result = controller.goHome();

        // Then
        assertEquals("index", result);
    }

    @Test
    @DisplayName("Test hr() method")
    public void testHr() {
        // Given
        ApplicationController controller = new ApplicationController();

        // When
        String result = controller.hr();

        // Then
        assertEquals("/hr/index", result);
    }

    @Test
    @DisplayName("Test cmsApp() method")
    public void testCmsApp() {
        // Given
        ApplicationController controller = new ApplicationController();

        // When
        String result = controller.cmsApp();

        // Then
        assertEquals("/cars/index", result);
    }

    @Test
    @DisplayName("Test accounts() method")
    public void testAccounts() {
        // Given
        ApplicationController controller = new ApplicationController();

        // When
        String result = controller.accounts();

        // Then
        assertEquals("/accounts/index", result);
    }

    @Test
    @DisplayName("Test payroll() method")
    public void testPayroll() {
        // Given
        ApplicationController controller = new ApplicationController();

        // When
        String result = controller.payroll();

        // Then
        assertEquals("/payroll/index", result);
    }

    @Test
    @DisplayName("Test helpdesk() method")
    public void testHelpdesk() {
        // Given
        ApplicationController controller = new ApplicationController();

        // When
        String result = controller.helpdesk();

        // Then
        assertEquals("/helpdesk/index", result);
    }

    @Test
    @DisplayName("Test parameters() method")
    public void testParameters() {
        // Given
        ApplicationController controller = new ApplicationController();

        // When
        String result = controller.parameters();

        // Then
        assertEquals("/parameters/index", result);
    }

    @Test
    @DisplayName("Test reports() method")
    public void testReports() {
        // Given
        ApplicationController controller = new ApplicationController();

        // When
        String result = controller.reports();

        // Then
        assertEquals("/reports/index", result);
    }

    @Test
    @DisplayName("Test security() method")
    public void testSecurity() {
        // Given
        ApplicationController controller = new ApplicationController();

        // When
        String result = controller.security();

        // Then
        assertEquals("/security/index", result);
    }
}
