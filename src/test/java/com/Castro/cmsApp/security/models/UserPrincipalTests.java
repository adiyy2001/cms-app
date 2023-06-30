package com.Castro.cmsApp.security.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UserPrincipal Tests")
class UserPrincipalTests {

@Test
@DisplayName("getAuthorities should return authorities with role descriptions")
void getAuthorities_ShouldReturnAuthoritiesWithRoleDescriptions() {
    // Given
    Role role1 = new Role();
    role1.setDescription("ROLE_ADMIN");

    Role role2 = new Role();
    role2.setDescription("ROLE_USER");

    User user = new User();
    user.setRoles(new HashSet<>(Arrays.asList(role1, role2)));

    UserPrincipal userPrincipal = new UserPrincipal(user);

    // When
    Collection<? extends GrantedAuthority> authorities = userPrincipal.getAuthorities();

    // Then
    assertNotNull(authorities);
    assertEquals(2, authorities.size());

    List<String> expectedAuthorities = Arrays.asList("ROLE_ADMIN", "ROLE_USER");
    for (GrantedAuthority authority : authorities) {
        assertTrue(expectedAuthorities.contains(authority.getAuthority()));
    }
}
    @Test
    @DisplayName("getPassword should return user's password")
    void getPassword_ShouldReturnUserPassword() {
        // Given
        User user = new User();
        user.setPassword("password");

        UserPrincipal userPrincipal = new UserPrincipal(user);

        // When
        String password = userPrincipal.getPassword();

        // Then
        assertEquals("password", password);
    }

    @Test
    @DisplayName("getUsername should return user's username")
    void getUsername_ShouldReturnUserUsername() {
        // Given
        User user = new User();
        user.setUsername("johndoe");

        UserPrincipal userPrincipal = new UserPrincipal(user);

        // When
        String username = userPrincipal.getUsername();

        // Then
        assertEquals("johndoe", username);
    }

    @Test
    @DisplayName("isAccountNonExpired should always return true")
    void isAccountNonExpired_ShouldAlwaysReturnTrue() {
        // Given
        UserPrincipal userPrincipal = new UserPrincipal(new User());

        // When
        boolean accountNonExpired = userPrincipal.isAccountNonExpired();

        // Then
        assertTrue(accountNonExpired);
    }

    @Test
    @DisplayName("isAccountNonLocked should always return true")
    void isAccountNonLocked_ShouldAlwaysReturnTrue() {
        // Given
        UserPrincipal userPrincipal = new UserPrincipal(new User());

        // When
        boolean accountNonLocked = userPrincipal.isAccountNonLocked();

        // Then
        assertTrue(accountNonLocked);
    }

    @Test
    @DisplayName("isCredentialsNonExpired should always return true")
    void isCredentialsNonExpired_ShouldAlwaysReturnTrue() {
        // Given
        UserPrincipal userPrincipal = new UserPrincipal(new User());

        // When
        boolean credentialsNonExpired = userPrincipal.isCredentialsNonExpired();

        // Then
        assertTrue(credentialsNonExpired);
    }

    @Test
    @DisplayName("isEnabled should always return true")
    void isEnabled_ShouldAlwaysReturnTrue() {
        // Given
        UserPrincipal userPrincipal = new UserPrincipal(new User());

        // When
        boolean enabled = userPrincipal.isEnabled();

        // Then
        assertTrue(enabled);
    }
}
