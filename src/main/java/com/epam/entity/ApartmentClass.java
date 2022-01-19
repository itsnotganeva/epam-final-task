package com.epam.entity;

import java.util.Arrays;

public enum ApartmentClass {
    ECONOMY, BUSINESS;

    public static ApartmentClass getByOrdinal(int ordinal) {
        return Arrays.asList(ApartmentClass.values()).stream().filter(c -> ordinal == c.ordinal()).findFirst().get();
    }
}
