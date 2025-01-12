package com.petproject.orderservice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

/**
 * Dto с информацией об объекте Author
 *
 * @author Egor Nazarev
 */
@Builder
public record AuthorResponseDto(
    @Schema(description = "Имя (отчество) автора", example = "Александр")
    @NotNull(message = "firstName не может быть null")
    String firstName,

    @Schema(description = "Фамилия автора", example = "Пушкин")
    @NotNull(message = "lastName не может быть null")
    String lastName
){}
