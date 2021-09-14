package com.ecommerce.bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.bank.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>
{
	Optional<Account> findByAccountNo(long accountNo);
}
