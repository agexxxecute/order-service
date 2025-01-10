package com.petproject.orderservice.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDto(
    String orderNumber,
    BigDecimal totalPrice,
    String customerName,
    LocalDateTime orderDate,
    List<BookResponseDto> books
) { }
