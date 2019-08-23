
package com.msa.inventory.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.msa.inventory.models.PrimaryCategory;

@Repository
public interface PrimaryCategoryRepository extends CrudRepository<PrimaryCategory, Long> {

}
