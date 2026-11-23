package com.example.demo.user.mapper;

import com.example.demo.user.dto.UserDto;
import com.example.demo.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(UserDto dto);
}
