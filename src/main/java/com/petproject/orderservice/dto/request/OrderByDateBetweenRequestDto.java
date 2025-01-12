package com.petproject.orderservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Builder;

/**
 * Dto для поиска заказа в заданном временном промежутке
 *
 * @author Egor Nazarev
 */
@Builder
public record OrderByDateBetweenRequestDto(
    @Schema(description = "Начало периода", example = "2022-12-12")
    @NotNull(message = "startDate не может быть null")
    LocalDate startDate,

    @Schema(description = "Конец периода", example = "2024-12-12")
    @NotNull(message = "endDate не может быть null")
    LocalDate endDate
) {

}
