package com.qa.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.qa.demo.entities.Account;
import com.qa.demo.repository.AccountRepo;

@Service
public class AccountService {

	private AccountRepo accountRepo;

	public AccountService(AccountRepo accountRepo) {
		this.accountRepo = accountRepo;
	}

	public String createAccount(Account account) {
		this.accountRepo.save(account);
		return "Account created";	
	}

	public List<Account> getAccounts() {
		return this.accountRepo.findAll();
	}
	
	public Account getLastAccount() {
		return this.accountRepo.findAll().get((int) (this.accountRepo.count()-1));
	}

	public List<Account> accountSearch(Account account) {
		return this.getAccounts().stream().filter(x -> x.matches(account)).collect(Collectors.toList());
	}
	
}