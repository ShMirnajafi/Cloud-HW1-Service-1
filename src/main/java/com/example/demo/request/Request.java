package com.example.demo.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Getter
public class Request {
    private String email;
    private MultipartFile image;
}
