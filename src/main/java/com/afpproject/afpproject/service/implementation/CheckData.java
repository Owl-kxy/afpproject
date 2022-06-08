package com.afpproject.afpproject.service.implementation;

import com.afpproject.afpproject.service.CheckDataExtern;

public class CheckData
{
    CheckDataExtern checkDataExtern;

    public String dniEntry(String dni)
    {
        return checkDataExtern.CheckData(dni);
    }
}
