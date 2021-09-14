package com.ecommerce.bank.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.bank.dto.AccountResponse;
import com.ecommerce.bank.dto.FundTransferDto;
import com.ecommerce.bank.model.Account;
import com.ecommerce.bank.service.BankServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@EnableTransactionManagement
@RequestMapping("/api/bankservice")
@Slf4j
public class BankServiceController 
{

	private static final Logger logger = LoggerFactory.getLogger(BankServiceController.class);
	
	@Autowired
	BankServiceImpl  bankserviceImpl;
	
	@GetMapping("/{accountNo}")
	public ResponseEntity<AccountResponse> getAccountAvlBal(@PathVariable("accountNo") String accountNo)
	{
		return bankserviceImpl.getAccountAvlBal(accountNo);
	}
	@PostMapping("/fundTransfer")
   	public ResponseEntity<HttpStatus> fundTransfer(@RequestBody FundTransferDto fundTransferDto)  
   	{
       	logger.info("inside checkCredentials");
   		return bankserviceImpl.fundTransfer(fundTransferDto);
   	}
	@GetMapping("/getAllAccount")
	public ResponseEntity<List<Account>> getAllAccounts()
	{
		return bankserviceImpl.getAllAccounts();
	}
	
	
}
