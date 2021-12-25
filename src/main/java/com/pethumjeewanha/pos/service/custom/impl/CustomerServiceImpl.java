/*
 * Copyright (c) 2021 - present Pethum Jeewantha. All rights reserved.
 *  Licensed under the MIT License. See LICENSE.txt in the project root for license information.
 */

package com.pethumjeewanha.pos.service.custom.impl;

import com.pethumjeewanha.pos.dao.custom.CustomerDAO;
import com.pethumjeewanha.pos.dto.CustomerDTO;
import com.pethumjeewanha.pos.exception.DuplicateEntityException;
import com.pethumjeewanha.pos.exception.EntityNotFoundException;
import com.pethumjeewanha.pos.service.custom.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.pethumjeewanha.pos.service.util.EntityDTOMapper.INSTANCE;

@Transactional
@Component
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public void saveCustomer(CustomerDTO customer) {
        if (existCustomer(customer.getId())) {
            throw new DuplicateEntityException(customer.getId() + " already exists");
        }
        customerDAO.save(INSTANCE.fromCustomerDTO(customer));
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public long getCustomersCount() {
        return customerDAO.count();
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public boolean existCustomer(String id) {
        return customerDAO.existsById(id);
    }

    @Override
    public void updateCustomer(CustomerDTO customer) {
        if (!existCustomer(customer.getId())) {
            throw new EntityNotFoundException("There is no such customer associated with the id " + customer.getId());
        }
        customerDAO.update(INSTANCE.fromCustomerDTO(customer));
    }

    @Override
    public void deleteCustomer(String id) {
        if (!existCustomer(id)) {
            throw new EntityNotFoundException("There is no such customer associated with the id " + id);
        }
        customerDAO.deleteById(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public CustomerDTO findCustomer(String id) {
        return INSTANCE.toCustomerDTO(customerDAO.findById(id).orElseThrow(() -> new EntityNotFoundException("There is no such customer associated with the id " + id)));
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<CustomerDTO> findAllCustomers() {
        return INSTANCE.toCustomerDTOList(customerDAO.findAll());
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<CustomerDTO> findAllCustomers(int page, int size) {
        return INSTANCE.toCustomerDTOList(customerDAO.findAll(page, size));
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public String generateNewCustomerId() {
        String id = customerDAO.getLastCustomerId();
        if (id != null) {
            int newCustomerId = Integer.parseInt(id.replace("C", "")) + 1;
            return String.format("C%03d", newCustomerId);
        } else {
            return "C001";
        }
    }

}
