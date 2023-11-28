package com.chapterone.chapteroneapp.dto;

import com.chapterone.chapteroneapp.models.OrderTotal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderTotalDTO {
    private Long id;
    private double totalAmount;
    private String address;
    private List<OrderDetailDTO> bookOrders;
    private String localDateTime;


    public OrderTotalDTO(OrderTotal orderTotal) {
        this.id = orderTotal.getId();
        this.totalAmount = orderTotal.getPrice();
        this.address = orderTotal.getAddress();
        this.bookOrders = orderTotal.getOrderDetails().stream().map(orderDetail -> new OrderDetailDTO(orderDetail)).collect(Collectors.toList());
        this.localDateTime = orderTotal.getLocalDateTime();
    }

    public Long getId() {
        return id;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }


    public String getAddress() {
        return address;
    }

    public List<OrderDetailDTO> getBookOrders() {
        return bookOrders;
    }

    public String getLocalDateTime() {
        return localDateTime;
    }
}
