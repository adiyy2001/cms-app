package com.Castro.cmsApp.parameters.services;

import com.Castro.cmsApp.parameters.models.State;
import com.Castro.cmsApp.parameters.repositories.StateRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StateServiceTests {

    private StateRepository stateRepository;
    private StateService stateService;

    @BeforeEach
    public void setup() {
        stateRepository = Mockito.mock(StateRepository.class);
        stateService = new StateService(stateRepository);
    }

    @Test
    @DisplayName("Test findAll()")
    public void findAll_ShouldReturnAllStates() {
        // Create sample states
        State state1 = new State(1, "State 1", "Capital 1", "Code 1", null, null, "Details 1");
        State state2 = new State(2, "State 2", "Capital 2", "Code 2", null, null, "Details 2");
        List<State> states = new ArrayList<>();
        states.add(state1);
        states.add(state2);

        // Mock the behavior of stateRepository.findAll()
        Mockito.when(stateRepository.findAll()).thenReturn(states);

        // Call the service method
        List<State> result = stateService.findAll();

        // Verify the result
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(state1, result.get(0));
        Assertions.assertEquals(state2, result.get(1));
    }

    @Test
    @DisplayName("Test findById()")
    public void findById_ShouldReturnStateWithMatchingId() {
        // Create a sample state
        State state = new State(1, "State", "Capital", "Code", null, null, "Details");

        // Mock the behavior of stateRepository.findById()
        Mockito.when(stateRepository.findById(1)).thenReturn(Optional.of(state));

        // Call the service method
        State result = stateService.findById(1);

        // Verify the result
        Assertions.assertEquals(state, result);
    }

    @Test
    @DisplayName("Test save()")
    public void save_ShouldSaveState() {
        // Create a sample state
        State state = new State(1, "State", "Capital", "Code", null, null, "Details");

        // Call the service method
        stateService.save(state);

        // Verify that stateRepository.save() was called with the state object
        Mockito.verify(stateRepository, Mockito.times(1)).save(state);
    }

    @Test
    @DisplayName("Test delete()")
    public void delete_ShouldDeleteStateWithMatchingId() {
        // Call the service method
        stateService.delete(1);

        // Verify that stateRepository.deleteById() was called with the correct id
        Mockito.verify(stateRepository, Mockito.times(1)).deleteById(1);
    }
}
