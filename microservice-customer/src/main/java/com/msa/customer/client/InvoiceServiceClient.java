
package com.msa.customer.client;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.msa.customer.controller.CustomerController;
import com.msa.customer.dto.Invoice;

@FeignClient(name = "invoice-service", fallback = InvoiceServiceClient.InvoiceClientFallback.class)
public interface InvoiceServiceClient {

	public static final Logger LOGGER = Logger.getLogger(CustomerController.class.getName());

	@RequestMapping(method = RequestMethod.GET, value = "/invoice/custid/{custid}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	List<Invoice> getInvoices(@PathVariable("custid") String custid);

	/*
	 * Circuit Breaker code, This will run if Invoice-service is down
	 * 
	 */
	@Component
	static class InvoiceClientFallback implements InvoiceServiceClient {
		@Override
		public List<Invoice> getInvoices(String custId) {
			List<Invoice> invoiceList = new ArrayList<Invoice>();
			LOGGER.info("Service is down, please try later");
			return invoiceList;
		}
	}
}