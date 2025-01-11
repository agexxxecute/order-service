package com.petproject.orderservice.util;

import com.petproject.orderservice.model.Author;
import com.petproject.orderservice.model.Book;
import com.petproject.orderservice.model.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class TestContainersUtil {
    public static List<Author> authors = List.of(
        new Author(1L, "Александр Сергеевич", "Пушкин"),
        new Author(2L, "Михаил Юрьевич", "Лермонтов")
    );

    public static List<Book> books = List.of(
        new Book(1L, "Евгений Онегин", new BigDecimal("75.00"), authors.get(0)),
        new Book(2L, "Герой нашего времени", new BigDecimal("25.00"), authors.get(1)),
        new Book(3L, "Руслан и Людмила", new BigDecimal("50.00"), authors.get(0))
    );

    public static List<Order> orders = List.of(
        new Order(1L, "11111120210101", new BigDecimal("100.00"), "Покупатель-1", LocalDate.of(2021,1,1), List.of(books.get(0), books.get(1))),
        new Order(2L, "22222220220101", new BigDecimal("50.00"), "Покупатель-2", LocalDate.of(2022,1,1), List.of(books.get(2)))
    );
}
