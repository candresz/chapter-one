package com.chapterone.chapteroneapp.services.implement;

import com.chapterone.chapteroneapp.models.Client;
import com.chapterone.chapteroneapp.models.OrderDetail;
import com.chapterone.chapteroneapp.repositories.OrderDetailRepository;
import com.chapterone.chapteroneapp.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImplement implements OrderDetailService {
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Override
    public void saveOrderDetail(OrderDetail orderDetail) {
        orderDetailRepository.save(orderDetail);
    }

    @Override
    public OrderDetail findById(Long id) {
        return orderDetailRepository.findById(id).orElse(null);
    }
}
