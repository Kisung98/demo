package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.TestMapper;

@Service
public class TestService {

    @Autowired
    private TestMapper testMapper;

    public int getData() {
        Map<String, Object> param = new HashMap<>(); 
        List<Map<String, Integer>> list = new ArrayList<>();
      
        for (int i = 1; i < 100001; i++) {
            Map<String, Integer> map = new HashMap<>();
            map.put("value1",i);
            map.put("value2",i);
            list.add(map);
        }
        param.put("list", list);
        System.err.println(param);
        return testMapper.getData(param);
    }

}
