package com.yash.product_cart.controller;

import com.yash.product_cart.model.ProductDetails;
import com.yash.product_cart.model.ProductItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping("/")
    public Mono<List<ProductItem>> getAllProducts() {
        String ratingUrl = "http://localhost:8083/details/";
        WebClient webClient = webClientBuilder.build();
        return webClient.get()
                .uri(ratingUrl)
                .retrieve()
                .bodyToFlux(ProductDetails.class).collectList()
                .flatMapMany(Flux::fromIterable)
                .map(productDetails -> { // Generate ProductItem object using ProductRating
                    ProductItem productItem = new ProductItem();
                    productItem.setProductName(productDetails.getName());
                    productItem.setPrice(47.00);
                    productItem.setDescription("Description for Product " + productDetails.getProductId());
                    productItem.setRating(Double.parseDouble(productDetails.getProductRating()));
                    return productItem;
                }).collectList();
    }

}
