package com.accenture.flowershop.frontend.dto;

import com.accenture.flowershop.backend.entity.Customer;
import com.accenture.flowershop.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.Date;

public class OrdersDTO {

    private Long id;

    private Customer customerId;

    private OrderStatus status;

    private BigDecimal price;

    private BigDecimal discountPrice;

    private Date ordersDate;

    private Date ordersCloseDate;

    public OrdersDTO (Long id, Customer customerId, OrderStatus status, BigDecimal price, BigDecimal discountPrice, Date ordersDate, Date ordersCloseDate) {
        this.id = id;
        this.customerId = customerId;
        this.status = status;
        this.price = price;
        this.discountPrice = discountPrice;
        this.ordersDate = ordersDate;
        this.ordersCloseDate = ordersCloseDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
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

    public Date getOrdersCloseDate() {
        return ordersCloseDate;
    }

    public void setOrdersCloseDate(Date ordersCloseDate) {
        this.ordersCloseDate = ordersCloseDate;
    }
}
