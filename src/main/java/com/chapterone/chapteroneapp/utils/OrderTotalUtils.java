package com.chapterone.chapteroneapp.utils;

import com.chapterone.chapteroneapp.dto.BookDTO;
import com.chapterone.chapteroneapp.dto.BookOrderDetailDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class OrderTotalUtils {

 public static double subTotal(double price, int stock){
     return price * stock;
 }
 public static String localDateTime(){
     LocalDateTime now = LocalDateTime.now();
     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'-'HH:mm:ss");
     String formattedDateTime = now.format(formatter);
     String formattedDateTimeWithoutT = formattedDateTime.replace('T', ' ');
     return formattedDateTimeWithoutT;
 }

}
