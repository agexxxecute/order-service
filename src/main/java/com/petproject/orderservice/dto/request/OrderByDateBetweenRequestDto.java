package com.petproject.orderservice.dto.request;

import java.time.LocalDate;
import lombok.Builder;

@Builder
public record OrderByDateBetweenRequestDto(
    LocalDate startDate,
    LocalDate endDate
) {

}
