package com.group8.project.domain;

import java.util.ArrayList;
import java.util.List;

public enum PropertyTypeEnum {
    HOUSE("house"),
    APARTMENT("apartment"),
    COMMERCIAL("commercial");

    private String name;

    PropertyTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static  List<String> getAll() {
        List<String> result = new ArrayList<>();
        PropertyTypeEnum[] values = PropertyTypeEnum.values();
        for (PropertyTypeEnum value : values) {
            result.add(value.name);
        }
        return result;
    }

}
