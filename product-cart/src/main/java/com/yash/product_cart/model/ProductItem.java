package com.yash.product_cart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
@AllArgsConstructor
public class ProductItem {
    private String productName;
    private double price;
    private String description;
    private double rating;
}
