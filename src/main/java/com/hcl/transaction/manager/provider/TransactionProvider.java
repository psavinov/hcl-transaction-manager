package com.hcl.transaction.manager.provider;

import com.hcl.transaction.manager.exception.ExternalProviderException;
import com.hcl.transaction.manager.model.Transaction;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author Pavel Savinov
 */
public interface TransactionProvider {

	@Cacheable("transactions")
	public List<Transaction> getTransactions() throws ExternalProviderException;

}
