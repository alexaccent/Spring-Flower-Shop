package com.accenture.flowershop.frontend.dto;

import com.accenture.flowershop.backend.entity.Flower;
import com.accenture.flowershop.backend.entity.Orders;

import java.math.BigDecimal;

public class FlowerOrderDTO {

    private Long id;

    private Orders ordersId;

    private Flower flowerId;

    private Long amountFlowers;

    private BigDecimal price;

    public FlowerOrderDTO(Long id, Orders ordersId, Flower flowerId, Long amountFlowers, BigDecimal price) {
        this.id = id;
        this.ordersId = ordersId;
        this.flowerId = flowerId;
        this.amountFlowers = amountFlowers;
        this.price = price;
    }
}
