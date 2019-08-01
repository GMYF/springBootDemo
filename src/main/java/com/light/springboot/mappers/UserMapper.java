package com.light.springboot.mappers;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {
    List<Map> findById(Integer id);
}
