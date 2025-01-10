package com.petproject.orderservice.mapper;

import com.petproject.orderservice.dto.request.BookCreateDto;
import com.petproject.orderservice.dto.response.BookResponseDto;
import com.petproject.orderservice.model.Book;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book toEntity (BookCreateDto bookCreateDto);

    BookResponseDto toDto (Book book);

    List<Book> toDto (List<BookCreateDto> bookCreateDtos);
}
