package com.pethumjeewanha.pos.dao.custom;

import com.pethumjeewanha.pos.dao.CrudDAO;
import com.pethumjeewanha.pos.entity.Customer;

public interface CustomerDAO extends CrudDAO<Customer, String> {

    String getLastCustomerId();
}