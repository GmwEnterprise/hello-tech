package com.example.studentsystem.controller;

import com.example.studentsystem.dao.StudentMapper;
import com.example.studentsystem.domain.Student;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentMapper studentMapper;

    public StudentController(StudentMapper studentMapper) {this.studentMapper = studentMapper;}

    @GetMapping("/list")
    public PageInfo<Student> studentList(@RequestParam(required = false) String name,
                                         @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                         @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        Student condition = new Student();
        condition.setName(name);
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(studentMapper.selectByCondition(condition));
    }
}
