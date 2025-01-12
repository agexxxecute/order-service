package com.petproject.orderservice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

/**
 * Dto с информацией об объекте Order
 *
 * @author Egor Nazarev
 */
@Builder
public record OrderResponseDto(
    @Schema(description = "Номер заказа", example = "12345620221212")
    @NotNull(message = "orderNumber не может быть null")
    String orderNumber,

    @Schema(description = "Стоимость заказа", example = "100.00")
    @NotNull(message = "totalPrice не может быть null")
    BigDecimal totalPrice,

    @Schema(description = "Имя покупателя", example = "Иванов И.И.")
    @NotNull(message = "customerName не может быть null")
    String customerName,

    @Schema(description = "Дата создания заказа", example = "2022-12-12")
    @NotNull(message = "orderDate не может быть null")
    LocalDateTime orderDate,

    @Schema(description = "Список книг в заказе")
    @NotNull(message = "books не может быть null")
    List<BookResponseDto> books
) { }
