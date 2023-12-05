package com.chapterone.chapteroneapp.services;

import com.chapterone.chapteroneapp.models.Client;
import com.chapterone.chapteroneapp.models.OrderDetail;

public interface OrderDetailService {
    void saveOrderDetail(OrderDetail orderDetail);
    OrderDetail findById(Long id);
}
