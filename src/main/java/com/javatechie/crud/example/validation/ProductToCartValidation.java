package com.javatechie.crud.example.validation;

import com.javatechie.crud.example.entities.tables.pojos.Cart;
import com.javatechie.crud.example.entities.tables.pojos.ProductToCart;
import com.javatechie.crud.example.entities.tables.pojos.Products;
import com.javatechie.crud.example.service.CartService;
import com.javatechie.crud.example.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductToCartValidation {
    @Autowired
    private ProductsService productsService;

    @Autowired
    private CartService cartService;

    public boolean checkProductId(ProductToCart productToCart) {
        Products products = productsService.findById(productToCart.getProductid());
        return !(products == null);
    }

    public boolean checkCartId(ProductToCart productToCart) {
        Cart cart = cartService.findById(productToCart.getCartid());
        return !(cart == null);
    }

    public boolean validateQuantity(ProductToCart productToCart) {
        return productToCart.getProductquantity() > 0 ? true : false;
    }

}
