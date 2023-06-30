package com.Castro.cmsApp.security.controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SecurityControllerTest {

    @Test
    void testLoginPage() {
        SecurityController securityController = new SecurityController();
        String result = securityController.loginPage();
        assertEquals("security/login", result);
    }

    @Test
    void testRegister() {
        SecurityController securityController = new SecurityController();
        String result = securityController.register();
        assertEquals("security/register", result);
    }

    @Test
    void testHomePage() {
        SecurityController securityController = new SecurityController();
        String result = securityController.homePage();
        assertEquals("index", result);
    }

    @Test
    void testAccessDenied() {
        SecurityController securityController = new SecurityController();
        String result = securityController.accessDenied();
        assertEquals("accessDenied", result);
    }
}

