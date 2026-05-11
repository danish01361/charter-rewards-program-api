package com.charter.springboot.apis.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.charter.springboot.apis.dto.RewardResponseDTO;
import com.charter.springboot.apis.entity.Transaction;
import com.charter.springboot.apis.exception.CustomerNotFoundException;
import com.charter.springboot.apis.repository.TransactionRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RewardServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private RewardService rewardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetRewardsByCustomer() {

        List<Transaction> transactions = Arrays.asList(
                new Transaction(
                        1L,
                        "John Doe",
                        120.0,
                        LocalDate.of(2025, 1, 15)
                ),
                new Transaction(
                        1L,
                        "John Doe",
                        75.0,
                        LocalDate.of(2025, 2, 10)
                ),
                new Transaction(
                        1L,
                        "John Doe",
                        200.0,
                        LocalDate.of(2025, 3, 5)
                )
        );

        when(transactionRepository.findByCustomerId(1L))
                .thenReturn(transactions);

        RewardResponseDTO response =
                rewardService.getRewardsByCustomer(1L);

        assertEquals(1L, response.getCustomerId());
        assertEquals("John Doe", response.getCustomerName());
        assertEquals(365, response.getTotalPoints());
        assertEquals(3, response.getMonthlyRewards().size());
    }

    @Test
    void testCustomerNotFound() {

        when(transactionRepository.findByCustomerId(999L))
                .thenReturn(Collections.emptyList());

        assertThrows(
                CustomerNotFoundException.class,
                () -> rewardService.getRewardsByCustomer(999L)
        );
    }
}