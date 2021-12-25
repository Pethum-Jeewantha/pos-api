package com.pethumjeewanha.pos.service.custom;


import com.pethumjeewanha.pos.dto.CustomerDTO;
import com.pethumjeewanha.pos.service.SuperService;

import java.util.List;

public interface CustomerService extends SuperService {

    void saveCustomer(CustomerDTO customer);

    long getCustomersCount();

    boolean existCustomer(String id);

    void updateCustomer(CustomerDTO customer);

    void deleteCustomer(String id);

    CustomerDTO findCustomer(String id);

    List<CustomerDTO> findAllCustomers();

    List<CustomerDTO> findAllCustomers(int page, int size);

    String generateNewCustomerId();

}
