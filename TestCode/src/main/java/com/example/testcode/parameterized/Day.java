package com.example.testcode.parameterized;

public enum Day {

    Monday("2024-09-30"),
    Tuesday("2024-10-01"),
    Wednesday("2024-10-02"),
    Thursday("2024-10-03"),
    Friday("2024-10-04"),
    Saturday("2024-10-05"),
    Sunday("2024-10-06");

    private final String date;

    Day(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
}


