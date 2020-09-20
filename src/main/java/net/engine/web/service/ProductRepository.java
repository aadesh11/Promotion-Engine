package net.engine.web.service;

import net.engine.web.model.SkuProduct;

import java.util.List;

/**
 * Product repository, we can add other methods also for CRUD opertions.
 */
public interface ProductRepository {

    //To query the database and return all the product saved in DB.
    List<SkuProduct> findAll();
}
