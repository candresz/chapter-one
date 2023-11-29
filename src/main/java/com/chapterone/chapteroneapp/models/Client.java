package com.chapterone.chapteroneapp.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Boolean isAdmin;
    @OneToMany(mappedBy = "client")
    private Set<ClientBook> wishList = new HashSet<>();
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<OrderTotal> orderTotals = new HashSet<>();

    public Client() {
    }

    public Client(String firstName, String lastName, String email, String password, Boolean isAdmin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;

    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<OrderTotal> getOrderTotal() {
        return orderTotals;
    }

    public void setOrderTotals(Set<OrderTotal> orderTotals) {
        this.orderTotals = orderTotals;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }


    public Set<OrderTotal> getOrderTotals() {
        return orderTotals;
    }

    public void addClientOrder(OrderTotal orderTotal) {
        orderTotal.setClient(this);
        this.orderTotals.add(orderTotal);
    }

    public Set<ClientBook> getWishList() {
        return wishList;
    }

    public void setWishList(Set<ClientBook> wishList) {
        this.wishList = wishList;
    }
    public void addClientBook(ClientBook clientBook){
        clientBook.setClient(this);
        this.wishList.add(clientBook);
    }
}
