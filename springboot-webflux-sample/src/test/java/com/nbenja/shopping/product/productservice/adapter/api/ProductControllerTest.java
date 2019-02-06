package com.nbenja.shopping.product.productservice.adapter.api;

import com.nbenja.shopping.product.productservice.adapter.datastore.ProductRepository;
import com.nbenja.shopping.product.productservice.domain.Product;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProductControllerTest {

  @Autowired
  private ProductController productController;
  @Autowired
  private ProductRepository productRepository;

  private WebTestClient testClient;
  private List<Product> products;

  @BeforeEach
  public void beforeEach() {
    testClient = WebTestClient.bindToController(productController)
        .configureClient().baseUrl("/products").build();
    products = productRepository.findAll().collectList().block();
  }

  @Test
  public void getAllProducts_withValidEndpoint_returnListOfProducts() {
    testClient.get().exchange().expectStatus().isOk().expectBodyList(Product.class)
        .isEqualTo(products);
  }

  @Test
  public void getAllProducts_withInValidEndpoint_return404() {
    testClient.get().uri("/invalid").exchange().expectStatus().isNotFound();
  }

  @Test
  public void getProduct_withValidEndpoint_returnProduct() {
    testClient.get().uri("/{id}", products.get(0).getId()).exchange().expectStatus().isOk()
        .expectBody(Product.class).isEqualTo(products.get(0));
  }
}