package com.pethumjeewanha.pos.dao.custom;

import com.pethumjeewanha.pos.dao.CrudDAO;
import com.pethumjeewanha.pos.entity.OrderDetail;
import com.pethumjeewanha.pos.entity.OrderDetailPK;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface OrderDetailDAO extends CrudDAO<OrderDetail, OrderDetailPK> {

    Optional<BigDecimal> findOrderTotal(String orderId);

    List<OrderDetail> findOrderDetailsByOrderId(String orderId);
}
