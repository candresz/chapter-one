package com.chapterone.chapteroneapp.controllers;


import com.chapterone.chapteroneapp.dto.OrderTotalDTO;
import com.chapterone.chapteroneapp.models.Client;
import com.chapterone.chapteroneapp.services.ClientService;
import com.chapterone.chapteroneapp.services.OrderTotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api")
public class OrderTotalController {

    @Autowired
    private OrderTotalService orderTotalService;
    @Autowired
    private ClientService clientService;

    @GetMapping("/orderTotal/{id}")
    public ResponseEntity<Object> getOrderTotalByNumber(@PathVariable Long id, Authentication authentication) {
        Client client = clientService.findClientByEmail(authentication.getName());
        if(!orderTotalService.existsByIdAndClient(id, client)){
         return   new ResponseEntity<>("This order was not made by this client", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(new OrderTotalDTO(orderTotalService.findByIdAndClient(id, client)), HttpStatus.OK);
    }


}
