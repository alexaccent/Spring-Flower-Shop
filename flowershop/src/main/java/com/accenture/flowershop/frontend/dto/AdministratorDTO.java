package com.accenture.flowershop.frontend.dto;

import com.accenture.flowershop.backend.entity.User;

public class AdministratorDTO extends UserDTO {

    private String accessLevel;

    public AdministratorDTO(String login, String password, String accessLevel) {
        this.login = login;
        this.password = password;
        this.accessLevel = accessLevel;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }
}
