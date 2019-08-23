package com.msa.inventory.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.msa.inventory.dto.InventoryDetailDTO;
import com.msa.inventory.models.Inventory;
import com.msa.inventory.repository.InventoryRepository;
import com.msa.inventory.service.InventoryService;

@RestController
public class InventoryController {

	public static final Logger LOGGER = Logger.getLogger(InventoryController.class.getName());

	@Autowired
	InventoryRepository inventoryRepository;

	@Autowired
	InventoryService inventoryService;

	@RequestMapping(value = "/inventory", method = RequestMethod.GET)
	public Iterable<Inventory> getAllinventory() {
		Iterable<Inventory> inventories = inventoryRepository.findAll();
		LOGGER.info("Fetching the all Inventory " + inventories);
		return inventories;
	}

	@RequestMapping(value = "/inventory/{id}", method = RequestMethod.GET)
	public Inventory getInventoryById(@PathVariable("id") String id) {
		LOGGER.info("Fetching Inventory with id {} " + id);
		return inventoryRepository.findOne(Long.parseLong(id));
	}

	@RequestMapping(value = "/inventory/product/{name}", method = RequestMethod.GET)
	public List<Inventory> getInventoryByName(@PathVariable("name") String productName) {
		LOGGER.info("Fetching Inventory name {} " + productName);
		return inventoryRepository.findByProductName(productName);
	}

	@RequestMapping(value = "/inventory/new/", method = RequestMethod.POST)
	public Inventory addInventory(@RequestBody Inventory inventory) {
		LOGGER.info("Creating a new Inventory : {} " + inventory);
		return inventoryRepository.save(inventory);
	}

	@RequestMapping(value = "/inventory/update", method = RequestMethod.PUT)
	public List<InventoryDetailDTO> updateInventory(@RequestBody List<InventoryDetailDTO> inventoryDetailDTOList) {
		return inventoryService.updateInventory(inventoryDetailDTOList);
	}

	@RequestMapping(value = "/inventory/check", method = RequestMethod.POST)
	public List<InventoryDetailDTO> checkInventory(@RequestBody List<InventoryDetailDTO> inventoryDetailDTOList) {
		return inventoryService.checkForInventory(inventoryDetailDTOList);
	}

	/**
	 * localhost:3333/inventory/check
	 * [{
  			"inventoryId":1,
  			"quantity":9
		},
		{
  			"inventoryId":2,
  			"quantity":200	
		}]
	 * @param inventoryDetailDTOList
	 * @return
	 */
	
	

	/**
	 * localhost:3333/inventory/update
	 * [{
           "inventoryId":1,
           "quantity":10
        },
        {
           "inventoryId":2,
           "quantity":10
        },
        {
           "inventoryId":3,
           "quantity":10
        }]
	 */

	
}
