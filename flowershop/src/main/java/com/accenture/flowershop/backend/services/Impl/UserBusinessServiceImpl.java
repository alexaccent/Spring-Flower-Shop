package com.accenture.flowershop.backend.services.Impl;

import com.accenture.flowershop.backend.dao.AdministratorDao;
import com.accenture.flowershop.backend.dao.CustomerDao;
import com.accenture.flowershop.backend.dao.UserDao;
import com.accenture.flowershop.backend.entity.Customer;
import com.accenture.flowershop.backend.entity.User;
import com.accenture.flowershop.backend.services.UserBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

@Service
public class UserBusinessServiceImpl implements UserBusinessService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private AdministratorDao administratorDao;

    @Autowired
    private UserDao userDao;

    @Override
    public User login(String login, String password) {

        User userLogin = userDao.getOne(login);

        if (userLogin != null) {
            if (userLogin.getPassword().equals(password)){
                return userLogin;
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
            customer.setDiscount(0);

            customerDao.add(customer);

            return customer;
        } else {
            return null;
        }
    }

    public void logout (HttpSession session) {

        session.removeAttribute("user");
        session.removeAttribute("ordersInSessions");
    }

    public void updateCustomer(Customer customer) {
        customerDao.update(customer);
    }

    public void updateCustomerForJMS(Customer customer) {

        Customer customerFromBd = customerDao.getOne(customer.getLogin());
        customerFromBd.setDiscount(customer.getDiscount());
        customerDao.update(customerFromBd);
    }

    public User updateUserSession(Customer userDataSession) {
        Customer userUpdateSession = customerDao.getOne(userDataSession.getLogin());
        return userUpdateSession;
    }

}
