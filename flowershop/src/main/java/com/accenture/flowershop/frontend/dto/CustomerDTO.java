package com.accenture.flowershop.frontend.dto;

import com.accenture.flowershop.backend.entity.Orders;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class CustomerDTO extends UserDTO {

    private String phone;

    private String address;

    private BigDecimal balance;

    private Integer discount;

    private Set<Orders> orders = new HashSet<Orders>();

    public CustomerDTO (String login, String password, String phone, String address, BigDecimal balance, Integer discount, Set<Orders> orders) {
        this.login = login;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.balance = balance;
        this.discount = discount;
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

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }
}
