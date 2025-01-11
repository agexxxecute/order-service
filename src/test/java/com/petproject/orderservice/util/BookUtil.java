package com.petproject.orderservice.util;

import com.petproject.orderservice.dto.request.BookCreateDto;
import com.petproject.orderservice.dto.response.BookResponseDto;
import com.petproject.orderservice.model.Book;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BookUtil {
    public static Book getValidBook(){
        return Book.builder()
            .id(ConstantUtil.VALID_ID)
            .title(ConstantUtil.VALID_BOOK_TITLE)
            .price(ConstantUtil.VALID_BOOK_PRICE)
            .author(AuthorUtil.getValidAuthor())
            .build();
    }

    public static BookCreateDto getValidBookCreateDto(){
        return BookCreateDto.builder()
            .title(ConstantUtil.VALID_BOOK_TITLE)
            .price(ConstantUtil.VALID_BOOK_PRICE)
            .author(AuthorUtil.getValidAuthorCreateDto())
            .build();
    }

    public static BookResponseDto getValidBookResponseDto(){
        return BookResponseDto.builder()
            .title(ConstantUtil.VALID_BOOK_TITLE)
            .price(ConstantUtil.VALID_BOOK_PRICE)
            .author(AuthorUtil.getValidAuthorResponseDto())
            .build();
    }
}
