package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.TestMapper;

@Service
public class TestService {

    @Autowired
    private TestMapper testMapper;

    public List<Map<String, Object>> getData() {

        return testMapper.getData();
    }

}
