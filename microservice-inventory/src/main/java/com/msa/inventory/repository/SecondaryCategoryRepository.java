
package com.msa.inventory.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.msa.inventory.models.PrimaryCategory;
import com.msa.inventory.models.SecondaryCategory;

@Repository
public interface SecondaryCategoryRepository extends CrudRepository<SecondaryCategory, Long> {

	public List<SecondaryCategory> findByPrimaryCategory(PrimaryCategory primaryCategory);

}
