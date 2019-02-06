package com.nbenja.shopping.product.productservice.config;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import com.nbenja.shopping.product.productservice.adapter.api.ProductHandler;
import com.nbenja.shopping.product.productservice.adapter.datastore.ProductRepository;
import com.nbenja.shopping.product.productservice.domain.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

@Configuration
public class ProductServiceConfiguration {

  /**
   * Initialize your database
   * @param productRepository
   * @return Save products in to database
   */
  @Bean
  public CommandLineRunner initialize(
      ProductRepository productRepository) {
    return args -> {
      Flux<Product> products = Flux.just(
          new Product("IPhone X", "Apple IPhone X, 32 GB, Gold Color", 649.99),
          new Product("IPhone XR", "Apple IPhone XS, 64 GB, Gold Color", 749.99),
          new Product("IPhone XS", "Apple IPhone XS, 128 GB, Gold Color", 949.99)
      ).flatMap(productRepository::save);

      products.thenMany(productRepository.findAll()).subscribe(System.out::println);
    };
  }

  /**
   * Router function with separate route for each endpoint
   * @param productHandler
   * @return RouterFunction
   */
  @Bean
  public RouterFunction<ServerResponse> routerFunction(ProductHandler productHandler) {
    return route(GET("/v1/products").and(accept(APPLICATION_JSON)), productHandler::getAllProducts)
                .andRoute(GET("/v1/products/{id}").and(accept(APPLICATION_JSON)), productHandler::getProduct)
                .andRoute(POST("/v1/products").and(accept(APPLICATION_JSON)), productHandler::saveProduct)
                .andRoute(PUT("/v1/products/{id}").and(accept(APPLICATION_JSON)), productHandler::updateProduct);
  }


  /**
   * Router function with nested route, so no need to repeat the base url for each endpoint
   * @param productHandler
   * @return RouterFunction
   */
  @Bean
  public RouterFunction<ServerResponse> routerNestedFunction(ProductHandler productHandler) {
    return route(GET("/v2/products").and(accept(APPLICATION_JSON)), productHandler::getAllProducts)
        .andRoute(GET("/v2/products/{id}").and(accept(APPLICATION_JSON)), productHandler::getProduct)
        .andRoute(POST("/v2/products").and(accept(APPLICATION_JSON)), productHandler::saveProduct)
        .andRoute(PUT("/v2/products/{id}").and(accept(APPLICATION_JSON)), productHandler::updateProduct);
  }
}
