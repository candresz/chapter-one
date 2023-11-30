package com.chapterone.chapteroneapp.repositories;

import com.chapterone.chapteroneapp.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@RepositoryRestResource
public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsByName(String name);

    boolean existsById(Long id);

    boolean existsByStockGreaterThanEqual(int amount);
    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.stock = b.stock + :newStock WHERE b.id = :bookId")
    int updateStock(Long bookId, int newStock);

}
