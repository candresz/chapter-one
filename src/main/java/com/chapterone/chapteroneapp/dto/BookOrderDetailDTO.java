package com.chapterone.chapteroneapp.dto;

import java.util.ArrayList;
import java.util.List;

public class BookOrderDetailDTO {

    private String address;
    private List<OrderDetailDTO> books = new ArrayList<>();
    private String stripeToken;

    public BookOrderDetailDTO() {
    }

    public BookOrderDetailDTO(List<OrderDetailDTO> books, String stripeToken) {
        this.books = books;
        this.stripeToken = stripeToken;
    }

    // Getters y setters para books, address y stripeToken
    public List<OrderDetailDTO> getBooks() {
        return books;
    }

    public void setBooks(List<OrderDetailDTO> books) {
        this.books = books;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStripeToken() {
        return stripeToken;
    }

    public void setStripeToken(String stripeToken) {
        this.stripeToken = stripeToken;
    }
}
