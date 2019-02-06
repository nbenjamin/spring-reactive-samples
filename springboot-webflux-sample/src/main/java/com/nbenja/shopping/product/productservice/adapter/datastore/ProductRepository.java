package com.nbenja.shopping.product.productservice.adapter.datastore;

import com.nbenja.shopping.product.productservice.domain.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

}
