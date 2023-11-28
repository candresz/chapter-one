package com.chapterone.chapteroneapp.services;

import com.chapterone.chapteroneapp.dto.OrderTotalDTO;
import com.chapterone.chapteroneapp.models.Client;
import com.chapterone.chapteroneapp.models.OrderTotal;

import java.util.List;
import java.util.Set;

public interface OrderTotalService {
    List<OrderTotal> getAllOrderTotal();
    List<OrderTotalDTO> getAllOrderTotalDTO();

    Set<OrderTotal> findOrderTotalByClient(Client client);
    Set<OrderTotalDTO> findOrderTotalByClientDTO(Client client);
    OrderTotal getOrderTotalById(Long id);
    void saveOrderTotal(OrderTotal orderTotal);

    OrderTotal findByIdAndClient(Long id, Client client);
    Boolean existsByIdAndClient(Long id, Client client);

}
