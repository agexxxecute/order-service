package com.petproject.orderservice.repository;

import static org.junit.Assert.assertEquals;

import com.petproject.orderservice.model.Author;
import com.petproject.orderservice.model.Book;
import com.petproject.orderservice.model.Order;
import com.petproject.orderservice.util.TestContainersUtil;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class OrderRepositoryTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:17.2");

    @Autowired
    OrderRepository orderRepository;

    private final LocalDate startDate = LocalDate.of(2021, 2, 1);
    private final LocalDate endDate = LocalDate.of(2023, 1, 1);


    private final int ONE_ELEMENT = 1;


    @Test
    @DisplayName("Find by date between successfully")
    void findByDateBetweenSuccessfully() {
        List<Order> orderList = orderRepository.getOrderByDateBetween(startDate, endDate);
        Assertions.assertEquals(ONE_ELEMENT, orderList.size());
        Assertions.assertEquals(TestContainersUtil.orders.get(1),orderList.get(0));
    }

}