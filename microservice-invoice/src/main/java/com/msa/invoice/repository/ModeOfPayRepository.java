
package com.msa.invoice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.msa.invoice.models.ModeOfPay;

@Repository
public interface ModeOfPayRepository extends CrudRepository<ModeOfPay, Long> {

}
