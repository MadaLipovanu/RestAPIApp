package com.javatechie.crud.example.controller;

import com.javatechie.crud.example.entities.tables.pojos.Cart;
import com.javatechie.crud.example.service.CartService;
import com.javatechie.crud.example.validation.CartValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService service;
    private CartValidation cartValidation = new CartValidation();

    @GetMapping
    public List<Cart> findAll() {
        return service.findAll();
    }

    @GetMapping("/api/validate1")
    public boolean checkIfCartFieldsExists(Cart cart) {
        return cartValidation.checkIfCartFieldsExists(cart);
    }

//    @GetMapping("/api/test/validate2"){
//        public boolean getDateAsLocalDateTime(Cart cart){
//            return cartValidation.getDateAsLocalDateTime(cart.cartValidation);
//        }
//    }

    @GetMapping("/{id}")
    public Object findById(@PathVariable int id) {
        return service.findById(id);
    }

    @PostMapping
    public int add(@RequestBody Cart cart) {
        return service.add(cart);
    }

    @PutMapping("/{id}")
    public int update(@RequestBody Cart cart) {
        return service.update(cart);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        service.deleteById(id);
    }

}