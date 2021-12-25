package com.pethumjeewanha.pos.dao.custom.impl;

import com.pethumjeewanha.pos.dao.CrudDAOImpl;
import com.pethumjeewanha.pos.dao.custom.CustomerDAO;
import com.pethumjeewanha.pos.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDAOImpl extends CrudDAOImpl<Customer, String> implements CustomerDAO {

    @Override
    public String getLastCustomerId() {
        em.flush();
        return (String) em.createNativeQuery("SELECT id FROM customer ORDER BY id DESC LIMIT 1").getSingleResult();
    }
}
