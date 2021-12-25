package com.pethumjeewanha.pos.service.custom;

import com.pethumjeewanha.pos.dto.OrderDTO;
import com.pethumjeewanha.pos.service.SuperService;

import java.util.List;

public interface OrderService extends SuperService {

    void saveOrder(OrderDTO order);

    long getSearchOrdersCount(String query);

    List<OrderDTO> searchOrders(String query, int page, int size);

    OrderDTO searchOrder(String orderId);

    String generateNewOrderId();
}
