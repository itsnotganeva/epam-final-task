package com.epam.entity;

import java.util.Arrays;

public enum PersonRole {
    ADMIN, USER;

    public static PersonRole getByOrdinal(int ordinal) {
        return Arrays.asList(PersonRole.values()).stream().filter(pr -> ordinal == pr.ordinal()).findFirst().get();
    }
}
