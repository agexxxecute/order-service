package com.petproject.orderservice.dto.request;

import java.util.List;
import lombok.Builder;

@Builder
public record OrderCreateDto(
    String customerName,
    List<BookCreateDto> books
) {

}
