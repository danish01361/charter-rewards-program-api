package com.charter.springboot.apis.dto;

import java.util.List;

public class RewardResponseDTO {

    private Long customerId;

    private String customerName;

    private List<MonthlyRewardDTO> monthlyRewards;

    private int totalPoints;

    public RewardResponseDTO(Long customerId,
                             String customerName,
                             List<MonthlyRewardDTO> monthlyRewards,
                             int totalPoints) {

        this.customerId = customerId;
        this.customerName = customerName;
        this.monthlyRewards = monthlyRewards;
        this.totalPoints = totalPoints;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<MonthlyRewardDTO> getMonthlyRewards() {
        return monthlyRewards;
    }

    public int getTotalPoints() {
        return totalPoints;
    }
}
