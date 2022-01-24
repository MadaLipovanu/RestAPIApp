package com.javatechie.crud.example.controller;

import com.javatechie.crud.example.entities.tables.pojos.Products;
import com.javatechie.crud.example.service.ProductsService;
import com.javatechie.crud.example.validation.ProductValidation;
import org.jooq.Condition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xmlunit.builder.Input;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
    @Autowired
    private ProductsService service;
    private ProductValidation productValidation = new ProductValidation();

    @GetMapping
    public List<Products> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Object findById(@PathVariable int id) {
        return service.findById(id);
    }

    @GetMapping("/api/validate")
    public Condition checkIfProductNameExists() {
        return productValidation.checkIfProductNameExists();
    }

    @GetMapping("/validatePrice/{id}")
    public ResponseEntity<String> validatePrice(Products price, @PathVariable int id) {
        return ResponseEntity.ok("valid");
    }

    @PostMapping
    public int add(@RequestBody Products product) {
        return service.add(product);
    }

    @PutMapping("/{id}")
    public int update(@RequestBody Products product) {
        return service.update(product);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        service.deleteById(id);
    }

}