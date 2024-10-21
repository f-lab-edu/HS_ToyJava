package com.example.testcode.parameterized;

public enum Constants {
    
    SUNDAY(7);

    private long day;

    Constants(long day) {
        this.day = day;
    }

    public long getDate() {
        return day;
    }
}
