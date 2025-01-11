package com.petproject.orderservice.dto.response;

import lombok.Builder;

@Builder
public record AuthorResponseDto(
    String firstName,
    String lastName
){}
