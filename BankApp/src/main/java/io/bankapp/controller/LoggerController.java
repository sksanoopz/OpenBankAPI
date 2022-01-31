package io.bankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.bankapp.model.AcctLogger;
import io.bankapp.service.LoggerService;

import java.util.List;

@RestController
public class LoggerController {
	@Autowired
	private LoggerService loggerService;

	// addLog
	public void addLog(AcctLogger acctLogger) {
		loggerService.addLog(acctLogger);
	}

	// showLog
	@GetMapping("/account/{acctID}/logs")
	public List<AcctLogger> showLog(@PathVariable int acctID) {
		return loggerService.showLog(acctID);
	}

	public void deleteLog(int acctID) {
		loggerService.deleteLog(acctID);
	}
}
