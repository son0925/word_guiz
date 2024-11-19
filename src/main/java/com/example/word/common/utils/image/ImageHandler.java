package com.example.word.common.utils.image;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class ImageHandler {

    private static final String IMAGE_DIRECTORY = "C:\\project\\word_guiz\\src\\main\\resources\\public\\images\\";

    public String save(MultipartFile image) throws IOException {
        String fileName = image.getOriginalFilename();
        String fullPathName = IMAGE_DIRECTORY + fileName;

        // 이미지 파일을 지정된 폴더에 저장
        File file = new File(fullPathName);
        file.getParentFile().mkdirs(); // 부모 디렉터리 생성
        image.transferTo(file);

        // 클라이언트가 접근할 수 있도록 URL 형태로 반환
        return "http://my-word-book.kro.kr:8080/public/images/" + fileName;
    }

}
