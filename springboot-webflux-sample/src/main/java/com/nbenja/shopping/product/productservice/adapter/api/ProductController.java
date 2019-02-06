package com.nbenja.shopping.product.productservice.adapter.api;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

import com.nbenja.shopping.product.productservice.domain.ProductService;
import com.nbenja.shopping.product.productservice.domain.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

  private ProductService productService;

  public ProductController(
      ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public Flux<Product> getAllProducts() {
    return productService.getAllProducts();
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<Product>> getProduct(@PathVariable String id) {
    return productService.getProduct(id).map(product -> ok(product))
        .defaultIfEmpty(notFound().build());
  }

  @PostMapping
  @ResponseStatus(CREATED)
  public Mono<Product> saveProduct(@RequestBody Product product) {
    return productService.save(product);
  }

  @PutMapping("{id}")
  public Mono<ResponseEntity<Product>> updateProduct(@PathVariable("id") String id,
      @RequestBody Product product) {
    return productService.getProduct(id)
        .flatMap(productService::save)
        .map(p-> ok(p))
        .defaultIfEmpty(notFound().build());
  }

}
