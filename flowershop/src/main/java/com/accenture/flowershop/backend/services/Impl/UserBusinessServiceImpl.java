package com.accenture.flowershop.backend.services.Impl;

import com.accenture.flowershop.backend.dao.CustomerDao;
import com.accenture.flowershop.backend.entity.Customer;
import com.accenture.flowershop.backend.entity.User;
import com.accenture.flowershop.backend.services.UserBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserBusinessServiceImpl implements UserBusinessService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public User login(String login, String password) {

        Customer customerLogin = customerDao.getOne(login);

        if(customerLogin != null) {
            if(customerLogin.getPassword().equals(password)){
                return customerLogin;
            }
        }

        return null;
    }

    @Override
    public User register(String login, String password, String phone, String address) {

        if (customerDao.getOne(login) == null) {

            Customer customer  = new Customer();
            customer.setLogin(login);
            customer.setPassword(password);
            customer.setPhone(phone);
            customer.setAddress(address);
            customer.setBalance(new BigDecimal(2000.0));

            customerDao.add(customer);

            return customer;
        } else {
            return null;
        }
    }
}
