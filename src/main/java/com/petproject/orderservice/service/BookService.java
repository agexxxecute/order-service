package com.petproject.orderservice.service;

import com.petproject.orderservice.dto.request.BookCreateDto;
import com.petproject.orderservice.dto.response.BookResponseDto;

public interface BookService {
    BookResponseDto addBook (BookCreateDto bookCreateDto);
}
