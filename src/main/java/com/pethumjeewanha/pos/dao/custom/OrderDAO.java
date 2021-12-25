package com.pethumjeewanha.pos.dao.custom;

import com.pethumjeewanha.pos.dao.CrudDAO;
import com.pethumjeewanha.pos.entity.Order;

public interface OrderDAO extends CrudDAO<Order, String> {

    String getLastOrderId();
}
