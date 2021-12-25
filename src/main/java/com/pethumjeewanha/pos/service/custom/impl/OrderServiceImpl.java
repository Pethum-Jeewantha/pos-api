/*
 * Copyright (c) 2021 - present Pethum Jeewantha. All rights reserved.
 *  Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.pethumjeewanha.pos.service.custom.impl;

import com.pethumjeewanha.pos.dao.custom.*;
import com.pethumjeewanha.pos.dto.OrderDTO;
import com.pethumjeewanha.pos.dto.OrderDetailDTO;
import com.pethumjeewanha.pos.entity.Customer;
import com.pethumjeewanha.pos.entity.Item;
import com.pethumjeewanha.pos.entity.Order;
import com.pethumjeewanha.pos.entity.OrderDetail;
import com.pethumjeewanha.pos.exception.DuplicateEntityException;
import com.pethumjeewanha.pos.exception.EntityNotFoundException;
import com.pethumjeewanha.pos.service.custom.CustomerService;
import com.pethumjeewanha.pos.service.custom.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static com.pethumjeewanha.pos.service.util.EntityDTOMapper.INSTANCE;

@Transactional
@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private OrderDetailDAO orderDetailDAO;
    @Autowired
    private QueryDAO queryDAO;
    @Autowired
    private ItemDAO itemDAO;
    @Autowired
    private CustomerService customerService;

    @Override
    public void saveOrder(OrderDTO order) {
        final String orderId = order.getOrderId();
        final String customerId = order.getCustomerId();

        if (orderDAO.existsById(orderId)) {
            throw new DuplicateEntityException("Invalid Order ID." + orderId + " already exists");
        }

        if (!customerService.existCustomer(customerId)) {
            throw new EntityNotFoundException("Invalid Customer ID. " + customerId + " doesn't exist");
        }

        System.out.println(order);
        System.out.println(INSTANCE.fromOrderDTO(order));
        orderDAO.save(INSTANCE.fromOrderDTO(order));

        for (OrderDetailDTO detail : order.getOrderDetails()) {
            Item item = itemDAO.findById(detail.getItemCode()).orElseThrow(() -> new EntityNotFoundException(detail.getItemCode() + " does not exists"));
            item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public long getSearchOrdersCount(String query) {
        return queryDAO.countOrders(query);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<OrderDTO> searchOrders(String query, int page, int size) {
        return INSTANCE.toOrderDTO2(queryDAO.findOrders(query, page, size));
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public OrderDTO searchOrder(String orderId) {
        Order order = orderDAO.findById(orderId).orElseThrow(() -> new EntityNotFoundException("Invalid Order ID: " + orderId));
        Customer customer = customerDAO.findById(order.getCustomer().getId()).get();
        BigDecimal orderTotal = orderDetailDAO.findOrderTotal(orderId).get();
        List<OrderDetail> orderDetails = orderDetailDAO.findOrderDetailsByOrderId(orderId);
        return INSTANCE.toOrderDTO(order, customer, orderTotal, orderDetails);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public String generateNewOrderId() {
        String id = orderDAO.getLastOrderId();
        if (id != null) {
            return String.format("OD%03d", (Integer.parseInt(id.replace("OD", "")) + 1));
        } else {
            return "OD001";
        }
    }
}
