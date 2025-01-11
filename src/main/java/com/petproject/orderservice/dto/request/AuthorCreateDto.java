package com.petproject.orderservice.dto.request;

import lombok.Builder;

@Builder
public record AuthorCreateDto(
    String firstName,
    String lastName
) {

}
