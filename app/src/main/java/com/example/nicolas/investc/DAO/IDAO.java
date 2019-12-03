package com.example.nicolas.investc.DAO;

import com.example.nicolas.investc.Beans.Indicateur;

import java.util.List;

/**
 * Created by NICOLAS on 08/06/2018.
 */

public interface IDAO<T> {

    long insert(T t);

    T selectById(int id);

    List<T> selectAll();

    void deleteAll();

    void deleteById(int id);

    void updateById(int id, T t);

    List<Indicateur> selectTous();
}