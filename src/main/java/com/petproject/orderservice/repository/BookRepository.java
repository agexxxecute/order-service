package com.petproject.orderservice.repository;

import com.petproject.orderservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с книгами в базе данных.
 *
 * @author Egor Nazarev
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
