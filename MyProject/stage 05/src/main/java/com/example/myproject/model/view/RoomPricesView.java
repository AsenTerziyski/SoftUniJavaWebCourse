package com.example.myproject.model.view;

import com.example.myproject.model.entities.enums.RoomEnum;

import java.math.BigDecimal;

public class RoomPricesView {
    private RoomEnum type;
    private BigDecimal price;

    public RoomPricesView() {
    }

    public RoomEnum getType() {
        return type;
    }

    public RoomPricesView setType(RoomEnum type) {
        this.type = type;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public RoomPricesView setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
