package com.accenture.flowershop.backend.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="FLOWERSHOP.FLOWER_ORDER")
public class FlowerOrder {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="ORDERS_ID", referencedColumnName = "ID")
    private Orders ordersId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="FLOWER_ID", referencedColumnName = "ID")
    private Flower flowerId;

    @Column(name="AMOUNT_FLOWERS")
    private Long amountFlowers;

    @Column(name="PRICE")
    private BigDecimal price;


    public FlowerOrder() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Orders getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Orders ordersId) {
        this.ordersId = ordersId;
    }

    public Flower getFlowerId() {
        return flowerId;
    }

    public void setFlowerId(Flower flowerId) {
        this.flowerId = flowerId;
    }

    public Long getAmountFlowers() {
        return amountFlowers;
    }

    public void setAmountFlowers(Long amountFlowers) {
        this.amountFlowers = amountFlowers;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
