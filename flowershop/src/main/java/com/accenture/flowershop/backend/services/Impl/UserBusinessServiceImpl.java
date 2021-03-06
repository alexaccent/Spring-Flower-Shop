package com.accenture.flowershop.backend.services.Impl;

import com.accenture.flowershop.backend.dao.AdministratorDao;
import com.accenture.flowershop.backend.dao.CustomerDao;
import com.accenture.flowershop.backend.dao.UserDao;
import com.accenture.flowershop.backend.entity.Customer;
import com.accenture.flowershop.backend.entity.User;
import com.accenture.flowershop.backend.services.UserBusinessService;
import com.accenture.flowershop.exception.UserLoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

@Service
public class UserBusinessServiceImpl implements UserBusinessService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private AdministratorDao administratorDao;

    @Autowired
    private UserDao userDao;

    @Override
    public User login(String login, String password) throws UserLoginException {

        User userLogin = userDao.getOne(login);

        if (userLogin != null && userLogin.getPassword().equals(password)) {

            return userLogin;
        } else {
            throw new UserLoginException("Вы ввели не верный логин или пароль");
        }
    }

    @Override
    public User register(String login, String password, String phone, String address) throws UserLoginException  {

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
            throw new UserLoginException("Данный логин уже занят");
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

    public List<Customer> getListForTable() {

        return customerDao.getAll();
    }

}
