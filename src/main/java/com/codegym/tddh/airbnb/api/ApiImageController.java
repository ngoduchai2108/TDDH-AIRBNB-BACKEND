package com.codegym.tddh.airbnb.api;

import com.codegym.tddh.airbnb.model.Image;
import com.codegym.tddh.airbnb.payload.response.UploadFileResponse;
import com.codegym.tddh.airbnb.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class ApiImageController {

    private static final Logger logger = LoggerFactory.getLogger(ApiImageController.class);

    @Autowired
    private ImageService imageService;
    @PostMapping("/uploadFile/{houseid}")
    public UploadFileResponse uploadFileResponse(@RequestParam("file") MultipartFile file, @PathVariable("houseid") Long id) {
        Image image = imageService.storeFile(file, id);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(String.valueOf(image.getId()))
                .toUriString();

        return new UploadFileResponse(image.getName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @GetMapping("/downloadFile/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) {
        Image image = imageService.getFile(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; name=\"" + image.getName() + "\"")
                .body(new ByteArrayResource(image.getData()));
    }
}
