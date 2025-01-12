package com.petproject.orderservice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

/**
 * Dto со сгенерированным номером заказа
 *
 * @author Egor Nazarev
 */
@Builder
public record GeneratedNumberDto(
    @Schema(description = "Номер заказа", example = "12345620221212")
    @NotNull(message = "orderNumber не может быть null")
    String orderNumber
) { }
