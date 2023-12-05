package com.chapterone.chapteroneapp.dto;

import com.chapterone.chapteroneapp.models.Book;
import com.chapterone.chapteroneapp.models.OrderDetail;

public class OrderDetailDTO {
    private Long id;
    private int quantity;
    private double price;
    private String nameBook;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(OrderDetail orderDetail) {
        this.id = orderDetail.getId();
        this.quantity = orderDetail.getQuantity();
        this.price = orderDetail.getPrice();
        this.nameBook = orderDetail.getBook().getName();
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

    public String getNameBook() {
        return nameBook;
    }
}
