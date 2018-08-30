package com.hcl.transaction.manager.model;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * @author Pavel Savinov
 */
public class Transaction {

	private String id;
	private String accountId;
	private String counterpartyAccount;
	private String counterpartyName;
	private String counterpartyLogoPath;
	private BigDecimal instructedAmount;
	private Currency instructedCurrency;
	private BigDecimal transactionAmount;
	private Currency transactionCurrency;
	private String type;
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getCounterpartyAccount() {
		return counterpartyAccount;
	}

	public void setCounterpartyAccount(String counterpartyAccount) {
		this.counterpartyAccount = counterpartyAccount;
	}

	public String getCounterpartyName() {
		return counterpartyName;
	}

	public void setCounterpartyName(String counterpartyName) {
		this.counterpartyName = counterpartyName;
	}

	public String getCounterpartyLogoPath() {
		return counterpartyLogoPath;
	}

	public void setCounterpartyLogoPath(String counterpartyLogoPath) {
		this.counterpartyLogoPath = counterpartyLogoPath;
	}

	public BigDecimal getInstructedAmount() {
		return instructedAmount;
	}

	public void setInstructedAmount(BigDecimal instructedAmount) {
		this.instructedAmount = instructedAmount;
	}

	public Currency getInstructedCurrency() {
		return instructedCurrency;
	}

	public void setInstructedCurrency(Currency instructedCurrency) {
		this.instructedCurrency = instructedCurrency;
	}

	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public Currency getTransactionCurrency() {
		return transactionCurrency;
	}

	public void setTransactionCurrency(Currency transactionCurrency) {
		this.transactionCurrency = transactionCurrency;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
