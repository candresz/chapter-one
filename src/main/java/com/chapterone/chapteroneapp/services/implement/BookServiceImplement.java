package com.chapterone.chapteroneapp.services.implement;

import com.chapterone.chapteroneapp.dto.BookDTO;
import com.chapterone.chapteroneapp.dto.ClientDTO;
import com.chapterone.chapteroneapp.models.Book;
import com.chapterone.chapteroneapp.repositories.BookRepository;
import com.chapterone.chapteroneapp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImplement implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<BookDTO> getAllBooksDTO() {
        return getAllBooks().stream().map(BookDTO::new).collect(Collectors.toList());
    }

    @Override
    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public void saveBook(Book book) {
    bookRepository.save(book);
    }

    @Override
    public Boolean existsByName(String name) {
        return bookRepository.existsByName(name);
    }

    @Override
    public boolean existsById(Long id) {
        return bookRepository.existsById(id);
    }

    @Override
    public boolean existsByStockGreaterThanEqual(int amount) {
        return bookRepository.existsByStockGreaterThanEqual(amount);
    }

    @Override
    public void updateBookStock(Long bookId, int quantity){
        Book book = findBookById(bookId);
        book.setStock(book.getStock() - quantity);
    }

    @Override
    public void updateTotalSales(Long bookId, int quantity) {
       Book book = findBookById(bookId);
       book.setTotalSales(book.getTotalSales() + quantity);
    }

    @Override
    public int updateStock(Long bookId, int newStock) {
        return bookRepository.updateStock(bookId, newStock);
    }


}
