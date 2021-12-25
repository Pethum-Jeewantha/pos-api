package com.pethumjeewanha.pos.service.util;

import com.pethumjeewanha.pos.dto.CustomerDTO;
import com.pethumjeewanha.pos.entity.Customer;

import java.util.List;
import java.util.stream.Collectors;

public class EntityDTOMapper {

    public static CustomerDTO toCustomerDTO(Customer c) {
        return new CustomerDTO(c.getId(), c.getName(), c.getAddress());
    }

    public static Customer fromCustomerDTO(CustomerDTO c) {
        return new Customer(c.getId(), c.getName(), c.getAddress());
    }

    public static List<CustomerDTO> toCustomerDTOList(List<Customer> customers) {
        return customers.stream().map(EntityDTOMapper::toCustomerDTO).collect(Collectors.toList());
    }

    public static List<Customer> fromCustomerDTOList(List<CustomerDTO> customers) {
        return customers.stream().map(EntityDTOMapper::fromCustomerDTO).collect(Collectors.toList());
    }

}
