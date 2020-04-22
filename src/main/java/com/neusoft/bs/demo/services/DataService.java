package com.neusoft.bs.demo.services;

import java.util.List;

public interface DataService<T> {
    T create(T one);

    T delete(T one);

    T update(T one);

    List<T> list();

    T findOne(String primaryKey);

    List<T> query(T condition);
}