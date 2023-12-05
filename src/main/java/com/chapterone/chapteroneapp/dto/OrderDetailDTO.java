package com.chapterone.chapteroneapp.dto;

import com.chapterone.chapteroneapp.models.Book;
import com.chapterone.chapteroneapp.models.OrderDetail;

public class OrderDetailDTO {
    private Long id;
    private int quantity;
    private double price;
    private Book bookId;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(OrderDetail orderDetail) {
        this.id = orderDetail.getId();
        this.quantity = orderDetail.getQuantity();
        this.price = orderDetail.getPrice();
        this.bookId = orderDetail.getBook();
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

    public Book getBookId() {
        return bookId;
    }
}
