package com.ecommerce.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.bank.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
