package com.hcl.transaction.manager.util;

import com.hcl.transaction.manager.model.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author Pavel Savinov
 */
public class TestUtil {

	public static List<Transaction> addTransactions(int number) {
		List<Transaction> transactions = new ArrayList<>();

		for (int k = 0; k < number; k++) {
			Transaction transaction = new Transaction();

			transaction.setId(UUID.randomUUID().toString());
			transaction.setTransactionCurrency(Currency.getInstance("USD"));
			transaction.setTransactionAmount(
				new BigDecimal(new Random().nextInt(1000)));
			transaction.setType("transaction-type");

			transactions.add(transaction);
		}

		return transactions;
	}

}
