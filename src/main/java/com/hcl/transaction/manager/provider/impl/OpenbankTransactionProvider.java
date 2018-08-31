package com.hcl.transaction.manager.provider.impl;

import com.hcl.transaction.manager.exception.ExternalProviderException;
import com.hcl.transaction.manager.model.Transaction;
import com.hcl.transaction.manager.provider.TransactionProvider;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.math.BigDecimal;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Pavel Savinov
 */
@Service
public class OpenbankTransactionProvider implements TransactionProvider {

	@Value("${openbank.transactions.endpoint}")
	private String endpoint;

	private static final Logger logger = LogManager.getLogger(
		OpenbankTransactionProvider.class);

	@Override
	public List<Transaction> getTransactions() throws
		ExternalProviderException {

		// Initialize custom request factory to pass certificate check

		TrustStrategy acceptingTrustStrategy = new TrustStrategy() {

			@Override
			public boolean isTrusted(
					X509Certificate[] x509Certificates, String s)
				throws CertificateException {

				return true;
			}
		};

		CloseableHttpClient httpClient = null;

		try {
			SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(
				null, acceptingTrustStrategy).build();

			SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(
				sslContext, new NoopHostnameVerifier());

			httpClient =
				HttpClients.custom().setSSLSocketFactory(csf).build();

		}
		catch (Exception e) {
			logger.error("Unable to initialize HTTP client", e);

			throw new ExternalProviderException(e);
		}

		HttpComponentsClientHttpRequestFactory requestFactory =
			new HttpComponentsClientHttpRequestFactory();

		requestFactory.setHttpClient(httpClient);

		// Get transactions list from the openbank endpoint

		RestTemplate restTemplate = new RestTemplate(requestFactory);

		Map<String, Object> response = restTemplate.getForObject(
			endpoint, Map.class);

		return mapTransactions(response);
	}

	private List<Transaction> mapTransactions(Map<String,Object> response) {
		List<Map<String, Object>> objects =
			(List<Map<String, Object>>)response.get("transactions");

		logger.info("Openbank transactions list size: " + objects.size());

		List<Transaction> transactions = new ArrayList<>();

		// Map openbank transactions to our structure

		objects.forEach(
			object -> {
				Transaction transaction = new Transaction();

				transaction.setId(object.get("id").toString());

				Map<String, Object> thisAccount =
					(Map<String, Object>) object.get("this_account");

				transaction.setAccountId(
					thisAccount.getOrDefault("id", "").toString());

				Map<String, Object> otherAccount =
					(Map<String, Object>) object.get("other_account");

				transaction.setCounterpartyAccount(
					otherAccount.getOrDefault("number", "").toString());

				Map<String, Object> holder =
					(Map<String, Object>) otherAccount.get("holder");

				transaction.setCounterpartyName(
					holder.getOrDefault("name", "").toString());

				Map<String, Object> metadata =
					(Map<String, Object>) otherAccount.getOrDefault(
						"metadata", new HashMap<>());

				transaction.setCounterpartyLogoPath(
					metadata.compute(
						"image_URL",
						(key, value) -> value != null ? value : "").toString());

				Map<String, Object> details =
					(Map<String, Object>) object.get("details");

				transaction.setType(
					details.compute(
						"type",
						(key, value) -> value != null ? value : "").toString());

				transaction.setDescription(
					details.compute(
						"description",
						(key, value) -> value != null ? value : "").toString());

				Map<String, Object> value =
					(Map<String, Object>) details.get("value");

				transaction.setInstructedAmount(
					new BigDecimal(
						value.getOrDefault(
							"amount", BigDecimal.ZERO).toString()));

				transaction.setInstructedCurrency(
					Currency.getInstance(
						value.getOrDefault("currency", "").toString()));

				transaction.setTransactionAmount(
					transaction.getInstructedAmount());
				transaction.setInstructedCurrency(
					transaction.getInstructedCurrency());

				if (logger.isDebugEnabled()) {
					logger.debug("Parsed transaction " + transaction.getId());
				}

				transactions.add(transaction);
			}
		);

		return transactions;
	}

}
