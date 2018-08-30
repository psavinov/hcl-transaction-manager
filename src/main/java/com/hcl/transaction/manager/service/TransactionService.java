package com.hcl.transaction.manager.service;

import com.hcl.transaction.manager.exception.TransactionManagerException;
import com.hcl.transaction.manager.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

/**
 * Transaction service, provides functionality accordingly to the task
 * description.
 *
 * @author Pavel Savinov
 */
public interface TransactionService {

	/**
	 * Paginated list of transactions.
	 *
	 * @param page Page number, starting with 0.
	 * @param size Size of current page.
	 * @return Paginated list of transactions.
	 * @throws TransactionManagerException In case of transaction provider failure.
	 */
	public List<Transaction> getTransactions(int page, int size) throws
		TransactionManagerException;

	/**
	 * Filtered by type and paginated list of transactions.
	 *
	 * @param type Type to filter by.
	 * @param page Page number, starting with 0.
	 * @param size Size of current page.
	 * @return Filtered and paginated list of transactions.
	 * @throws TransactionManagerException In case of transaction provider failure.
	 */
	public List<Transaction> getTransactions(String type, int page, int size)
		throws TransactionManagerException;

	/**
	 * Total amount for transactions of specific type.
	 *
	 * @param type Type to filter by.
	 * @return Total amount of transactions.
	 * @throws TransactionManagerException In case of transaction provider failure.
	 */
	public BigDecimal getTransactionsAmount(String type)
		throws TransactionManagerException;

}
