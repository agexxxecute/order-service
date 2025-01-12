package com.petproject.orderservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Builder;

/**
 * Dto для создания объекта Order
 *
 * @author Egor Nazarev
 */
@Builder
public record OrderCreateDto(
    @Schema(description = "Имя покупателя", example = "Иванов И.И.")
    @NotNull(message = "customerName не может быть null")
    String customerName,

    @Schema(description = "Список книг в заказе")
    @NotNull(message = "books не может быть null")
    List<BookCreateDto> books
) {

}
