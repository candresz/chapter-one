package com.chapterone.chapteroneapp.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;


    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    private Set<OrderDetail> orderDetails = new HashSet<>();

    @OneToMany(mappedBy = "book")
    private Set<ClientBook> clientBooks = new HashSet<>();
    private String name;
    private String author;
    private String categories;
    private double price;
    private int stock;
    private String imageUrl;
    private String description;
    private int totalSales;

    public Book() {
    }

    public Book(String name, String author, String categories, double price, int stock, String imageUrl, String description) {
        this.name = name;
        this.author = author;
        this.categories = categories;
        this.price = price;
        this.stock = stock;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public Long getId() {
        return id;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addOrderDetail(OrderDetail orderDetail) {
        orderDetail.setBook(this);
        this.orderDetails.add(orderDetail);

    }

    public int getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
    }

    public Set<ClientBook> getClientBooks() {
        return clientBooks;
    }

    public void setClientBooks(Set<ClientBook> clientBooks) {
        this.clientBooks = clientBooks;
    }
    public void addClientBook(ClientBook clientBook){
        clientBook.setBook(this);
        this.clientBooks.add(clientBook);
    }
}
