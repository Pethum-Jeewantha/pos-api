package com.pethumjeewanha.pos.service.util;

import com.pethumjeewanha.pos.dto.CustomerDTO;
import com.pethumjeewanha.pos.dto.ItemDTO;
import com.pethumjeewanha.pos.entity.Customer;
import com.pethumjeewanha.pos.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

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
}
