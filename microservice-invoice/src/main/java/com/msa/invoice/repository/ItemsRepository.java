
package com.msa.invoice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.msa.invoice.models.Invoice;
import com.msa.invoice.models.Items;

@Repository
public interface ItemsRepository extends CrudRepository<Items, Long> {

	List<Items> findByInvoiceId(Invoice invoiceId);
}
