package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.TestService;

@CrossOrigin
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @PostMapping("/testData")
    public int test() {
        return 1; 
    }
    
    @PostMapping("/insertData")
    public int insert() {
        return testService.getData();
    }
}
