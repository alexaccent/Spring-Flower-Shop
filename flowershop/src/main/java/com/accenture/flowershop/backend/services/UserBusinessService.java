package com.accenture.flowershop.backend.services;

import com.accenture.flowershop.backend.entity.User;
import com.accenture.flowershop.exception.UserLoginException;

public interface UserBusinessService {
    /**
     * Authorization user
     * @param login
     * @param password
     * @return User
     */
    User login(String login, String password) throws UserLoginException;

    /**
     * Registration user. Save data user to Bd
     * @param login
     * @param password
     * @param phone
     * @param address
     * @return User
     */
    User register(String login, String password, String phone,String address) throws UserLoginException;
}
