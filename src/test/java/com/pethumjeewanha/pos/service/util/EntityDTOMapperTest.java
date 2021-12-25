/*
 * Copyright (c) 2021 - present Pethum Jeewantha. All rights reserved.
 *  Licensed under the MIT License. See LICENSE.txt in the project root for license information.
 */

package com.pethumjeewanha.pos.service.util;

import com.pethumjeewanha.pos.dto.CustomerDTO;
import com.pethumjeewanha.pos.dto.ItemDTO;
import com.pethumjeewanha.pos.entity.Customer;
import com.pethumjeewanha.pos.entity.Item;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class EntityDTOMapperTest {

    @Test
    public void shouldMapCustomerToDTO() {
        Customer customer = new Customer("C001", "Pethum", "Galle");
        Item item = new Item("I001", "Pen", new BigDecimal("500"), 10);

        CustomerDTO customerDTO = EntityDTOMapper.INSTANCE.toCustomerDTO(customer);
        ItemDTO itemDTO = EntityDTOMapper.INSTANCE.toItemDTO(item);

        System.out.println(customerDTO);
        System.out.println(itemDTO);
    }
}
