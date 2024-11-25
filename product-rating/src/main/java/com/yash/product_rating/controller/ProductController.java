package com.yash.product_rating.controller;

import com.yash.product_rating.model.ProductDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rating")
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
    public List<ProductDetails> getAllProducts() {
        List<ProductDetails> ratings = List.of(new ProductDetails(1, "product1"));

        String url = "http://localhost:8083/details/";

        WebClient webClient = webClientBuilder.build();

        return ratings.stream().map(rating -> {
            try {
                ProductDetails[] productDetailsArray = webClient.get()
                        .uri(url)
                        .retrieve()
                        .bodyToMono(ProductDetails[].class)
                        .onErrorResume(WebClientResponseException.class, ex -> {
                            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                                System.err.println("Product not found: " + rating.getProductId());
                                return Mono.empty(); // return an empty Mono
                            }
                            return Mono.error(ex); // propagate other errors
                        })
                        .block();

                if (productDetailsArray != null && productDetailsArray.length > 0) {
                    return productDetailsArray[0];
                }
            } catch (WebClientResponseException e) {
                if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                    System.err.println("Product not found: " + rating.getProductId());
                } else {
                    throw e; // Rethrow the exception if it's not a 404
                }
            }
            return new ProductDetails(rating.getProductId(), rating.getName());
        }).collect(Collectors.toList());
    }




}

