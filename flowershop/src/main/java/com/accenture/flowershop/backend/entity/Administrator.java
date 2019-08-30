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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Administrator that = (Administrator) o;
        return Objects.equals(balance, that.balance) &&
                Objects.equals(accessLevel, that.accessLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance, accessLevel);
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "balance=" + balance +
                ", accesslevel='" + accessLevel + '\'' +
                '}';
    }
}
