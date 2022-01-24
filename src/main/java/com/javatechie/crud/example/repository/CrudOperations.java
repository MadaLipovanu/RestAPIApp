package com.javatechie.crud.example.repository;

import java.util.List;

public interface CrudOperations<T> {
    int add(T object);

    T findById(int id);

    List<T> findAll();

    int deleteById(int id);

    int update(T object);
}
