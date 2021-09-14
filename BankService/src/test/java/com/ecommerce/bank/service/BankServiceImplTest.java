package com.ecommerce.bank.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ecommerce.bank.dto.AccountResponse;
import com.ecommerce.bank.dto.FundTransferDto;
import com.ecommerce.bank.exception.InvalidAccountNoException;
import com.ecommerce.bank.mapper.FundTransferMapper;
import com.ecommerce.bank.model.Account;
import com.ecommerce.bank.model.Transaction;
import com.ecommerce.bank.repository.AccountRepository;
import com.ecommerce.bank.repository.TransactionRepository;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BankServiceImplTest {

	@Mock
	AccountRepository accountRepository;

	@Mock
	TransactionRepository transactionRepository;

	@Mock
	FundTransferMapper fundTransferMapper;

	static Account account;
	static Transaction transaction;
	static AccountResponse response;
	static FundTransferDto fundTransferDto = new FundTransferDto();

	@InjectMocks
	BankServiceImpl bankServiceImpl;

	@BeforeAll
	public static void setUp() {

		response = new AccountResponse();
		response.setAccountNo(1l);
		response.setAvailableBalance(1000);

		account = new Account();
		account.setAccountId(1l);
		account.setAccountNo(1l);
		account.setAccountType("Savings");
		account.setAvailableBalance(800000);

		transaction = new Transaction();

		transaction.setAccount(account);
		transaction.setAmount(1000);
		transaction.setFromAccount(1l);

		fundTransferDto.setFromAccountNo("33534022706");
		fundTransferDto.setToAccountNo("32145678098");
		fundTransferDto.setTransferAmount("9000");
		fundTransferDto.setRemarks("DEBIT");

	}

	@Test
	@DisplayName("Avaiable Balance :: Postive Scenario")
	@Order(1)
	public void testAvailableBalancePostive() {
		Mockito.when(accountRepository.findByAccountNo(Long.valueOf("33534022706"))).thenReturn(Optional.of(account));

		ResponseEntity<AccountResponse> result = bankServiceImpl.getAccountAvlBal("33534022706");

		assertEquals(HttpStatus.OK, result.getStatusCode());

	}

	@Test
	@DisplayName("Avaiable Balance :: Negative Scenario")
	@Order(2)
	public void testAvailableBalanceNegative() {
		Mockito.when(accountRepository.findByAccountNo(Long.valueOf("33534022706")))
				.thenThrow(InvalidAccountNoException.class);

		assertThrows(InvalidAccountNoException.class, () -> bankServiceImpl.getAccountAvlBal("33534022706"));

	}

	@Test
	@DisplayName("FundTransfer :: Postive Scenario")
	@Order(3)
	public void testFundTransferPostive() {
		Mockito.when(accountRepository.findByAccountNo(Long.valueOf("33534022706"))).thenReturn(Optional.of(account));

		Mockito.when(transactionRepository.save(Mockito.any(Transaction.class))).thenAnswer(i -> {
			Transaction transaction = i.getArgument(0);
			transaction.setTransactionId(1l);
			return transaction;
		});

		Mockito.when(fundTransferMapper.mapToTransaction(fundTransferDto, Optional.of(account).get()))
				.thenReturn(transaction);

		Mockito.when(fundTransferMapper.mapToAccount(fundTransferDto, Optional.of(account).get())).thenReturn(account);

		ResponseEntity<HttpStatus> result = bankServiceImpl.fundTransfer(fundTransferDto);

		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	@DisplayName("FundTransfer :: Negative Scenario")
	@Order(4)
	public void testFundTransferNegative() {
		Mockito.when(accountRepository.findByAccountNo(Long.valueOf("33534022706")))
				.thenThrow(InvalidAccountNoException.class);

		assertThrows(InvalidAccountNoException.class, () -> bankServiceImpl.fundTransfer(fundTransferDto));

	}

}
