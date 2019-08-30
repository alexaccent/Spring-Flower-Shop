package com.accenture.flowershop.backend.entity;

import com.accenture.flowershop.frontend.enums.OrderStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="FLOWERSHOP.ORDERS")
public class Orders implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="OWNER_ORDERS")
    private Customer customer;

    @Column(name="STATUS")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name="PRICE")
    private BigDecimal price;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="ORDERS_DATE")
    private Date ordersDate;



    @OneToMany(mappedBy="ordersId", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<FlowerOrder> flowerOrders = new HashSet<>();

    public Orders() {
    }

    public Orders(Customer customer, OrderStatus status) {
        this.customer = customer;
        this.status = status;
        price = new BigDecimal(100.0); // not done
        ordersDate = new Date();
    }

    public Set<FlowerOrder> getFlowerOrders() {
        return flowerOrders;
    }

    public void setFlowerOrders(Set<FlowerOrder> flowerOrders) {
        this.flowerOrders = flowerOrders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return Objects.equals(id, orders.id) &&
                Objects.equals(status, orders.status) &&
                Objects.equals(customer, orders.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, customer);
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", customer=" + customer +
                '}';
    }
}
