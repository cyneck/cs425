package com.group8.project.domain;

import org.springframework.data.relational.core.mapping.Column;

import java.io.Serializable;

public class Apartment implements Serializable {
    @Column("property_id")
    private String propertyId;
    @Column("rooms_number")
    private Integer roomsNumber;
    @Column("building_type")
    private String buildingType;
}
