package com.matchmaking.backend.common.lang;

public enum Degree {

    DIPLOMA("Diploma",1),
    BACHELOR("Bachelor",2),
    MASTER("Master",3),
    DOCTOR("Doctor",4),
    UNKNOWN("Unknown",5)
    ;


    private String key;
    private int value;

    Degree(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }
}
