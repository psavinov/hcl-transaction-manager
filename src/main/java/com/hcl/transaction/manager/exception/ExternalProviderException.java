package com.hcl.transaction.manager.exception;

/**
 * @author Pavel Savinov
 */
public class ExternalProviderException extends TransactionManagerException {

	public ExternalProviderException(Exception cause) {
		super(cause);
	}

}
