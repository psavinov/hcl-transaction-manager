package com.hcl.transaction.manager.controller;

import com.hcl.transaction.manager.model.Transaction;
import com.hcl.transaction.manager.service.TransactionService;
import com.hcl.transaction.manager.util.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Pavel Savinov
 */
@RunWith(SpringRunner.class)
@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private TransactionService transactionService;

	@Before
	public void setUp() throws Exception {
		given(transactionService.getTransactions(0, 1)).willReturn(
			TestUtil.addTransactions(1));

		given(transactionService.getTransactions("transaction-type", 0, 1)).willReturn(
			TestUtil.addTransactions(1));

		given(transactionService.getTransactions(0, 50)).willReturn(
			TestUtil.addTransactions(50));

		given(transactionService.getTransactions("transaction-type", 0, 50)).willReturn(
			TestUtil.addTransactions(50));
	}

	@Test
	public void getTransactionsUnauthorizedTest() throws Exception {
		mvc.perform(get("/transaction?page=0&size=1")).
			andExpect(status().isUnauthorized());

		mvc.perform(get("/transaction?page=0&size=1&type=some-type")).
			andExpect(status().isUnauthorized());
	}

	@Test
	public void getTransactionsTest() throws Exception {
		mvc.perform(get("/transaction?page=0&size=1").
			with(user("user").
				password("ca8b947d-e2f4-4fad-bb09-7b2f828b054a"))).
			andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)));

		mvc.perform(get("/transaction?page=0&size=50").
			with(user("user").
				password("ca8b947d-e2f4-4fad-bb09-7b2f828b054a"))).
			andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(50)));
	}

	@Test
	public void getTransactionsByTypeTest() throws Exception {
		mvc.perform(get("/transaction?page=0&size=1&type=transaction-type").
			with(user("user").
				password("ca8b947d-e2f4-4fad-bb09-7b2f828b054a"))).
			andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)));

		mvc.perform(get("/transaction?page=0&size=50&type=transaction-type").
			with(user("user").
				password("ca8b947d-e2f4-4fad-bb09-7b2f828b054a"))).
			andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(50)));
	}

}
