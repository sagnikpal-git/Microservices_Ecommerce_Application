package com.ecommerce.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.bank.dto.AccountResponse;
import com.ecommerce.bank.dto.FundTransferDto;
import com.ecommerce.bank.exception.TransactionFailedException;
import com.ecommerce.bank.mapper.FundTransferMapper;
import com.ecommerce.bank.model.Account;
import com.ecommerce.bank.repository.AccountRepository;
import com.ecommerce.bank.repository.TransactionRepository;

@Service
public class BankServiceImpl 
{

	@Autowired
	AccountRepository accRepo;
	
	@Autowired
	TransactionRepository transRepo;	
	
	@Autowired
	FundTransferMapper fundTransferMapper;	
	
	
	public ResponseEntity<AccountResponse> getAccountAvlBal(String accountNo)
	{
		 Optional<Account> acct = getAccountDetails(accountNo);
		 AccountResponse response = new AccountResponse();
		 if(acct.isPresent())
		 {
			 response.setAccountNo(acct.get().getAccountNo());
			 response.setAvailableBalance(acct.get().getAvailableBalance());
		 }
		 return new ResponseEntity<>(response,HttpStatus.OK);
		
	}
	
	@Transactional(rollbackFor = TransactionFailedException.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRES_NEW)
	public ResponseEntity<HttpStatus> fundTransfer(FundTransferDto fundTransDto)
	{
		 Optional<Account> fromAccDetails = getAccountDetails(fundTransDto.getFromAccountNo());
		if(fromAccDetails.isPresent())
		{
			transRepo.save(fundTransferMapper.mapToTransaction(fundTransDto, fromAccDetails.get()));
			accRepo.save(fundTransferMapper.mapToAccount(fundTransDto,fromAccDetails.get()));		
			return new ResponseEntity<>(HttpStatus.OK);
		}else
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private Optional<Account> getAccountDetails(String accountNo)
	{
		return accRepo.findByAccountNo(Long.valueOf(accountNo));
				
	}
	
	public ResponseEntity<List<Account>> getAllAccounts()
	{
		return new ResponseEntity<>(accRepo.findAll(),HttpStatus.OK);
	}
	
	
	
}
