package com.example.word.common.utils.image;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageHandler imageHandler;

    public String saveImage(MultipartFile image) throws IOException {
        return imageHandler.save(image);
    }

}
