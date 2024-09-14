package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.TestService;

@CrossOrigin
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @PostMapping("/getData")
    public List<Map<String, Object>> name(@RequestBody Map<String, Object> pMap) {
        return testService.getData();
    }
}
