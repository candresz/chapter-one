package com.chapterone.chapteroneapp.dto;

import java.util.ArrayList;
import java.util.List;

public class BookOrderDetailDTO {

    private String address;
    private List<OrderDetailDTO> books = new ArrayList<>();


    public BookOrderDetailDTO() {
    }

    public BookOrderDetailDTO(List<OrderDetailDTO> books, String address) {
        this.books = books;
    }

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
}
