package com.accenture.flowershop.backend.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name="FLOWERSHOP.ADMINISTRATOR")
public class Administrator extends User implements Serializable {

    @Column(name="BALANCE")
    private BigDecimal balance;

    @Column(name="ACCESS_LEVEL")
    private String accessLevel;

    public Administrator() {
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getType() {
        return accessLevel;
    }

    public void setType(String type) {
        this.accessLevel = type;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

}
