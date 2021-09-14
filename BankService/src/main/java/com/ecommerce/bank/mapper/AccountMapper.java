package com.ecommerce.bank.mapper;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ecommerce.bank.dto.AccountDTO;
import com.ecommerce.bank.model.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper
{
	@Mapping(target = "accountId", ignore = true)
	@Mapping(target = "transactionDetails", ignore = true)
	@Mapping(target = "availableBalance", expression = "java(Double.valueOf(request.getOpeningDeposit()))")
	@Mapping(target = "accountNo", expression = "java(numbGen())")
	Account mapToAccount(AccountDTO request);
	
	default long numbGen() 
	 {
		  while (true)
		  {
		        long numb = (long)(Math.random() * 100000000 * 100000000); // had to use this as int's are to small for a 12 digit number.
		        if (String.valueOf(numb).length() == 12)
		           return numb;
		  }
	  }
	
	default String passwordGenerator()
	{
		String password = UUID.randomUUID().toString().split("-")[1];
		return password;
	}

}
