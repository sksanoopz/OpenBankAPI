package io.bankapp.dao;

import org.springframework.data.repository.CrudRepository;

import io.bankapp.model.AcctLogger;

import java.util.List;

public interface LoggerRepository extends CrudRepository<AcctLogger, Integer> {

    List<AcctLogger> findByAcctID(int acctID);
}
