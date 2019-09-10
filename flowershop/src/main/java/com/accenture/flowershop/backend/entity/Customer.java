package com.accenture.flowershop.backend.entity;

import org.hibernate.annotations.Fetch;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name="FLOWERSHOP.CUSTOMER")
@XmlRootElement
public class Customer extends User implements Serializable {

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

    @XmlAttribute
    public String getPhone() {
        return phone;
    }

    @XmlAttribute
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @XmlAttribute
    public String getAddress() {
        return address;
    }

    @XmlAttribute
    public void setAddress(String address) {
        this.address = address;
    }

    @XmlAttribute
    public BigDecimal getBalance() {
        return balance;
    }

    @XmlAttribute
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @XmlAttribute
    public Integer getDiscount() {
        return discount;
    }

    @XmlAttribute
    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    @XmlElement
    public Set<Orders> getOrders() {
        return orders;
    }

    @XmlElement
    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

}
