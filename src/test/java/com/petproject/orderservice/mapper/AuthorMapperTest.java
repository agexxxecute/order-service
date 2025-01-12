package com.petproject.orderservice.mapper;

import com.petproject.orderservice.dto.request.AuthorCreateDto;
import com.petproject.orderservice.dto.response.AuthorResponseDto;
import com.petproject.orderservice.model.Author;
import com.petproject.orderservice.util.AuthorUtil;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class AuthorMapperTest {
    private AuthorMapper authorMapper;
    private Author author;
    private AuthorCreateDto authorCreateDto;
    private AuthorResponseDto authorResponseDto;

    @BeforeEach
    void setUp() {
        authorMapper = Mappers.getMapper(AuthorMapper.class);

        author = AuthorUtil.getValidAuthor();
        authorCreateDto = AuthorUtil.getValidAuthorCreateDto();
    }

    @Test
    @DisplayName("AuthorCreateDto to Author")
    void authorCreateDtoToAuthor() {
        Author mappedAuthor = authorMapper.toEntity(authorCreateDto);

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(mappedAuthor.getFirstName()).isEqualTo(author.getFirstName());
            softAssertions.assertThat(mappedAuthor.getLastName()).isEqualTo(author.getLastName());
        });
    }

    @Test
    @DisplayName("Author to AuthorResponseDto")
    void authorToAuthorResponseDto() {
        AuthorResponseDto mappedAuthorResponseDto = authorMapper.toDto(author);

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(mappedAuthorResponseDto.firstName()).isEqualTo(author.getFirstName());
            softAssertions.assertThat(mappedAuthorResponseDto.lastName()).isEqualTo(author.getLastName());
        });
    }
}