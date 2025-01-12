package com.petproject.orderservice.mapper;

import com.petproject.orderservice.dto.request.BookCreateDto;
import com.petproject.orderservice.dto.response.BookResponseDto;
import com.petproject.orderservice.model.Book;
import java.util.List;
import org.mapstruct.Mapper;

/**
 * Интерфейс для преобразования объектов типа Book в Dto и обратно.
 *
 * @author Egor Nazarev
 */
@Mapper(componentModel = "spring")
public interface BookMapper {

    /**
     * Метод для преобразования BookCreateDto в Book
     * @param bookCreateDto входная информация для создания книги
     * @return объект Book
     */
    Book toEntity (BookCreateDto bookCreateDto);

    /**
     * Метод для преобразования Book в BookResponseDto
     * @param book объект Book
     * @return dto с информацией о книге
     */
    BookResponseDto toDto (Book book);

    /**
     * Метод для преобразования списка BookCreateDto в список Book
     * @param bookCreateDtos список объектов BookCreateDto
     * @return список объектов Book
     */
    List<Book> toEntity (List<BookCreateDto> bookCreateDtos);
}
