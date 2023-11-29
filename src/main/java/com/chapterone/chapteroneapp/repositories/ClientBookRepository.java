package com.chapterone.chapteroneapp.repositories;

import com.chapterone.chapteroneapp.models.Client;
import com.chapterone.chapteroneapp.models.ClientBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@RepositoryRestResource
public interface ClientBookRepository extends JpaRepository<ClientBook, Long> {
    ClientBook findByClient(Client client);
    @Modifying
    @Transactional
    @Query("DELETE FROM ClientBook cb WHERE cb.client.id = :clientId AND cb.book.id = :bookId")
    void deleteByClientIdAndBookId(Long clientId, Long bookId);
}
