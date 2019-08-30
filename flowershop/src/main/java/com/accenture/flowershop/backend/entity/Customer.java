package com.accenture.flowershop.backend.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name="FLOWERSHOP.CUSTOMER")
public class Customer extends User implements Serializable {

    @Column(name="PHONE")
    private String phone;

    @Column(name="ADDRESS")
    private String address;

    @Column(name="BALANCE")
    private BigDecimal balance;

    @Column(name="DISCOUNT")
    private Integer discount;

    @OneToMany(mappedBy="customer")
    private Set<Orders> orders = new HashSet<>();

    public Customer() {
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(phone, customer.phone) &&
                Objects.equals(address, customer.address) &&
                Objects.equals(balance, customer.balance) &&
                Objects.equals(discount, customer.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone, address, balance, discount);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", balance='" + balance + '\'' +
                ", discount=" + discount +
                '}';
    }
}
