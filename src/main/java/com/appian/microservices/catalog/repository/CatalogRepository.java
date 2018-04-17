package com.appian.microservices.catalog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CatalogRepository extends MongoRepository<Inventory, String> {

  Inventory findBySku(String sku);

  Inventory findTopBySkuOrderByTimestampDesc(String sku);

}
