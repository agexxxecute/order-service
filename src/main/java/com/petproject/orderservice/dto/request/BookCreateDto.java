package com.petproject.orderservice.dto.request;

import java.math.BigDecimal;

public record BookCreateDto(
    String title,
    BigDecimal price,
    AuthorCreateDto author
) {

}
