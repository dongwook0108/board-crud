package com.example.boardcrud.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Rest Api용 컨트롤러 Json 반환
public class FirstApiController {

    @GetMapping("/api/hello")
    public String hello() {
        return "hello";
    }
}
