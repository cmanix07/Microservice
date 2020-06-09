package com.practice.stock.stockservice.model;

import java.math.BigDecimal;

public class Stock {
    private BigDecimal price;
    private String quote;
    public Stock(BigDecimal price){
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
