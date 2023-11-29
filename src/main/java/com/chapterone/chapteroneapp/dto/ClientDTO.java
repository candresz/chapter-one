package com.chapterone.chapteroneapp.dto;

import com.chapterone.chapteroneapp.models.Client;
import com.chapterone.chapteroneapp.models.ClientBook;
import com.chapterone.chapteroneapp.services.ClientBookService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    private Set<OrderTotalDTO> orderTotal;
    private Set<ClientBookDTO> clientBook;


    public ClientDTO() {
    }

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
        this.orderTotal = client.getOrderTotal().stream().map(OrderTotalDTO::new).collect(Collectors.toSet());
        this.clientBook = client.getWishList().stream().map(ClientBookDTO::new).collect(Collectors.toSet());

    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Set<OrderTotalDTO> getOrderTotal() {
        return orderTotal;
    }

    public Set<ClientBookDTO> getClientBook() {
        return clientBook;
    }
}
