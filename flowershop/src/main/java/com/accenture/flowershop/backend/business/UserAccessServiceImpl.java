package com.accenture.flowershop.backend.business;

import com.accenture.flowershop.backend.business.interfaces.UserAccessService;
import com.accenture.flowershop.backend.entity.User;

import java.util.HashMap;
import java.util.Map;

public class UserAccessServiceImpl implements UserAccessService {

    private static Map<String, User> usersStorage = new HashMap<>();

    @Override
    public User login(String login, String password) {
        User userReturn = null;

        if (usersStorage.containsKey(login) ) {

            for (Map.Entry<String, User> user : usersStorage.entrySet() ) {
                User userCheckData = user.getValue();

                if (userCheckData.getLogin().equals(login) && userCheckData.getPassword().equals(password)) {
                    userReturn =  userCheckData;
                }
            }
        }

        return userReturn;
    }

    @Override
    public User register(String login, String phone, String address, String password) {

        if (!usersStorage.containsKey(login)) {
            User user = new User(login, phone, address, password);
            usersStorage.put(login, user);

            return user;
        } else {
            return null;
        }
    }

    public static Map<String, User> getUsersStorage() {
        return usersStorage;
    }
}
