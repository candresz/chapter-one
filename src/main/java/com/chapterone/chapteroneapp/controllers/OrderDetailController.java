package com.chapterone.chapteroneapp.controllers;

import com.chapterone.chapteroneapp.dto.BookOrderDetailDTO;
import com.chapterone.chapteroneapp.dto.OrderDetailDTO;
import com.chapterone.chapteroneapp.models.Client;
import com.chapterone.chapteroneapp.models.OrderDetail;
import com.chapterone.chapteroneapp.models.OrderTotal;
import com.chapterone.chapteroneapp.services.BookService;
import com.chapterone.chapteroneapp.services.ClientService;
import com.chapterone.chapteroneapp.services.OrderDetailService;
import com.chapterone.chapteroneapp.services.OrderTotalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.chapterone.chapteroneapp.utils.OrderTotalUtils.localDateTime;
import static com.chapterone.chapteroneapp.utils.OrderTotalUtils.subTotal;

@RestController
@RequestMapping("/api/orderDetail")
public class OrderDetailController {
    @Autowired
    ClientService clientService;
    @Autowired
    BookService bookService;
    @Autowired
    OrderTotalService orderTotalService;
    @Autowired
    OrderDetailService orderDetailService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> newOrderDetail(@RequestBody BookOrderDetailDTO bookOrderDetail, Authentication authentication) {



        Client client = clientService.findClientByEmail(authentication.getName());


        if (bookOrderDetail.getBooks().isEmpty()) {
            return new ResponseEntity<>("Order can not be empty", HttpStatus.FORBIDDEN);
        }

        if (bookOrderDetail.getAddress().isBlank()) {
            return new ResponseEntity<>("The address is missing", HttpStatus.FORBIDDEN);
        }

        OrderTotal orderTotal = new OrderTotal(bookOrderDetail.getAddress(), localDateTime());
        orderTotalService.saveOrderTotal(orderTotal);
        double totalOrderAmount = 0.0;

        if (!bookOrderDetail.getBooks().isEmpty()) {
            for (OrderDetailDTO book : bookOrderDetail.getBooks()) {
                if (!bookService.existsById(book.getId())) {
                    return new ResponseEntity<>("The book does not exists",
                            HttpStatus.FORBIDDEN);
                }
                if (book.getQuantity() <= 0) {
                    return new ResponseEntity<>("The quantity must not be zero", HttpStatus.FORBIDDEN);
                }
                if (!bookService.existsByStockGreaterThanEqual(book.getQuantity())) {
                    return new ResponseEntity<>("Out of stock",
                            HttpStatus.FORBIDDEN);
                }


                OrderDetail orderDetail = new OrderDetail(book.getQuantity(), subTotal(book.getPrice(), book.getQuantity()));
                orderDetail.setBook(bookService.findBookById(book.getId()));
                totalOrderAmount += subTotal(book.getPrice(), book.getQuantity());

                bookService.updateTotalSales(book.getId(), book.getQuantity());
                bookService.updateBookStock(book.getId(), book.getQuantity());
                orderTotal.addOrderDetail(orderDetail);
                orderDetailService.saveOrderDetail(orderDetail);
            }
        }

        orderTotal.setPrice(totalOrderAmount);
        orderTotalService.saveOrderTotal(orderTotal);
        client.addClientOrder(orderTotal);


        return new ResponseEntity<>("Order processed", HttpStatus.CREATED);
    }
}
