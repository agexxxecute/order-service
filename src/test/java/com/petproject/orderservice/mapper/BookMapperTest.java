package com.petproject.orderservice.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.petproject.orderservice.dto.request.BookCreateDto;
import com.petproject.orderservice.dto.response.BookResponseDto;
import com.petproject.orderservice.model.Book;
import com.petproject.orderservice.util.BookUtil;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class BookMapperTest {
    private BookMapper bookMapper;
    private Book book;
    private BookCreateDto bookCreateDto;

    @BeforeEach
    void setUp() {
        bookMapper = Mappers.getMapper(BookMapper.class);

        book = BookUtil.getValidBook();
        bookCreateDto = BookUtil.getValidBookCreateDto();
    }

    @Test
    @DisplayName("BookCreateDto to Book")
    void bookCreateDtoToBook() {
        Book mappedBook = bookMapper.toEntity(bookCreateDto);

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(mappedBook.getTitle()).isEqualTo(book.getTitle());
            softAssertions.assertThat(mappedBook.getPrice()).isEqualTo(book.getPrice());
        });
    }

    @Test
    @DisplayName("Book to BookResponseDto")
    void bookToBookResponseDto() {
        BookResponseDto mappedBookResponseDto = bookMapper.toDto(book);

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(mappedBookResponseDto.title()).isEqualTo(book.getTitle());
            softAssertions.assertThat(mappedBookResponseDto.price()).isEqualTo(book.getPrice());
        });
    }

}