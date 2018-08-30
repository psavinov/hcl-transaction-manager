package com.hcl.transaction.manager.provider;

import com.hcl.transaction.manager.exception.ExternalProviderException;
import com.hcl.transaction.manager.model.Transaction;

import java.util.List;

/**
 * @author Pavel Savinov
 */
public interface TransactionProvider {

	public List<Transaction> getTransactions() throws ExternalProviderException;

}
