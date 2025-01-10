package com.petproject.orderservice.dto.request;

import java.util.List;

public record OrderCreateDto(
    String customerName,
    List<BookCreateDto> books
) {

}
