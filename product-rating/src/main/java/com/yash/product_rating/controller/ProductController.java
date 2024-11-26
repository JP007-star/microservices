package com.yash.product_rating.controller;

import com.yash.product_rating.model.ProductRating;
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
@RequestMapping("/rating")
public class ProductController {



    @GetMapping("/")
    public List<ProductRating> getAllProducts(){
        return Collections.singletonList(new ProductRating("23",4.6));
    }




}

