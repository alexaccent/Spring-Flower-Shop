package com.accenture.flowershop.backend.entity;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name="FLOWERSHOP.CUSTOMER")
public class Customer extends User {

    @Column(name="PHONE")
    private String phone;

    @Column(name="ADDRESS")
    private String address;

    @Column(name="BALANCE")
    private BigDecimal balance;

    @Column(name="DISCOUNT")
    private Integer discount;


    @Fetch(value = org.hibernate.annotations.FetchMode.SELECT)
    @OneToMany(mappedBy = "customerId", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Orders> orders = new HashSet<Orders>();

    public Customer() {
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
