package com.javatechie.crud.example.service;

import com.javatechie.crud.example.entities.Tables;
import com.javatechie.crud.example.entities.tables.pojos.Products;
import com.javatechie.crud.example.repository.CrudOperations;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService implements CrudOperations<Products> {
    @Autowired
    DSLContext context;

    //returns 1 when a product it's added successfully
    public int add(Products product) {
        return context.insertInto(Tables.PRODUCTS, Tables.PRODUCTS.PRODUCTNAME, Tables.PRODUCTS.UNITPRICE,
                        Tables.PRODUCTS.UNIT).values(product.getProductname(), product.getUnitprice(), product.getUnit())
                .returning()
                .execute();
    }

    public Products findById(int id) {
        return context.select()
                .from(Tables.PRODUCTS)
                .where(Tables.PRODUCTS.PRODUCTID.eq(id))
                .fetchInto(Products.class).get(0);
    }

    public List findAll() {
        return context.select()
                .from(Tables.PRODUCTS)
                .fetchInto(Products.class);
    }

    //returns 1 when a product it's deleted successfully from the table
    public int deleteById(int id) {
        return context.delete(Tables.PRODUCTS)
                .where(Tables.PRODUCTS.PRODUCTID.eq(id))
                .execute();
    }

    //returns 1 when a product it's updated successfully in the table
    public int update(Products product) {
        return context.update(Tables.PRODUCTS)
                .set(Tables.PRODUCTS.PRODUCTNAME, product.getProductname())
                .set(Tables.PRODUCTS.UNITPRICE, product.getUnitprice())
                .set(Tables.PRODUCTS.UNIT, product.getUnit())
                .where(Tables.PRODUCTS.PRODUCTID.eq(product.getProductid()))
                .execute();
    }
}