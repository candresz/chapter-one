package com.chapterone.chapteroneapp.controllers;

import com.chapterone.chapteroneapp.dto.ClientBookDTO;
import com.chapterone.chapteroneapp.models.Book;
import com.chapterone.chapteroneapp.models.Client;
import com.chapterone.chapteroneapp.models.ClientBook;
import com.chapterone.chapteroneapp.services.BookService;
import com.chapterone.chapteroneapp.services.ClientBookService;
import com.chapterone.chapteroneapp.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientBook")
public class ClientBookController {

    @Autowired
    ClientService clientService;
    @Autowired
    BookService bookService;
    @Autowired
    ClientBookService clientBookService;

    @PostMapping
    public ResponseEntity<String> clientBook(@RequestParam Long id, Authentication authentication) {

        Client client = clientService.findClientByEmail(authentication.getName());
        Book book = bookService.findBookById(id);

        if (book == null) {
            return new ResponseEntity<>("Book does not exist", HttpStatus.NOT_FOUND);
        }

        ClientBook clientBook = new ClientBook();
        client.addClientBook(clientBook);
        book.addClientBook(clientBook);
        clientBookService.saveClientBook(clientBook);
        bookService.saveBook(book);
        clientService.saveClient(client);
        return new ResponseEntity<>("Book added to list", HttpStatus.OK);

    }
    @PostMapping("/delete")
    public ResponseEntity<String> deleteClientBook(@RequestParam Long id, Authentication authentication){
        Client client = clientService.findClientByEmail(authentication.getName());

        clientBookService.deleteByClientIdAndBookId(client.getId(), id);
        return new ResponseEntity<>("Book deleted from list", HttpStatus.OK);

    }

}
