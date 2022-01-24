package com.javatechie.crud.example.controller;

import com.javatechie.crud.example.entities.tables.pojos.ProductToCart;
import com.javatechie.crud.example.service.ProductToCartService;
import com.javatechie.crud.example.validation.ProductToCartValidation;
import org.assertj.core.internal.ErrorMessages;
import org.jooq.Condition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-to-cart")
public class ProductToCartController {
    @Autowired
    private ProductToCartService service;
    private ProductToCartValidation productToCartValidation;

    @GetMapping("/{id}")
    public List viewCartContent(@PathVariable int id) {
        return service.findAll();
    }

    public double getTotalPriceOfACart(@PathVariable int id) {
        return service.findAllPrices(id);
    }

    //return 1 if the quantity has the correct form;
    public boolean validateQuantity(ProductToCart productToCart) {
        return productToCartValidation.validateQuantity(productToCart);
    }

    @PostMapping
    public void addNewProductInCart(@RequestBody ProductToCart productToCart) {
       if(productToCartValidation.checkProductId(productToCart) == false){
           System.out.println("No product with this id. ");
       } else if(productToCartValidation.checkCartId(productToCart) == false){
           System.out.println("No cart with this id. ");
    } else{
       service.add(productToCart);
       }
    }

    public boolean checkProductId(ProductToCart productToCart){
        return productToCartValidation.checkProductId(productToCart);
    }

    public boolean checkCartId(ProductToCart productToCart){
        return productToCartValidation.checkCartId(productToCart);
    }

    @PutMapping("/{id}")
    public int updateProductInACart(@RequestBody ProductToCart productToCart) {
        return service.update(productToCart);
    }

}
