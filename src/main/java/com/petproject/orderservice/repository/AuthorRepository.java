package com.petproject.orderservice.repository;

import com.petproject.orderservice.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с авторами в базе данных.
 *
 * @author Egor Nazarev
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
