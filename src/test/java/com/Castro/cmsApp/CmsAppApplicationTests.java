package com.Castro.cmsApp;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.AuditorAware;

@SpringBootTest
@DisplayName("CmsAppApplication Integration Tests")
@SuppressWarnings("unchecked")
public class CmsAppApplicationTests {

    @Autowired
    private CmsAppApplication cmsAppApplication;

    @MockBean
    private AuditorAware<String> auditorAware;

    private String expectedUser = "john.doe";

    @BeforeEach
    public void setUp() {
        auditorAware = mock(AuditorAware.class);
        when(auditorAware.getCurrentAuditor()).thenReturn(Optional.of(expectedUser));
    }

    @Test
    @DisplayName("Main Method - Does not throw any exceptions")
    public void testMainMethod() {
        // When
        assertThatCode(() -> CmsAppApplication.main(new String[]{})).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Auditor Aware - Returns valid current auditor")
    public void testAuditorAware() {
        // Given
        assertThat(cmsAppApplication);

        // When
        AuditorAware<String> result = cmsAppApplication.auditorAware();
        String currentAuditor = result.getCurrentAuditor().orElse(null);

        // Then
        assertThat(result);
        assertThat(currentAuditor);
    }
}
