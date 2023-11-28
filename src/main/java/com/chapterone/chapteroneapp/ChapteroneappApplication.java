package com.chapterone.chapteroneapp;

import com.chapterone.chapteroneapp.models.*;
import com.chapterone.chapteroneapp.repositories.BookRepository;
import com.chapterone.chapteroneapp.repositories.OrderTotalRepository;
import com.chapterone.chapteroneapp.repositories.ClientRepository;
import com.chapterone.chapteroneapp.repositories.OrderDetailRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class ChapteroneappApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChapteroneappApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(
            BookRepository bookRepository,
            OrderTotalRepository orderTotalRepository,
            ClientRepository clientRepository,
            OrderDetailRepository orderDetailRepository) {

        return args -> {

//            LocalDateTime now = LocalDateTime.now();
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'-'HH:mm:ss");
//            String formattedDateTime = now.format(formatter);
//            String formattedDateTimeWithoutT = formattedDateTime.replace('T', '-');
//
//            Client israel = new Client("Israel", "Carrion", "isracarri@gmail.com", "123", false);
//
//
//            Book one = new Book("Isra memories", "Israel Carrion", "+18", 19.90, 10, "https://data.livriz.com/media/MediaSpace/F9AFB48D-741D-4834-B760-F59344EEFF34/45/49ce2ca8-0dd1-4864-b942-b11c82bad38a/9789877950663_68511fbf-99b3-47c5-a883-239254fccb61.jpg", "Isra & Dani en su viaje a la montana");
//
//
//            OrderTotal orderTotal = new OrderTotal("Bs argentina, Isra casa", formattedDateTimeWithoutT);
//
//            israel.addClientOrder(orderTotal);
//            clientRepository.save(israel);
//            orderTotalRepository.save(orderTotal);
//            OrderDetail orderDetail = new OrderDetail(5, 39.90);
//
//            orderTotal.addOrderDetail(orderDetail);
//            one.addOrderDetail(orderDetail);
//            bookRepository.save(one);
//            orderDetailRepository.save(orderDetail);
//
        };
    }
}
