package com.example.ms1user.mapper;

import com.example.ms1user.dao.entity.RoleEntity;
import com.example.ms1user.model.auth.RoleDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleEntity mapDtoToEntity(RoleDto roleDto);

    RoleDto mapEntityToDto(RoleEntity roleEntity);
}