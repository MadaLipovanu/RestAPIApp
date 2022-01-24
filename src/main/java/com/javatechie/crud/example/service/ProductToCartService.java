package com.javatechie.crud.example.service;

import com.javatechie.crud.example.entities.Tables;
import com.javatechie.crud.example.entities.tables.pojos.ProductToCart;
import com.javatechie.crud.example.repository.CrudOperations;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductToCartService implements CrudOperations<ProductToCart> {
    @Autowired
    DSLContext context;

    //returns 1 when a product it's added successfully in the table
    public int add(ProductToCart productToCart) {
        return context.insertInto(Tables.PRODUCT_TO_CART, Tables.PRODUCT_TO_CART.CARTID, Tables.PRODUCT_TO_CART.PRODUCTID, Tables.PRODUCT_TO_CART.PRODUCTQUANTITY)
                .values(productToCart.getCartid(), productToCart.getProductid(), productToCart.getProductquantity()) //getOrderdate()
                .execute();
    }

    //view cart content by id
    public ProductToCart findById(int id) {
        return (ProductToCart) context.select()
                .from(Tables.PRODUCT_TO_CART)
                .where(Tables.PRODUCT_TO_CART.CARTID.eq(id))
                .fetchInto(ProductToCart.class);
    }

    @Override
    public List findAll() {
        return null;
    }

    //return total price of a cart by idCART;
    public double findAllPrices(int id) {
        List<Integer> quantity = context.select(Tables.PRODUCT_TO_CART.PRODUCTQUANTITY)
                .from(Tables.PRODUCT_TO_CART)
                .where(Tables.PRODUCT_TO_CART.CARTID.eq(id))
                .fetch(0, int.class);

        List<Double> unitPrice = context.select(Tables.PRODUCTS.UNITPRICE)
                .from(Tables.PRODUCT_TO_CART).join(Tables.PRODUCTS)
                .on(Tables.PRODUCT_TO_CART.PRODUCTID.eq(Tables.PRODUCTS.PRODUCTID))
                .where(Tables.PRODUCT_TO_CART.CARTID.eq(id))
                .fetch(0, double.class);

        double totalPriceOfACart = 0.0;
        for (int i = 0; i < quantity.size(); i++) {
            totalPriceOfACart = quantity.get(i) * unitPrice.get(i);
        }

        return totalPriceOfACart;
    }

    //returns 1 when a product it's updated successfully in the table
    public int update(ProductToCart productToCart) {
        return context.update(Tables.PRODUCT_TO_CART)
                .set(Tables.PRODUCT_TO_CART.CARTID, productToCart.getCartid())
                .set(Tables.PRODUCT_TO_CART.PRODUCTID, productToCart.getProductid())
                .set(Tables.PRODUCT_TO_CART.PRODUCTQUANTITY, productToCart.getProductquantity())
                .where(Tables.PRODUCT_TO_CART.CARTID.eq(productToCart.getCartid())
                        .and(Tables.PRODUCT_TO_CART.PRODUCTID.eq(productToCart.getProductid())))
                .execute();
    }

    @Override
    public int deleteById(int id) {
        return 0;
    }

}