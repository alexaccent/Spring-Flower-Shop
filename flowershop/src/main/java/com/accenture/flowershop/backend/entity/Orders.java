package com.accenture.flowershop.backend.entity;

import com.accenture.flowershop.enums.OrderStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="FLOWERSHOP.ORDERS")
public class Orders {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="OWNER_ORDERS")
    private Customer customerId;

    @Column(name="STATUS")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name="PRICE")
    private BigDecimal price;

    @Column(name="DISCOUNT_PRICE")
    private BigDecimal discountPrice;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="ORDERS_DATE")
    private Date ordersDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="ORDERS_CLOSE_DATE")
    private Date ordersCloseDate;

    @OneToMany(mappedBy="ordersId", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<FlowerOrder> flowerOrders = new HashSet<>();

    public Orders() {
    }

    public Orders(Customer customer, OrderStatus status) {
        this.customerId = customer;
        this.status = status;
        this.setOrdersDate(new Date());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customerId;
    }

    public void setCustomer(Customer customer) {
        this.customerId = customer;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Date getOrdersDate() {
        return ordersDate;
    }

    public void setOrdersDate(Date ordersDate) {
        this.ordersDate = ordersDate;
    }

    public Set<FlowerOrder> getFlowerOrders() {
        return flowerOrders;
    }

    public void setFlowerOrders(Set<FlowerOrder> flowerOrders) {
        this.flowerOrders = flowerOrders;
    }

    public Date getOrdersCloseDate() {
        return ordersCloseDate;
    }

    public void setOrdersCloseDate(Date ordersCloseDate) {
        this.ordersCloseDate = ordersCloseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return Objects.equals(id, orders.id) &&
                status == orders.status &&
                Objects.equals(price, orders.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, price);
    }
}
