// src/main/java/com/apcsa/practice/controller/TestController.java
package com.apcsa.practice.controller;

import org.springframework.web.bind.annotation.CrossOrigin; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") // 可以在瀏覽器打http://localhost:8080/api/hello以測試
public class TestController {
    
    @GetMapping("/hello")
    public String hello() {
        return "Hello from Backend! 後端回應成功！";
    }
}
