package com.chapterone.chapteroneapp.repositories;

import com.chapterone.chapteroneapp.models.Client;
import com.chapterone.chapteroneapp.models.ClientBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientBookRepository extends JpaRepository<ClientBook, Long> {
    ClientBook findByClient(Client client);
}
