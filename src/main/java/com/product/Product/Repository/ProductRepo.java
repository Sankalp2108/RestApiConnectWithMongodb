package com.product.Product.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.product.Product.model.Product;

@Repository
public interface ProductRepo extends MongoRepository<Product, String> {

}
