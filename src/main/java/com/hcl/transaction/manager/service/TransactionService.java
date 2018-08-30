package com.hcl.transaction.manager.service;

import com.hcl.transaction.manager.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Pavel Savinov
 */
public interface TransactionService {

	public List<Transaction> getTransactions(int page, int size);

	public List<Transaction> getTransactions(String type, int page, int size);

	public BigDecimal getTransactionsAmount(String type);

}
