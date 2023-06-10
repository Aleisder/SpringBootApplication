package com.tsarenko.demo.repository;

public enum UserColumn {
    ID("id"),
    LAST_NAME("last_name");


    public final String value;

    UserColumn(String value) {
        this.value = value;
    }
}
