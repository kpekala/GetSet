package com.kpekala.data.preferences;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PreferencesServiceTest {

    PreferencesService preferencesService;

    @BeforeEach
    public void beforeAll() {
        preferencesService = new PreferencesService();
    }

    @Test
    public void testUpdateUserName() {
        preferencesService.updateUserName("Kornat");

        assertEquals("Kornat", preferencesService.getUserName());
    }

    @Test
    public void testUpdateUserHash() {
        preferencesService.updateUserHash("123");

        assertEquals("123", preferencesService.getUserHash());
    }
}
