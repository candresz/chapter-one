package com.chapterone.chapteroneapp.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "orderTotal_id")
    private OrderTotal orderTotal;


    private int quantity;

    private double price;



    public OrderDetail() {
    }

    public OrderDetail(int quantity, double subtotal) {
        this.quantity = quantity;
        this.price = subtotal;

    }

    public Long getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public OrderTotal getClientOrder() {
        return orderTotal;
    }

    public void setClientOrder(OrderTotal orderTotal) {
        this.orderTotal = orderTotal;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double subtotal) {
        this.price = subtotal;
    }

    public OrderTotal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(OrderTotal orderTotal) {
        this.orderTotal = orderTotal;
    }

}
