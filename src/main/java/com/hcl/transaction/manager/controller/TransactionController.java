package com.hcl.transaction.manager.controller;

import com.hcl.transaction.manager.exception.TransactionManagerException;
import com.hcl.transaction.manager.model.Transaction;
import com.hcl.transaction.manager.service.TransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Pavel Savinov
 */
@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	private static final Logger logger = LogManager.getLogger(
		TransactionController.class);

	@GetMapping(
		value = "", params = { "page", "size" },
		produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Transaction> getTransactions(
			@RequestParam("page") int page, @RequestParam("size") int size)
		throws TransactionManagerException {

		if (logger.isDebugEnabled()) {
			logger.debug(
				"getTransactions call, page = " + page + ", size = " + size);
		}

		return transactionService.getTransactions(page, size);
	}

	@GetMapping(
		value = "", params = { "page", "size", "type" },
		produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Transaction> getTransactions(
			@RequestParam("page") int page, @RequestParam("size") int size,
			@RequestParam("type") String type)
		throws TransactionManagerException {

		if (logger.isDebugEnabled()) {
			logger.debug(
				"getTransactions call, page = " + page + ", size = " + size +
					", type = " + type);
		}

		return transactionService.getTransactions(type, page, size);
	}
	
}
