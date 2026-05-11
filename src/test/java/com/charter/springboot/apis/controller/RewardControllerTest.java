package com.charter.springboot.apis.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.charter.springboot.apis.dto.MonthlyRewardDTO;
import com.charter.springboot.apis.dto.RewardResponseDTO;
import com.charter.springboot.apis.service.RewardService;

public class RewardControllerTest {

    @Test
    void testControllerResponse() {

        RewardService rewardService =
                Mockito.mock(RewardService.class);

        RewardController rewardController =
                new RewardController(rewardService);

        RewardResponseDTO mockResponse =
                new RewardResponseDTO(
                        1L,
                        "John Doe",
                        Arrays.asList(
                                new MonthlyRewardDTO(
                                        "JANUARY",
                                        90
                                )
                        ),
                        90
                );

        Mockito.when(
                rewardService.getRewardsByCustomer(1L))
                .thenReturn(mockResponse);

        RewardResponseDTO response =
                rewardController.getRewards(1L);

        assertEquals(1L, response.getCustomerId());
        assertEquals("John Doe",
                response.getCustomerName());
        assertEquals(90,
                response.getTotalPoints());
    }
}