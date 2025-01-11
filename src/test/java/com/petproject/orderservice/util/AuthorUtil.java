package com.petproject.orderservice.util;

import com.petproject.orderservice.dto.request.AuthorCreateDto;
import com.petproject.orderservice.dto.response.AuthorResponseDto;
import com.petproject.orderservice.model.Author;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthorUtil {
    public static Author getValidAuthor(){
        return Author.builder()
            .id(ConstantUtil.VALID_ID)
            .firstName(ConstantUtil.VALID_FIRST_NAME)
            .lastName(ConstantUtil.VALID_LAST_NAME)
            .build();
    }

    public static AuthorCreateDto getValidAuthorCreateDto(){
        return AuthorCreateDto.builder()
            .firstName(ConstantUtil.VALID_FIRST_NAME)
            .lastName(ConstantUtil.VALID_LAST_NAME)
            .build();
    }

    public static AuthorResponseDto getValidAuthorResponseDto(){
        return AuthorResponseDto.builder()
            .firstName(ConstantUtil.VALID_FIRST_NAME)
            .lastName(ConstantUtil.VALID_LAST_NAME)
            .build();
    }
}
