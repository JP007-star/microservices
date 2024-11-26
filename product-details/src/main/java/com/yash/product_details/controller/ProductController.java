package com.yash.product_details.controller;

import com.yash.product_details.model.ProductDetails;
import com.yash.product_details.model.ProductRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/details")
public class ProductController {


//    @Autowired
//    RestTemplate restTemplate;

//    @GetMapping("/")
//    public List<ProductDetails> getAllProducts() {
//        List<ProductDetails> ratings = List.of(new ProductDetails(1, "product1"));
//        RestTemplate restTemplate=new RestTemplate();
//        String url = "http://localhost:8083/details/";
//
//        return ratings.stream().map(rating -> {
//            ProductDetails[] productDetailsArray = restTemplate.getForEntity(url , ProductDetails[].class).getBody();
//            return new ProductDetails(rating.getProductId(), rating.getName());
//        }).collect(Collectors.toList());
//    }

    @Autowired
    private WebClient.Builder webClientBuilder;


    @GetMapping("/")
    public Mono<List<ProductDetails>> getAllProducts() {
        String ratingUrl = "http://localhost:8082/rating/";
        WebClient webClient = webClientBuilder.build();
        return webClient.get()
                .uri(ratingUrl)
                .retrieve()
                .bodyToFlux(ProductRating.class).collectList()
                .flatMapMany(Flux::fromIterable)
                .map(productRating -> {
                    ProductDetails productDetails = new ProductDetails();
                    productDetails.setProductId(Integer.parseInt(productRating.getProductId()));
                    productDetails.setName("productName" + productRating.getProductId());
                    productDetails.setProductRating(String.valueOf(productRating.getRating()));
                    return productDetails;
                }).collectList();
        }
    }

