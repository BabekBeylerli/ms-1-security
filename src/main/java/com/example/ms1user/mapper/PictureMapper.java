package com.example.ms1user.mapper;

import com.example.ms1user.dao.entity.PictureEntity;
import com.example.ms1user.model.PictureDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PictureMapper {
    PictureDto mapEntityToDto(PictureEntity pictureEntity);

    PictureEntity mapDtoToEntity(PictureDto pictureDto);

    PictureEntity mapDtoToEntity(PictureDto pictureDto, Integer pictureId);

    List<PictureDto> mapEntityToDtos(List<PictureEntity> pictureEntities);
}
