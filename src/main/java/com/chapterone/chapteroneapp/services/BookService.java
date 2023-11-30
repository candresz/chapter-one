package com.chapterone.chapteroneapp.services;

import com.chapterone.chapteroneapp.dto.BookDTO;
import com.chapterone.chapteroneapp.dto.ClientDTO;
import com.chapterone.chapteroneapp.models.Book;
import com.chapterone.chapteroneapp.models.Client;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    List<BookDTO> getAllBooksDTO();
    Book findBookById(Long id);
    void saveBook(Book book);
    Boolean existsByName(String name);
    boolean existsById(Long id);
    boolean existsByStockGreaterThanEqual(int amount);
    void updateBookStock(Long bookId, int quantity);
    void updateTotalSales(Long bookId, int quantity);
    int updateStock(Long bookId, int newStock);



}
