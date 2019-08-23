package com.msa.customer.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.msa.customer.client.InvoiceServiceClient;
import com.msa.customer.dto.CustomerOrders;
import com.msa.customer.dto.InventoryCheckResultDTO;
import com.msa.customer.dto.Invoice;
import com.msa.customer.dto.OrderDetail;
import com.msa.customer.models.Customer;
import com.msa.customer.repository.CustomerRepository;
import com.msa.customer.services.InventoryService;
import com.msa.customer.services.InvoiceService;

@RestController
public class CustomerController {
	public static final Logger LOGGER = Logger.getLogger(CustomerController.class.getName());

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	InvoiceService invoiceService;

	@Autowired
	InvoiceServiceClient invoiceServiceClient;

	@Autowired
	InventoryService inventoryService;

	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	public Iterable<Customer> getAllCustomers() {
		Iterable<Customer> customers = customerRepository.findAll();
		LOGGER.info("Fetching the all Customers " + customers);
		return customers;
	}

	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public Customer getCustomerById(@RequestParam("id") String id) {
		LOGGER.info("Fetching Customer with id {} " + id);
		return customerRepository.findOne(Long.parseLong(id));
	}

	@RequestMapping(value = "/customers/name/{name}", method = RequestMethod.GET)
	public List<Customer> getCustomerByName(@PathVariable("name") String customerName) {
		LOGGER.info("Fetching Customer with name {} " + customerName);
		return customerRepository.findByCustomerName(customerName);
	}

	@RequestMapping(value = "/customers/new/", method = RequestMethod.POST)
	public Customer addCustomer(@RequestBody Customer customer) {
		LOGGER.info("Creating a new Customer : {} " + customer);
		return customerRepository.save(customer);
	}

	@RequestMapping(value = "/customers/{id}/orders", method = RequestMethod.GET)
	public CustomerOrders getCustomerOrders(@PathVariable("id") String id) {
		// System.out.println("Calling a load balanced Rest template");
		// return invoiceService.getInvoices(id);
		CustomerOrders customerOrders = new CustomerOrders();
		Customer customer = customerRepository.findOne(Long.parseLong(id));
		LOGGER.info("Fetching CustomerOrders " + customer.getId());
		customerOrders.setCustomer(customer);
		LOGGER.info("Calling feign client to get invoices" + id);
		List<Invoice> invoiceList = invoiceServiceClient.getInvoices(id);
		LOGGER.info("Fetching Invoive List " + invoiceList.size());// TBD
		customerOrders.setInvoiceList(invoiceList);
		return customerOrders;
	}

	/**
	 * http://localhost:2222/customers/order { "customerId":1, "modePayId":2,
	 * "cashierName":"hanuman", "items":[ { "itemId":2, "quantity":5,
	 * "unitCost":10
	 * 
	 * } ] }
	 * 
	 * @param orderDetail
	 * @return
	 */
	// TODO - design for failure
	@RequestMapping(value = "/customers/order", method = RequestMethod.POST)
	public OrderDetail placeOrder(@RequestBody OrderDetail orderDetail) {
		List<InventoryCheckResultDTO> inventoryCheckResultDTOs = inventoryService.checkAvailability(orderDetail);
		for (InventoryCheckResultDTO icrd : inventoryCheckResultDTOs) {
			LOGGER.info(icrd.toString());
		}
		// TODO - do something if the requested inventory is not there
		OrderDetail orderPlaced = invoiceService.createInvoice(orderDetail);
		inventoryService.updateInventory(orderPlaced);
		LOGGER.info("Creating a new Customer Order " + orderPlaced);
		return orderPlaced;
	}
}
