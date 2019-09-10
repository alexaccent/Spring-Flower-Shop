package com.accenture.flowershop.backend.entity;

import javax.persistence.*;
import javax.persistence.InheritanceType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "type")
@XmlRootElement
public abstract class User implements Serializable {

    @Id
    @Column(name="LOGIN")
    protected String login;

    @Column(name="PASSWORD")
    protected String password;

    @XmlAttribute
    public String getLogin() {
        return login;
    }

    @XmlAttribute
    public void setLogin(String login) {
        this.login = login;
    }

    @XmlAttribute
    public String getPassword() {
        return password;
    }

    @XmlAttribute
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
