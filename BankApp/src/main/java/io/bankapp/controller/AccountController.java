package io.bankapp.controller;

import io.bankapp.model.AcctLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import io.bankapp.model.Accounts;
import io.bankapp.service.AccountService;

@RestController
public class AccountController {
	Logger log =LoggerFactory.getLogger(AccountController.class.getName());

	@Autowired
	private AccountService accountService;
	@Autowired
	private LoggerController loggerController;

	// createAccount happens upon createCustomer
	public void createAccount(int acctID, int balance, String acctStatus) {
		Accounts acct = new Accounts(acctID, balance, acctStatus);
		accountService.createAccount(acct);
	}

	// checkBalance
	@GetMapping("/account/{acctID}/balance")
	public int getBalance(@PathVariable int acctID) {
		return accountService.getBalance(acctID);
	}

	// depositAmount
	@PutMapping("/account/{acctID}/deposit/{amount}")
	public void depositAmount(@PathVariable int acctID, @PathVariable int amount) {
		int initBal = getBalance(acctID);
		accountService.depositAmount(acctID, amount);
		AcctLogger logger = new AcctLogger(acctID, "Deposited", "Success", initBal, initBal + amount);
		loggerController.addLog(logger);
	}

	// withdrawAmount
	@PutMapping("/account/{acctID}/withdraw/{amount}")
	public void withdrawAmount(@PathVariable int acctID, @PathVariable int amount) {
		int initBal = getBalance(acctID);
		log.info("Before Trax Current Balance {}",initBal);
		accountService.withdrawAmount(acctID, amount);
		log.info("Current Balance {}",getBalance(acctID));
		AcctLogger logger = new AcctLogger(acctID, "Withdrawn", "Success", initBal, initBal - amount);
		loggerController.addLog(logger);
	}

	// transferAmount
	@PutMapping("/account/{acctID}/transfer/{destAcctID}/{amount}")
	public void transferAmount(@PathVariable int acctID, @PathVariable int destAcctID, @PathVariable int amount) {
		int initBalSender = getBalance(acctID);
		int initBalReceiver = getBalance(destAcctID);
		accountService.transferAmount(acctID, destAcctID, amount);
		AcctLogger loggerSender = new AcctLogger(acctID, "Transferred", "Success", initBalSender, initBalSender - amount);
		loggerController.addLog(loggerSender);
		AcctLogger loggerReceiver = new AcctLogger(destAcctID, "Received", "Success", initBalReceiver,
				initBalReceiver + amount);
		loggerController.addLog(loggerReceiver);
	}

	// deleteAccount
	@DeleteMapping("/account/{acctID}")
	public void deleteAccount(@PathVariable int acctID) {
		accountService.deleteAccount(acctID);
		loggerController.deleteLog(acctID);
	}

	// getAccountInfo
	@GetMapping("/account/{acctID}")
	public Accounts getAccountInfo(@PathVariable int acctID) {
		return accountService.getAccountInfo(acctID);
	}

}
