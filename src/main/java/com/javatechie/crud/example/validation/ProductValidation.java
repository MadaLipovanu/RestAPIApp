package com.javatechie.crud.example.validation;

import com.javatechie.crud.example.entities.Tables;
import com.javatechie.crud.example.entities.tables.pojos.Products;
import org.apache.tomcat.jni.Error;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.jooq.Condition;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedList;
import java.util.List;

public class ProductValidation {

    public Condition checkIfProductNameExists() {
        return Tables.PRODUCTS.PRODUCTNAME.isNotNull();
    }

    public boolean validatePrice(Products product) {
        return product.getUnitprice() > 0;
//        if (product.getUnitprice() > 0) {
//            System.out.println("Price ok");
//        } else if (product.getUnitprice() == 0) {
//            System.out.println("nok");
//        }
//        return false;
    }
}
