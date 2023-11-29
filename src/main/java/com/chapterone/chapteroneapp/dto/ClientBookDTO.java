package com.chapterone.chapteroneapp.dto;

import com.chapterone.chapteroneapp.models.Book;
import com.chapterone.chapteroneapp.models.ClientBook;
import org.springframework.beans.factory.annotation.Autowired;

public class ClientBookDTO {

    private Long id;
    private WishBookDTO wishBookDTO;

    public ClientBookDTO(ClientBook clientBook) {
        this.id = clientBook.getId();
        this.wishBookDTO = new WishBookDTO(clientBook.getBook());


    }

    public Long getId() {
        return id;
    }

    public WishBookDTO getWishBookDTO() {
        return wishBookDTO;
    }
}
