package com.chapterone.chapteroneapp.dto;

import com.chapterone.chapteroneapp.models.Book;
import com.chapterone.chapteroneapp.models.OrderDetail;


import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class BookDTO {

    private Long id;

    private String name;
    private String author;
    private String categories;
    private Double price;
    private Integer stock;
    private String imageUrl;
    private Set<OrderDetailDTO> orderDetails;
    private String description;
    private int totalSales;

    public BookDTO() {
    }

    public BookDTO(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.author = book.getAuthor();
        this.categories = book.getCategories();
        this.price = book.getPrice();
        this.stock = book.getStock();
        this.imageUrl = book.getImageUrl();
        this.orderDetails = book.getOrderDetails().stream().map(OrderDetailDTO::new).collect(Collectors.toSet());
        this.description = book.getDescription();
        this.totalSales = book.getTotalSales();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategories() {
        return categories;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Set<OrderDetailDTO> getOrderDetails() {
        return orderDetails;
    }

    public String getDescription() {
        return description;
    }

    public int getTotalSales() {
        return totalSales;
    }
}
