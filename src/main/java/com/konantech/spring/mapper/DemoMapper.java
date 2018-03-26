package com.konantech.spring.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface DemoMapper {

    List<Map<String,Object>> getMetaList(String n);

}
