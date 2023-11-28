package com.chapterone.chapteroneapp.repositories;

import com.chapterone.chapteroneapp.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

}
