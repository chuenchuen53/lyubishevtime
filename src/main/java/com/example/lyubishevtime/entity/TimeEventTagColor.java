package com.example.lyubishevtime.entity;

public enum TimeEventTagColor {
    RED,
    ORANGE,
    YELLOW,
    GREEN,
    CYAN,
    BLUE,
    PURPLE,
    GREY;

    public String getValue() {
        return this.name().toLowerCase();
    }
}