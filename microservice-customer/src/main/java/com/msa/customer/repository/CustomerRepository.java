package com.msa.customer.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.msa.customer.models.Customer;

@Repository
public interface CustomerRepository  extends CrudRepository<Customer, Long> {
	   List<Customer> findByCustomerName(String customerName); 

}



