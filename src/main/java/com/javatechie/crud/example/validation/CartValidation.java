package com.javatechie.crud.example.validation;

import com.javatechie.crud.example.entities.tables.pojos.Cart;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDateTime;

public class CartValidation {

    public boolean checkIfCartFieldsExists(Cart cart) {
        return cart.getOrderdate() == null;
    }

    public boolean getDateAsLocalDate(Cart cart) {
        LocalDate localDate = LocalDate.of(2022, 01, 20);
        return cart.getOrderdate().isBefore(ChronoLocalDateTime.from(localDate));
    }

}

