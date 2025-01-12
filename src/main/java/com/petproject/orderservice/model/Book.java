package com.petproject.orderservice.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Сущность, описывающая книгу
 *
 * @author Egor Nazarev
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
@Builder
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private BigDecimal price;

    @ManyToOne (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Author author;
}
