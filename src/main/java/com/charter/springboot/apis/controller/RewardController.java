package com.charter.springboot.apis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.charter.springboot.apis.dto.RewardResponseDTO;
import com.charter.springboot.apis.service.RewardService;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {

    private final RewardService rewardService;

    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    @GetMapping("/{customerId}")
    public RewardResponseDTO getRewards(
            @PathVariable Long customerId) {

        return rewardService.getRewardsByCustomer(customerId);
    }
}