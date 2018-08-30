package com.hcl.transaction.manager.service.impl;

import com.hcl.transaction.manager.exception.TransactionManagerException;
import com.hcl.transaction.manager.model.Transaction;
import com.hcl.transaction.manager.provider.TransactionProvider;
import com.hcl.transaction.manager.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Pavel Savinov
 *
 */
@Service
public class TransactionServiceImpl implements TransactionService {

	@Override
	public List<Transaction> getTransactions(int page, int size)
		throws TransactionManagerException {

		List<Transaction> transactions = transactionProvider.getTransactions();

		return transactions.stream().skip(page > 0 ? page * size : page).
			limit(size).collect(Collectors.toList());
	}

	@Override
	public List<Transaction> getTransactions(String type, int page, int size)
		throws TransactionManagerException {

		List<Transaction> transactions = transactionProvider.getTransactions();

		return transactions.stream().filter(
			transaction -> Objects.equals(type, transaction.getType())).
			skip(page > 0 ? page * size : page).limit(size).
			collect(Collectors.toList());
	}

	@Override
	public BigDecimal getTransactionsAmount(String type)
		throws TransactionManagerException {

		List<Transaction> transactions = transactionProvider.getTransactions();

		Function<Transaction, BigDecimal> mapper =
			transaction -> transaction.getTransactionAmount();

		return transactions.stream().filter(
			transaction -> Objects.equals(type, transaction.getType())).
			map(mapper).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
	}

	@Autowired
	private TransactionProvider transactionProvider;

}
