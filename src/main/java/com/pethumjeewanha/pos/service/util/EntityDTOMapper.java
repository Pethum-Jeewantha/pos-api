/*
 * Copyright (c) 2021 - present Pethum Jeewantha. All rights reserved.
 *  Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.pethumjeewanha.pos.service.util;

import com.pethumjeewanha.pos.dto.CustomerDTO;
import com.pethumjeewanha.pos.dto.ItemDTO;
import com.pethumjeewanha.pos.dto.OrderDTO;
import com.pethumjeewanha.pos.dto.OrderDetailDTO;
import com.pethumjeewanha.pos.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface EntityDTOMapper {

    EntityDTOMapper INSTANCE = Mappers.getMapper(EntityDTOMapper.class);

    CustomerDTO toCustomerDTO(Customer c);

    Customer fromCustomerDTO(CustomerDTO c);

    List<CustomerDTO> toCustomerDTOList(List<Customer> customers);

    List<Customer> fromCustomerDTOList(List<CustomerDTO> customers);

    ItemDTO toItemDTO(Item i);

    Item fromItemDTO(ItemDTO i);

    List<ItemDTO> toItemDTOList(List<Item> items);

    List<Item> fromItemDTOList(List<ItemDTO> items);

    /*@Mapping(source = "orderId", target = "id")
    @Mapping(source = "orderDate", target = "date")
    @Mapping(source = "customerId", target = "customer.id")
    @Mapping(source = "customerName", target = "customer.name")
    Order fromOrderDTO(OrderDTO o);*/

    default Order fromOrderDTO(OrderDTO o) {
        return new Order(o.getOrderId(), Date.valueOf(o.getOrderDate()), new Customer(o.getCustomerId(), null, null), fromOrderDetailDTOList(o.getOrderId(), o.getOrderDetails()));
    }

    @Mapping(source = "orderId", target = "orderDetailPK.orderId")
    @Mapping(source = "od.itemCode", target = "orderDetailPK.itemCode")
    @Mapping(source = "od.qty", target = "qty")
    OrderDetail fromOrderDetailDTO(String orderId, OrderDetailDTO od);

    default Set<OrderDetail> fromOrderDetailDTOList(String orderId, List<OrderDetailDTO> orderDetails) {
        return orderDetails.stream().map(od -> fromOrderDetailDTO(orderId, od)).collect(Collectors.toSet());
    }

/*    @Mapping(source = "orderId", target = "orderDetailPK.orderId")
    @Mapping(source = "itemCode", target = "orderDetailPK.itemCode")
    List<OrderDetail> fromOrderDetailDTOList(String orderId, List<OrderDetailDTO> orderDetails);*/

    OrderDTO toOrderDTO(HashMap<String, Object> or);

    OrderDTO toOrderDTO(CustomEntity ce);

    OrderDetailDTO toOrderDetailDTO(OrderDetail orderDetail);

    @Mapping(source = "orderDetailPK.itemCode", target = "itemCode")
    List<OrderDetailDTO> toOrderDetailDTOList(List<OrderDetail> orderDetails);

    @Mapping(source = "order.id", target = "orderId")
    @Mapping(source = "order.date", target = "orderDate")
    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "customer.name", target = "customerName")
    OrderDTO toOrderDTO(Order order, Customer customer, BigDecimal orderTotal, List<OrderDetail> orderDetails);

    List<OrderDTO> toOrderDTO1(List<HashMap<String, Object>> orderRecords);

    List<OrderDTO> toOrderDTO2(List<CustomEntity> orderRecords);
}
