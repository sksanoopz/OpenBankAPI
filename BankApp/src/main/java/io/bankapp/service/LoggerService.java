package io.bankapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.bankapp.dao.LoggerRepository;
import io.bankapp.model.AcctLogger;

import java.util.List;

@Service
public class LoggerService {
	@Autowired
	LoggerRepository loggerRepository;

	public void addLog(AcctLogger acctLogger) {
		loggerRepository.save(acctLogger);
	}

	public List<AcctLogger> showLog(int acctID) {
		return loggerRepository.findByAcctID(acctID);
	}

	public void deleteLog(int acctID) {
		loggerRepository.deleteById(acctID);
	}
}
