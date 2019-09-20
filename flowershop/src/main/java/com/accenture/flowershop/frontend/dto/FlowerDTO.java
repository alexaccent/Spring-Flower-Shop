package com.accenture.flowershop.frontend.dto;

import java.math.BigDecimal;

public class FlowerDTO {

    private Long id;

    private String name;

    private BigDecimal price;

    private Long amount;

    private String imageUrl;

    public FlowerDTO (Long id, String name, BigDecimal price, Long amount, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
