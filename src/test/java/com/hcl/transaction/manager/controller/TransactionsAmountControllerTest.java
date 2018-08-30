package com.hcl.transaction.manager.controller;

import com.hcl.transaction.manager.service.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Pavel Savinov
 */
@RunWith(SpringRunner.class)
@WebMvcTest(TransactionsAmountController.class)
public class TransactionsAmountControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private TransactionService transactionService;

	@Before
	public void setUp() throws Exception {
		given(transactionService.getTransactionsAmount("empty")).willReturn(
			BigDecimal.ZERO);

		given(transactionService.getTransactionsAmount("10")).willReturn(
			BigDecimal.TEN);
	}

	@Test
	public void getTransactionsAmountUnauthorizedTest() throws Exception {
		mvc.perform(get("/transactions-amount?type=empty")).
			andExpect(status().isUnauthorized());

	}

	@Test
	public void getTransactionsAmountTest() throws Exception {
		mvc.perform(get("/transactions-amount?type=empty").
			with(user("user").
				password("ca8b947d-e2f4-4fad-bb09-7b2f828b054a"))).
			andExpect(status().isOk()).andExpect(content().string(
				BigDecimal.ZERO.toString()));

		mvc.perform(get("/transactions-amount?type=10").
			with(user("user").
				password("ca8b947d-e2f4-4fad-bb09-7b2f828b054a"))).
			andExpect(status().isOk()).andExpect(content().string(
				BigDecimal.TEN.toString()));
	}

}
