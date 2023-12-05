package com.chapterone.chapteroneapp.repositories;

import com.chapterone.chapteroneapp.models.Client;
import com.chapterone.chapteroneapp.models.OrderDetail;
import com.chapterone.chapteroneapp.models.OrderTotal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {



}
