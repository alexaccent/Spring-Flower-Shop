package com.accenture.flowershop.backend.entity;

import javax.persistence.*;
import javax.persistence.InheritanceType;
import java.io.Serializable;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "type")
public abstract class User {

    @Id
    @Column(name="LOGIN")
    protected String login;

    @Column(name="PASSWORD")
    protected String password;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
