package com.yash.product_cart.controller;

import com.yash.product_cart.model.ProductItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping("/")
    public List<ProductItem> getAllProducts(){
        return Collections.singletonList(new ProductItem("productName",23,"this product description",4.6));
    }
}
