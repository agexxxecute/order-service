package com.petproject.orderservice.mapper;

import com.petproject.orderservice.dto.request.AuthorCreateDto;
import com.petproject.orderservice.dto.response.AuthorResponseDto;
import com.petproject.orderservice.model.Author;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface AuthorMapper {
    Author toEntity(AuthorCreateDto authorCreateDto);

    AuthorResponseDto toDto(Author author);
}
