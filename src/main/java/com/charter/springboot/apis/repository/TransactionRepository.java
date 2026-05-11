package com.charter.springboot.apis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.charter.springboot.apis.entity.Transaction;

public interface TransactionRepository
        extends JpaRepository<Transaction, Long> {

    List<Transaction> findByCustomerId(Long customerId);
}