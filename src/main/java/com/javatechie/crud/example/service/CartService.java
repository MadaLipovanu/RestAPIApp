package com.javatechie.crud.example.service;

import com.javatechie.crud.example.entities.Tables;
import com.javatechie.crud.example.entities.tables.pojos.Cart;
import com.javatechie.crud.example.repository.CrudOperations;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CartService --- program to implement CRUD operations for table Cart.
 * The interface "CrudOperations" is defined in repository.
 * The DSLContext object will be injected into the property context at run time using @Autowired annotation
 */

@Service
public class CartService implements CrudOperations<Cart> {
    @Autowired
    DSLContext context;

    //returns 1 when a cart it's added successfully
    public int add(Cart cart) {
        return context.insertInto(Tables.CART, Tables.CART.CARTID, Tables.CART.ORDERDATE)
                .values(cart.getCartid(), cart.getOrderdate())
                .returning()
                .execute();
    }

    @Override
    public Cart findById(int id) {
        return context.select()
                .from(Tables.CART)
                .where(Tables.CART.CARTID.eq(id))
                .fetchInto(Cart.class).get(0);
    }

    @Override
    public List findAll() {
        return context.select()
                .from(Tables.CART)
                .fetchInto(Cart.class);
    }

    //returns 1 when a cart it's updated successfully
    public int update(Cart cart) {
        return context.update(Tables.CART)
                .set(Tables.CART.ORDERDATE, cart.getOrderdate())
                .where(Tables.CART.CARTID.eq(cart.getCartid()))
                .execute();
    }

    //returns 1 when a cart it's deleted successfully from the list
    public int deleteById(int id) {
        return context.delete(Tables.CART)
                .where(Tables.CART.CARTID.eq(id))
                .execute();
    }

}