package com.example.studentsystem.dao;

import com.example.studentsystem.domain.Academy;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcademyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Academy record);

    int insertSelective(Academy record);

    Academy selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Academy record);

    int updateByPrimaryKey(Academy record);

    List<Academy> selectAll();
}