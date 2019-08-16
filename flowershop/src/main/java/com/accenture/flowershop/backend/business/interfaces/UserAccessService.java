package com.accenture.flowershop.backend.business.interfaces;

import com.accenture.flowershop.backend.entity.User;

public interface UserAccessService {
    User login(String user, String password);
    User register(String login, String phone,String address, String password);
}
