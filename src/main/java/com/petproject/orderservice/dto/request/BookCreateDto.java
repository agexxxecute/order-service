package com.petproject.orderservice.dto.request;

import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record BookCreateDto(
    String title,
    BigDecimal price,
    AuthorCreateDto author
) {

}
