/*
 * Copyright (c) 2021 - present Pethum Jeewantha. All rights reserved.
 *  Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.pethumjeewanha.pos.dao.custom.impl;

import com.pethumjeewanha.pos.dao.CrudDAOImpl;
import com.pethumjeewanha.pos.dao.custom.OrderDAO;
import com.pethumjeewanha.pos.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderDAOImpl extends CrudDAOImpl<Order, String> implements OrderDAO {

    @Override
    public String getLastOrderId() {
        em.flush();
        return (String) em.createNativeQuery("SELECT id FROM `order` ORDER BY id DESC LIMIT 1").getSingleResult();
    }
}
