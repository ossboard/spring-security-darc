package com.konantech.spring.service;

import com.konantech.spring.mapper.DemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class DemoService {

    @Autowired
    private DemoMapper demoMapper;

    public List<Map<String,Object>> getMetaList(String n) {
        return demoMapper.getMetaList(n);
    }

}