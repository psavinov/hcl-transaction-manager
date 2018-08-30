package com.hcl.transaction.manager.provider;

import com.hcl.transaction.manager.model.Transaction;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Pavel Savinov
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionProviderTest {

	@Autowired
	private TransactionProvider transactionProvider;

	@Test
	public void getTransactionsTest() throws Exception {
		List<Transaction> transactions = transactionProvider.getTransactions();

		Assert.assertEquals(transactions.size(), 50);
	}

}
