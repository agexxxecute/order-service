package com.petproject.orderservice.mapper;

import com.petproject.orderservice.dto.request.AuthorCreateDto;
import com.petproject.orderservice.dto.response.AuthorResponseDto;
import com.petproject.orderservice.model.Author;
import org.mapstruct.Mapper;

/**
 * Интерфейс для преобразования объектов типа Author в Dto и обратно.
 *
 * @author Egor Nazarev
 */
@Mapper (componentModel = "spring")
public interface AuthorMapper {

    /**
     * Метод для преобразования AuthorCreateDto в Author
     * @param authorCreateDto входная информация об авторе
     * @return объект Author
     */
    Author toEntity(AuthorCreateDto authorCreateDto);

    /**
     * Метод для преобразования Author в AuthorResponseDto
     * @param author объект Author
     * @return Dto с информацией об авторе
     */
    AuthorResponseDto toDto(Author author);
}
