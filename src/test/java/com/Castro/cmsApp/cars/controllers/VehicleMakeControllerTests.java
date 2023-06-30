package com.Castro.cmsApp.cars.controllers;

import com.Castro.cmsApp.cars.models.VehicleMake;
import com.Castro.cmsApp.cars.services.VehicleMakeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("VehicleMakeController Tests")
class VehicleMakeControllerTests {
    @Mock
private VehicleMakeService vehicleMakeService;

@Mock
private Model model;

private VehicleMakeController vehicleMakeController;

@BeforeEach
void setUp() {
    MockitoAnnotations.openMocks(this);
    vehicleMakeController = new VehicleMakeController(vehicleMakeService);
}

@Test
@DisplayName("findAll method should return the correct view name")
void findAll_ShouldReturnCorrectViewName() {
    // Given
    List<VehicleMake> vehicleMakes = new ArrayList<>();
    when(vehicleMakeService.findAll()).thenReturn(vehicleMakes);

    // When
    String viewName = vehicleMakeController.findAll(model);

    // Then
    assertEquals("/cars/vehicleMakes", viewName);
    verify(model, times(1)).addAttribute("vehicleMakes", vehicleMakes);
}

@Test
@DisplayName("findById method should return the optional vehicle make")
void findById_ShouldReturnOptionalVehicleMake() {
    // Given
    Integer id = 1;
    VehicleMake vehicleMake = new VehicleMake();
    when(vehicleMakeService.findById(id)).thenReturn(Optional.of(vehicleMake));

    // When
    Optional<VehicleMake> result = vehicleMakeController.findById(id);

    // Then
    assertEquals(Optional.of(vehicleMake), result);
}

@Test
@DisplayName("addNew method should save the vehicle make and redirect correctly")
void addNew_ShouldSaveVehicleMakeAndRedirect() {
    // Given
    VehicleMake vehicleMake = new VehicleMake();

    // When
    String redirectUrl = vehicleMakeController.addNew(vehicleMake);

    // Then
    verify(vehicleMakeService, times(1)).save(vehicleMake);
    assertEquals("redirect:/cars/vehicleMakes", redirectUrl);
}

@Test
@DisplayName("delete method should delete the vehicle make and redirect correctly")
void delete_ShouldDeleteVehicleMakeAndRedirect() {
    // Given
    Integer id = 1;

    // When
    String redirectUrl = vehicleMakeController.delete(id);

    // Then
    verify(vehicleMakeService, times(1)).delete(id);
    assertEquals("redirect:/cars/vehicleMakes", redirectUrl);
}

}