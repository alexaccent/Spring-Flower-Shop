package com.accenture.flowershop.backend.business.interfaces;

import com.accenture.flowershop.backend.entity.User;

public interface UserBusinessServices {
    String login(String user, String password);
    User register(String user, String password, String address);
}
