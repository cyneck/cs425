package com.group8.project.domain;

import org.springframework.data.relational.core.mapping.Column;

import java.io.Serializable;

public class Commercial implements Serializable {
    @Column("property_id")
    private String propertyId;
    @Column("business_type")
    private String businessType;
}
