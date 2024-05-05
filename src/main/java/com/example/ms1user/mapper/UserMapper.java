package com.example.ms1user.mapper;

import com.example.ms1user.dao.entity.UserEntity;
import com.example.ms1user.model.UserDto;
import com.example.ms1user.model.auth.UserRegisterRequestDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto mapEntityToDto(UserEntity userEntity);

    UserEntity mapRegisterRequestDtoToEntity(UserRegisterRequestDto userRegisterRequestDto);

    UserEntity mapDtoToEntity(UserDto userDto);

    UserEntity mapDtoToEntity(UserDto userDto, Integer userId);

    List<UserDto> mapEntityToDtos(List<UserEntity> userEntities);
}
