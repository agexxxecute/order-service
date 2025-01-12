package com.petproject.orderservice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Builder;

/**
 * Dto с информацией об объекте Book
 *
 * @author Egor Nazarev
 */
@Builder
public record BookResponseDto(
    @Schema(description = "Название книги", example = "Евгений Онегин")
    @NotNull(message = "title не может быть null")
    String title,

    @Schema(description = "Стоимость книги", example = "100.00")
    @NotNull(message = "price не может быть null")
    BigDecimal price,

    @Schema(description = "Автор книги")
    @NotNull(message = "author не может быть null")
    AuthorResponseDto author
) { }
