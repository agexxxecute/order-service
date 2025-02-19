package com.petproject.orderservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Сущность, описывающая автора
 *
 * @author Egor Nazarev
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authors")
@Builder
public class Author {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;
}
