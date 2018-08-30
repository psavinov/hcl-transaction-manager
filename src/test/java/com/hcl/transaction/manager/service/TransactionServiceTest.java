package com.hcl.transaction.manager.service;

import com.hcl.transaction.manager.exception.ExternalProviderException;
import com.hcl.transaction.manager.model.Transaction;
import com.hcl.transaction.manager.provider.TransactionProvider;
import com.hcl.transaction.manager.service.impl.TransactionServiceImpl;
import com.hcl.transaction.manager.util.TestUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

/**
 * @author Pavel Savinov
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceTest {

	@Autowired
	private TransactionService transactionService;

	@Test
	public void getTransactionsTest() throws Exception {
		List<Transaction> transactions = transactionService.getTransactions(
			0, 100);

		Assert.assertEquals(transactions.size(), 50);
	}

	@Test
	public void getTransactionsByTypeTest() throws Exception {
		List<Transaction> transactions = transactionService.getTransactions(
			"SANDBOX_TAN", 0, 100);

		Assert.assertEquals(transactions.size(), 2);
	}

	@Test
	public void getTransactionsAmountTest() throws Exception {
		BigDecimal amount = transactionService.getTransactionsAmount("SANDBOX_TAN");

		Assert.assertEquals(amount.compareTo(BigDecimal.TEN), 0);

		BigDecimal badAmount = transactionService.getTransactionsAmount("BAD TYPE");

		Assert.assertEquals(badAmount.compareTo(BigDecimal.ZERO), 0);
	}

}
