package com.nbenja.shopping.product.productservice.adapter.service;

import com.nbenja.shopping.product.productservice.adapter.datastore.ProductRepository;
import com.nbenja.shopping.product.productservice.domain.Product;
import com.nbenja.shopping.product.productservice.domain.ProductService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements
    ProductService {

  private ProductRepository productRepository;

  public ProductServiceImpl(
      ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public Flux<Product> getAllProducts() {
    return productRepository.findAll();
  }

  @Override
  public Mono<Product> getProduct(String id) {
    return productRepository.findById(id);
  }

  @Override
  public Mono<Product> save(Product product) {
    return productRepository.save(product);
  }
}
