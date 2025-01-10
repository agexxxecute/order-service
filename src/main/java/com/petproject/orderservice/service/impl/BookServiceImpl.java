package com.petproject.orderservice.service.impl;

import com.petproject.orderservice.dto.request.BookCreateDto;
import com.petproject.orderservice.dto.response.BookResponseDto;
import com.petproject.orderservice.mapper.BookMapper;
import com.petproject.orderservice.model.Book;
import com.petproject.orderservice.repository.BookRepository;
import com.petproject.orderservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookResponseDto addBook(BookCreateDto bookCreateDto) {
        Book book = bookMapper.toEntity(bookCreateDto);
        return bookMapper.toDto(bookRepository.save(book));
    }
}
