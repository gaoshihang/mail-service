package com.gsh.mailservice.test;

public class TestEnum {

    public static void main(String[] args) {
        System.out.println(Day.MONDAY.toString().equals("MONDAY"));
    }
}

enum Day{
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}
