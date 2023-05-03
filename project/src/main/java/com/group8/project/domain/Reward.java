package com.group8.project.domain;

import java.io.Serializable;
import java.util.List;

public class Reward implements Serializable {

    private String rewardId;
    private String email;
    private int pointCount;
    private List<RewardDetail> rewardDetails;

    public Reward() {}

    public Reward(String rewardId, String email, int pointCount) {
        this.rewardId = rewardId;
        this.email = email;
        this.pointCount = pointCount;
    }

    public String getRewardId() {
        return rewardId;
    }

    public void setRewardId(String rewardId) {
        this.rewardId = rewardId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPointCount() {
        return pointCount;
    }

    public void setPointCount(int pointCount) {
        this.pointCount = pointCount;
    }

    public List<RewardDetail> getRewardDetails() {
        return rewardDetails;
    }

    public void setRewardDetails(List<RewardDetail> rewardDetails) {
        this.rewardDetails = rewardDetails;
    }
}
