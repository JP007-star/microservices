package com.yash.product_details.controller;

import com.yash.product_details.model.ProductDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/details")
public class ProductController {

    @GetMapping("/")
    public List<ProductDetails> getAllProducts(){
        return Collections.singletonList(new ProductDetails(23,"productName"));
    }
}

