package com.hcl.transaction.manager.provider;

import com.hcl.transaction.manager.exception.ExternalProviderException;
import com.hcl.transaction.manager.model.Transaction;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * External transactions provider.
 *
 * @author Pavel Savinov
 */
public interface TransactionProvider {

	/**
	 * Provides list of transactions from the external source.
	 *
	 * @return List of transactions from the external source.
	 * @throws ExternalProviderException In case of HTTP client initialization error.
	 */
	@Cacheable("transactions")
	public List<Transaction> getTransactions() throws ExternalProviderException;

}
