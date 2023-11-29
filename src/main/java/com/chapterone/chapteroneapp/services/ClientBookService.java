package com.chapterone.chapteroneapp.services;

import com.chapterone.chapteroneapp.models.Book;
import com.chapterone.chapteroneapp.models.Client;
import com.chapterone.chapteroneapp.models.ClientBook;

public interface ClientBookService {
    void saveClientBook(ClientBook clientBook);
    ClientBook findByClient(Client client);
    void deleteByClientIdAndBookId(Long clientId, Long bookId);

}
