package com.chapterone.chapteroneapp.dto;

import com.chapterone.chapteroneapp.models.OrderDetail;

public class OrderDetailDTO {
    private Long id;
    private int quantity;
    private double price;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(OrderDetail orderDetail) {
        this.id = orderDetail.getId();
        this.quantity = orderDetail.getQuantity();
        this.price = orderDetail.getPrice();
    }

    public Long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

}
