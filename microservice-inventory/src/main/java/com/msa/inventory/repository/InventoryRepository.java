package com.msa.inventory.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.msa.inventory.models.Inventory;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, Long> {
	public List<Inventory> findByProductName(String productName);

}
