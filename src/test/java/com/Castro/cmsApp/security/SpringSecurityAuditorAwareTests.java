package com.Castro.cmsApp.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SpringSecurityAuditorAwareTests {

    private AuditorAware<String> auditorAware;
    private SecurityContext securityContext;
    private Authentication authentication;

    @BeforeEach
    public void setUp() {
        auditorAware = new SpringSecurityAuditorAware();
    }

    @BeforeEach
    public void initSecurityContext() {
        securityContext = mock(SecurityContext.class);
        authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    @DisplayName("Test getCurrentAuditor() method when authentication is available")
    public void testGetCurrentAuditorWithAuthentication() {
        // Given
        String expectedUsername = "john.doe";
        when(authentication.getName()).thenReturn(expectedUsername);

        // When
        Optional<String> result = auditorAware.getCurrentAuditor();

        // Then
        assertEquals(expectedUsername, result.orElse(null));
    }

    @Test
    @DisplayName("Test getCurrentAuditor() method when authentication is not available or username is empty")
    public void testGetCurrentAuditorWithoutAuthenticationOrEmptyUsername() {
        // Given
        when(authentication.getName()).thenReturn("");

        // When
        Optional<String> result = auditorAware.getCurrentAuditor();

        // Then
        assertFalse(result.isPresent());
    }
}
