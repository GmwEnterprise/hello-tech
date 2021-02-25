package com.example.studentsystem.dao;

import com.example.studentsystem.domain.StudentsCourseSelection;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentsCourseSelectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StudentsCourseSelection record);

    int insertSelective(StudentsCourseSelection record);

    StudentsCourseSelection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StudentsCourseSelection record);

    int updateByPrimaryKey(StudentsCourseSelection record);

    List<StudentsCourseSelection> selectAll();

    List<StudentsCourseSelection> selectStudentStatus(Integer studentId);
}