package com.Castro.cmsApp.parameters.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModuleControllerTests {

    private ModuleController moduleController;

    @Test
    @DisplayName("Test getModules() method")
    public void testGetModules() {
        // Given
        moduleController = new ModuleController();

        // When
        String viewName = moduleController.getModules();

        // Then
        assertEquals("modules", viewName);
    }
}
