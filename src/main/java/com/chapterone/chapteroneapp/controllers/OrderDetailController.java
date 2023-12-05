package com.chapterone.chapteroneapp.controllers;

import com.chapterone.chapteroneapp.dto.BookOrderDetailDTO;
import com.chapterone.chapteroneapp.dto.ChargeResponseDTO;
import com.chapterone.chapteroneapp.dto.OrderDetailDTO;
import com.chapterone.chapteroneapp.models.ChargeRequest;
import com.chapterone.chapteroneapp.models.Client;
import com.chapterone.chapteroneapp.models.OrderDetail;
import com.chapterone.chapteroneapp.models.OrderTotal;
import com.chapterone.chapteroneapp.services.BookService;
import com.chapterone.chapteroneapp.services.ClientService;
import com.chapterone.chapteroneapp.services.OrderDetailService;
import com.chapterone.chapteroneapp.services.OrderTotalService;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

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

    @Value("${stripe.api.key}")
    private String apiKey;


    public ChargeResponseDTO chargeCard(@RequestBody ChargeRequest chargeRequest) throws StripeException {
        Stripe.apiKey = apiKey;
        long amountInCents = Math.round(chargeRequest.getAmount() * 100);

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", amountInCents);
        chargeParams.put("currency", "usd");
        chargeParams.put("description", "Example charge");
        chargeParams.put("source", chargeRequest.getToken());

        Charge charge = Charge.create(chargeParams);

        return new ChargeResponseDTO(charge.getId(), amountInCents, charge.getStatus());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> newOrderDetail(@RequestBody BookOrderDetailDTO bookOrderDetail, Authentication authentication) {
        Client client = clientService.findClientByEmail(authentication.getName());

        if (bookOrderDetail.getBooks().isEmpty()) {
            return new ResponseEntity<>("Order cannot be empty", HttpStatus.FORBIDDEN);
        }

        if (bookOrderDetail.getAddress().isBlank()) {
            return new ResponseEntity<>("The address is missing", HttpStatus.FORBIDDEN);
        }

        OrderTotal orderTotal = new OrderTotal(bookOrderDetail.getAddress(), localDateTime());
        orderTotalService.saveOrderTotal(orderTotal);
        double totalOrderAmount = 0.0;

        for (OrderDetailDTO book : bookOrderDetail.getBooks()) {
            if (!bookService.existsById(book.getId())) {
                return new ResponseEntity<>("The book does not exist", HttpStatus.FORBIDDEN);
            }
            if (book.getQuantity() <= 0) {
                return new ResponseEntity<>("The quantity must not be zero", HttpStatus.FORBIDDEN);
            }
            if (!bookService.existsByStockGreaterThanEqual(book.getQuantity())) {
                return new ResponseEntity<>("Out of stock", HttpStatus.FORBIDDEN);
            }

            OrderDetail orderDetail = new OrderDetail(book.getQuantity(), subTotal(book.getPrice(), book.getQuantity()));
            orderDetail.setBook(bookService.findBookById(book.getId()));
            totalOrderAmount += subTotal(book.getPrice(), book.getQuantity());

            bookService.updateTotalSales(book.getId(), book.getQuantity());
            bookService.updateBookStock(book.getId(), book.getQuantity());
            orderTotal.addOrderDetail(orderDetail);
            orderDetailService.saveOrderDetail(orderDetail);
        }

        orderTotal.setPrice(totalOrderAmount);
        orderTotalService.saveOrderTotal(orderTotal);
        client.addClientOrder(orderTotal);

        // Procesamiento del pago
        try {
            ChargeRequest chargeRequest = new ChargeRequest();
            chargeRequest.setAmount(totalOrderAmount);
            chargeRequest.setToken(bookOrderDetail.getStripeToken()); // Aquí utilizamos el token de Stripe

            ChargeResponseDTO chargeResponse = chargeCard(chargeRequest);

            if (chargeResponse.getStatus().equals("succeeded")) {
                return new ResponseEntity<>("Order processed and payment successful", HttpStatus.CREATED);

            } else {
                return new ResponseEntity<>("Payment failed", HttpStatus.BAD_REQUEST);
            }
        } catch (StripeException e) {
            return new ResponseEntity<>("Payment processing error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/orderDetail/{id}")
    public ResponseEntity<Object> getOrderDetailByNumber(@PathVariable Long id, Authentication authentication) {
        Client client = clientService.findClientByEmail(authentication.getName());
        OrderDetail orderDetail = orderDetailService.findById(id);
        if(orderDetail == null){
            return   new ResponseEntity<>("This order does not exists", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(orderDetailService.findById(id), HttpStatus.OK);
    }
//para
}
