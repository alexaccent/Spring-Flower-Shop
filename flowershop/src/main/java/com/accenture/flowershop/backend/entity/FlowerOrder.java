package com.accenture.flowershop.backend.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="FLOWERSHOP.FLOWER_ORDER")
public class FlowerOrder implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ORDERS_ID")
    private Orders ordersId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="FLOWER_ID", referencedColumnName = "id")
    private Flower flowerId;

    @Column(name="AMOUNT_FLOWERS")
    private Long amountFlowers;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlowerOrder that = (FlowerOrder) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(ordersId, that.ordersId) &&
                Objects.equals(flowerId, that.flowerId) &&
                Objects.equals(amountFlowers, that.amountFlowers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ordersId, flowerId, amountFlowers);
    }

    @Override
    public String toString() {
        return "FlowerOrder{" +
                "id=" + id +
                ", ordersId='" + ordersId + '\'' +
                ", flowerId=" + flowerId +
                ", numbers=" + amountFlowers +
                '}';
    }
}
