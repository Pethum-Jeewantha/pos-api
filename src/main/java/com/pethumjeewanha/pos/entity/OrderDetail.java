/*
 * Copyright (c) 2021 - present Pethum Jeewantha. All rights reserved.
 *  Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.pethumjeewanha.pos.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "order_detail")
@Entity
public class OrderDetail implements SuperEntity {
    @EmbeddedId
    private OrderDetailPK orderDetailPK;
    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;
    @Column(nullable = false)
    private int qty;
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", insertable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private Order order;
    @ManyToOne
    @JoinColumn(name = "item_code", referencedColumnName = "code", insertable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private Item item;

    public OrderDetail(OrderDetailPK orderDetailPK, BigDecimal unitPrice, int qty) {
        this.orderDetailPK = orderDetailPK;
        this.unitPrice = unitPrice;
        this.qty = qty;
    }

    public OrderDetail(String orderId, String itemCode, BigDecimal unitPrice, int qty) {
        this.orderDetailPK = new OrderDetailPK(orderId, itemCode);
        this.unitPrice = unitPrice;
        this.qty = qty;
    }
}
