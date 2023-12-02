package com.chapterone.chapteroneapp.controllers;

import com.chapterone.chapteroneapp.dto.BookDTO;
import com.chapterone.chapteroneapp.dto.ClientDTO;
import com.chapterone.chapteroneapp.models.Book;
import com.chapterone.chapteroneapp.models.Client;
import com.chapterone.chapteroneapp.services.BookService;
import com.chapterone.chapteroneapp.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooksDTO();
    }


    @PostMapping
    public ResponseEntity<String> newBook(
            @RequestParam String name, @RequestParam String author, @RequestParam String categories, @RequestParam Double price, @RequestParam Integer stock, @RequestParam String imageUrl, @RequestParam String description) {

        if (name.isBlank()) {
            return new ResponseEntity<>("Type name", HttpStatus.FORBIDDEN);
        }
        if (author.isBlank()) {
            return new ResponseEntity<>("Type author", HttpStatus.FORBIDDEN);
        }
        if (categories.isBlank()) {
            return new ResponseEntity<>("Type categories", HttpStatus.FORBIDDEN);
        }
        if (price <= 0) {
            return new ResponseEntity<>("Price must not be zero", HttpStatus.FORBIDDEN);
        }
        if (stock <= 0) {
            return new ResponseEntity<>("Stock must not be zero", HttpStatus.FORBIDDEN);
        }
        if (imageUrl.isBlank()) {
            return new ResponseEntity<>("Type categories", HttpStatus.FORBIDDEN);
        }
        if (description.isBlank()) {
            return new ResponseEntity<>("Description must not be empty", HttpStatus.FORBIDDEN);
        }
        if (bookService.existsByName(name)) {
            return new ResponseEntity<>("Name already exists", HttpStatus.FORBIDDEN);
        }


        Book book = new Book(name, author, categories, price, stock, imageUrl, description);
        bookService.saveBook(book);

        return new ResponseEntity<>("Book created successfully", HttpStatus.CREATED);
    }
    @PostMapping("/addStock")
    public ResponseEntity<String> updateStock(@RequestParam Long id, int newStock){
        if(!bookService.existsById(id)){
            return  new ResponseEntity<>("Book does not exists", HttpStatus.FORBIDDEN);
        }
        if(newStock <= 0){
            return  new ResponseEntity<>("Quantity must be greater than zero", HttpStatus.FORBIDDEN);
        }
        bookService.updateStock(id, newStock);
        return  new ResponseEntity<>("Stock updated", HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrderTotalByNumber(@PathVariable Long id) {

        return new ResponseEntity<>(bookService.findBookById(id), HttpStatus.OK);
    }

}
