package com.chapterone.chapteroneapp.repositories;
import com.chapterone.chapteroneapp.models.Client;
import com.chapterone.chapteroneapp.models.OrderTotal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Set;

@RepositoryRestResource
public interface OrderTotalRepository extends JpaRepository<OrderTotal, Long> {
    OrderTotal findByIdAndClient(Long id, Client client);

    Boolean existsByIdAndClient(Long id, Client client);
    Set<OrderTotal> findByClient(Client client);
}
