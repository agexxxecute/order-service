package com.petproject.orderservice.dto.response;

import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record BookResponseDto(
    String title,
    BigDecimal price,
    AuthorResponseDto author
) { }
