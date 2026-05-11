package com.charter.springboot.apis.service;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.charter.springboot.apis.dto.MonthlyRewardDTO;
import com.charter.springboot.apis.dto.RewardResponseDTO;
import com.charter.springboot.apis.entity.Transaction;
import com.charter.springboot.apis.exception.CustomerNotFoundException;
import com.charter.springboot.apis.repository.TransactionRepository;
import com.charter.springboot.apis.util.RewardCalculator;

@Service
public class RewardService {

    private final TransactionRepository transactionRepository;

    public RewardService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public RewardResponseDTO getRewardsByCustomer(Long customerId) {

        List<Transaction> transactions =
                transactionRepository.findByCustomerId(customerId);

        if (transactions.isEmpty()) {
            throw new CustomerNotFoundException(
                    "Customer not found with ID: " + customerId);
        }

        Map<Month, List<Transaction>> monthlyTransactions =
                transactions.stream()
                        .collect(Collectors.groupingBy(
                                transaction ->
                                        transaction.getTransactionDate().getMonth()));

        List<MonthlyRewardDTO> monthlyRewards =
                new ArrayList<>();

        int totalPoints = 0;

        for (Map.Entry<Month,
                List<Transaction>> entry :
                monthlyTransactions.entrySet()) {

            int monthlyPoints =
                    entry.getValue()
                            .stream()
                            .mapToInt(transaction ->
                                    RewardCalculator.calculatePoints(
                                            transaction.getAmount()))
                            .sum();

            totalPoints += monthlyPoints;

            monthlyRewards.add(
                    new MonthlyRewardDTO(
                            entry.getKey().name(),
                            monthlyPoints));
        }

        Transaction firstTransaction = transactions.get(0);

        return new RewardResponseDTO(
                customerId,
                firstTransaction.getCustomerName(),
                monthlyRewards,
                totalPoints);
    }
}