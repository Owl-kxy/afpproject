package com.afpproject.afpproject.service.implementation;

import com.afpproject.afpproject.service.CheckDataExtern;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CheckDataTest {

    @Mock
    CheckDataExtern checkDataExtern;

    @InjectMocks
    CheckData checkData;

    @BeforeEach
    public void init()
    {
        MockitoAnnotations.openMocks(this);
        when(checkDataExtern.CheckData("70106169")).thenReturn("correcto");
    }

    @Test
    void dniEntry()
    {
        Assertions.assertEquals("correcto", checkData.dniEntry("70106169"));
    }
}