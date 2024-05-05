package com.example.ms1user.service;

import com.example.ms1user.dao.repository.PictureRepository;
import com.example.ms1user.mapper.PictureMapper;
import com.example.ms1user.model.PictureDto;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
@Slf4j
public class PictureService {
    private final PictureRepository pictureRepository;
    private final PictureMapper pictureMapper;

    public PictureService(PictureRepository pictureRepository, PictureMapper pictureMapper) {
        this.pictureRepository = pictureRepository;
        this.pictureMapper = pictureMapper;
    }

    public List<String> getAllImages() {
        log.info("ActionLog.getAllImages.start");
        List<PictureDto> pictureDtos = pictureMapper.mapEntityToDtos(pictureRepository.findAll());
        List<String> base64Images = new ArrayList<>();

        for (PictureDto picture : pictureDtos) {
            byte[] imageBytes = picture.getPicture().getBytes();
            base64Images.add(Base64.encodeBase64String(imageBytes));
        }

        log.info("ActionLog.getAllImages.end");
        return base64Images;
    }
    public String getImage(Integer imageId) {
        log.info("ActionLog.getImage.start");
        PictureDto pictureDto = pictureMapper.mapEntityToDto(pictureRepository.findById(imageId).orElseThrow(() ->
                new RuntimeException("Not Found")
        ));
        log.info("ActionLog.getPicture.end");
        byte[] imageBytes = pictureDto.getPicture().getBytes();
        return Base64.encodeBase64String(imageBytes);
    }
    @Transactional
    public void saveImage(String base64Image) {
        log.info("ActionLog.saveImage.start");
        String picture = Arrays.toString(Base64.decodeBase64(base64Image));
        PictureDto pictureDto = new PictureDto();
        pictureDto.setPicture(picture);
        pictureRepository.save(pictureMapper.mapDtoToEntity(pictureDto));
        log.info("ActionLog.saveImage.end");
    }



    public void updateImage(String base64Image, Integer imageId) {
        log.info("ActionLog.updateImage.start");
        String imageBytes = Arrays.toString(Base64.decodeBase64(base64Image));
        PictureDto pictureDto = pictureMapper.mapEntityToDto(pictureRepository.findById(imageId).orElseThrow(() ->
                new RuntimeException("Not Found")
        ));
        pictureDto.setPicture(imageBytes);
        pictureRepository.save(pictureMapper.mapDtoToEntity(pictureDto));
        log.info("ActionLog.updateImage.end");
    }
    public void deleteImage(Integer imageId) {
        log.info("ActionLog.deleteImage.start");
        pictureRepository.deleteById(imageId);
        log.info("ActionLog.deleteImage.end");
    }
}
