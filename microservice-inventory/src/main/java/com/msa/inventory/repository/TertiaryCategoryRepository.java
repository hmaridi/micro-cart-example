
package com.msa.inventory.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.msa.inventory.models.SecondaryCategory;
import com.msa.inventory.models.TertiaryCategory;

@Repository
public interface TertiaryCategoryRepository extends CrudRepository<TertiaryCategory, Long> {

	public List<TertiaryCategory> findBySecondaryCategory(SecondaryCategory secondaryCategory);
	
}