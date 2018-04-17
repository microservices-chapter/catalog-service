package com.appian.microservices.catalog;

import static com.appian.microservices.catalog.model.Status.INACTIVE;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appian.microservices.catalog.model.Delete;
import com.appian.microservices.catalog.model.Update;
import com.appian.microservices.catalog.repository.Inventory;
import com.appian.microservices.catalog.repository.CatalogRepository;

@Service
public class CatalogService {

  // docker run --name mongodb -p 27017:27017 mongo
  // docker start mongodb
  // curl -H "Content-Type: application/json" -d '{"sku":"10","quantity":"10", "type":"increase"}' -X PUT localhost:8080/products
  // show dbs
  // show collections
  // db.test_table.find( {} )
  // db.test_table.find( { _id: "2" } )

  @Autowired
  private CatalogRepository repository;

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  public List<Inventory> list() {
    // for reports
    logger.debug("Retrieving all products changes");
    return repository.findAll();
  }

  @Transactional
  public List<Inventory> getInventory() {
    logger.info("Retrieving all products");

    // Get the latest entry for each product by grouping them by sku and sorting by timestamp
//    List<Inventory> catalog = Lists.newArrayList(repository.findAll()
//        .stream()
//        .collect(Collectors.toMap(Inventory::getSku, Function.identity(),
//            BinaryOperator.maxBy(comparingLong(Inventory::getTimestamp))))
//        .values());
//
//    logger.info("Found {} product(s) in catalog", catalog.size());
//    return catalog;
    return null;
  }

  public Inventory get(String sku) {
    logger.info("Retrieving sku: {}", sku);
    return repository.findTopBySkuOrderByTimestampDesc(sku);
  }

  @Transactional
  public Inventory update(Update update) {
    Inventory inventory = newInventoryEntry(update.getSku());

    int qty;
    switch (update.getType()) {
      case DECREASE:
        logger.info("Decreasing sku {} by {}", update.getSku(), update.getQuantity());

        qty = inventory.getQuantity() - update.getQuantity();
        if (qty < 0) {
          logger.error("Cannot decrease sku {} by {} since there are only {} items",
              update.getSku(), update.getQuantity(), inventory.getQuantity());
          throw new RuntimeException("invalid quantity: " + update.getQuantity());
        }
        break;
      case INCREASE:
        logger.info("Increasing sku {} by {}", update.getSku(), update.getQuantity());

        qty = inventory.getQuantity() + update.getQuantity();
        break;
      default:
        logger.info("Unsupported update type: {}", update.getType());
        throw new RuntimeException("Unsupported update type: " + update.getType());
    }

    inventory.setQuantity(qty);
    return repository.save(inventory);
  }

  @Transactional
  public Inventory delete(Delete delete) {
    logger.info("Deleting sku {}", delete.getSku());

    Inventory inventory = newInventoryEntry(delete.getSku());
    inventory.setStatus(INACTIVE);
    return repository.save(inventory);
  }

  private Inventory newInventoryEntry(String sku) {
    Inventory previous = repository.findBySku(sku);
    Inventory inventory = new Inventory();
    inventory.setSku(sku);
    if (previous != null) {
      inventory.setQuantity(previous.getQuantity());
      inventory.setStatus(previous.getStatus());
    }

    return inventory;
  }
}
