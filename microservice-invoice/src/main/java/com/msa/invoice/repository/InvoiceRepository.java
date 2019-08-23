package com.msa.invoice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.msa.invoice.models.Invoice;

@Repository
public interface InvoiceRepository  extends CrudRepository<Invoice, Long> {
	List<Invoice> findByCustomerId(Long customerId);
	   
}



