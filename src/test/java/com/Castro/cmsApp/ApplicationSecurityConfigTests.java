package com.Castro.cmsApp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

@SpringBootTest
@DisplayName("ApplicationSecurityConfig Integration Tests")
public class ApplicationSecurityConfigTests {

    @Autowired
    private ApplicationSecurityConfig applicationSecurityConfig;

    @Test
    @DisplayName("BCryptPasswordEncoder Bean - Not Null")
    public void testBCryptPasswordEncoderBean() {
        // Given
        assertNotNull(applicationSecurityConfig);

        // When
        BCryptPasswordEncoder bCryptPasswordEncoder = applicationSecurityConfig.bCryptPasswordEncoder();

        // Then
        assertNotNull(bCryptPasswordEncoder);
    }

    @Test
    @DisplayName("SecurityFilterChain Bean - Not Null")
    public void testSecurityFilterChainBean() throws Exception {
        // Given
        assertNotNull(applicationSecurityConfig);

        // When
        SecurityFilterChain securityFilterChain = applicationSecurityConfig.filterChain(mock(HttpSecurity.class));

        // Then
        assertNotNull(securityFilterChain);
    }

    @Test
    @DisplayName("AuthenticationProvider Bean - Not Null")
    public void testAuthenticationProviderBean() {
        // Given
        assertNotNull(applicationSecurityConfig);

        // When
        AuthenticationProvider authenticationProvider = applicationSecurityConfig.authenticationProvider();

        // Then
        assertNotNull(authenticationProvider);
    }
}
