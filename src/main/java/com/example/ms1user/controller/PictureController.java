package com.example.ms1user.controller;

import com.example.ms1user.model.PictureDto;
import com.example.ms1user.service.PictureService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/pictures")
public class PictureController {

    private final PictureService pictureService;

    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("/all")
    public List<String> getAllImages() {
        return pictureService.getAllImages();
    }

    @GetMapping("/{imageId}")
    public String getImage(@PathVariable Integer imageId) {
        return pictureService.getImage(imageId);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveImage(@RequestBody String base64Image) {
        pictureService.saveImage(base64Image);
    }

    @PutMapping("/{imageId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateImage(@RequestBody String base64Image, @PathVariable Integer imageId) {
        pictureService.updateImage(base64Image, imageId);
    }

    @DeleteMapping("/{imageId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteImage(@PathVariable Integer imageId) {
        pictureService.deleteImage(imageId);
    }
}
