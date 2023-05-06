package com.group8.project.domain;

import java.io.Serializable;
import java.time.LocalDate;

public class RewardDetail implements Serializable {

    private String detailId;
    private String operation;
    private int point;
    private LocalDate optDate;

    public RewardDetail() {
    }

    public RewardDetail(String detailId, String operation, int point, LocalDate optDate) {
        this.detailId = detailId;
        this.operation = operation;
        this.point = point;
        this.optDate = optDate;
    }

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public LocalDate getOptDate() {
        return optDate;
    }

    public void setOptDate(LocalDate optDate) {
        this.optDate = optDate;
    }
}
