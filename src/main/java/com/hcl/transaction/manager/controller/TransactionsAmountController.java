package com.hcl.transaction.manager.controller;

import com.hcl.transaction.manager.exception.TransactionManagerException;
import com.hcl.transaction.manager.service.TransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author Pavel Savinov
 */
@RestController
@RequestMapping("/transactions-amount")
public class TransactionsAmountController {

	@Autowired
	private TransactionService transactionService;

	private static final Logger logger = LogManager.getLogger(
		TransactionsAmountController.class);

	@GetMapping(
		value = "", params = { "type" },
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BigDecimal> getTransactionsAmount(
		@RequestParam("type") String type) throws TransactionManagerException {

		if (logger.isDebugEnabled()) {
			logger.debug("getTransactionsAmount call, type = " + type);
		}

		BigDecimal totalAmount = transactionService.getTransactionsAmount(type);

		if (totalAmount == null) {
			return ResponseEntity.badRequest().build();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getTransactionsAmount response, type = " + type +
						 	", totalAmount = " + totalAmount);
		}

		return ResponseEntity.ok(totalAmount);
	}
	
}
