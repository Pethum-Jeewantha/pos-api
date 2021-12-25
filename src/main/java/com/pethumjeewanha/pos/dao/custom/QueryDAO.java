package com.pethumjeewanha.pos.dao.custom;

import com.pethumjeewanha.pos.dao.SuperDAO;
import com.pethumjeewanha.pos.entity.CustomEntity;

import java.util.HashMap;
import java.util.List;

public interface QueryDAO extends SuperDAO {

    List<HashMap<String, Object>> findOrders(String query);

    long countOrders(String query);

    List<CustomEntity> findOrders(String query, int page, int size);
}
