package com.matchmaking.backend.common.lang;

public enum JobType {

    FULLTIME("Full-Time",1),
    PARTIME("Part-Time",2),
    CONTRACT("Contract",3);


    private String key;
    private int value;

    JobType(String key, int value) {
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
