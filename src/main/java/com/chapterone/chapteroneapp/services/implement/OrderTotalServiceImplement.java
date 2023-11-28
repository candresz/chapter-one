package com.chapterone.chapteroneapp.services.implement;

import com.chapterone.chapteroneapp.dto.OrderTotalDTO;
import com.chapterone.chapteroneapp.models.Client;
import com.chapterone.chapteroneapp.models.OrderTotal;
import com.chapterone.chapteroneapp.repositories.OrderTotalRepository;
import com.chapterone.chapteroneapp.services.OrderTotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderTotalServiceImplement implements OrderTotalService {
    @Autowired
    OrderTotalRepository orderTotalRepository;
    @Override
    public List<OrderTotal> getAllOrderTotal() {
        return orderTotalRepository.findAll();
    }

    @Override
    public List<OrderTotalDTO> getAllOrderTotalDTO() {
        return getAllOrderTotal().stream().map(OrderTotalDTO::new).collect(Collectors.toList());

    }

    @Override
    public Set<OrderTotal> findOrderTotalByClient(Client client) {
        return orderTotalRepository.findByClient(client);
    }

    @Override
    public Set<OrderTotalDTO> findOrderTotalByClientDTO(Client client) {
        return findOrderTotalByClient(client).stream().map(OrderTotalDTO::new).collect(Collectors.toSet());
    }

    @Override
    public OrderTotal getOrderTotalById(Long id) {
        return orderTotalRepository.findById(id).orElse(null);
    }

    @Override
    public void saveOrderTotal(OrderTotal orderTotal) {
        orderTotalRepository.save(orderTotal);
    }


    @Override
    public OrderTotal findByIdAndClient(Long id, Client client) {
        return orderTotalRepository.findByIdAndClient(id, client);
    }

    @Override
    public Boolean existsByIdAndClient(Long id, Client client) {
        return orderTotalRepository.existsByIdAndClient(id, client);
    }

}
