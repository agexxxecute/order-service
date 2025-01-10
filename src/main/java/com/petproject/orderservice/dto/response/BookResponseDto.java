package com.petproject.orderservice.dto.response;

import java.math.BigDecimal;

public record BookResponseDto(
    String title,
    BigDecimal price,
    AuthorResponseDto author
) { }
