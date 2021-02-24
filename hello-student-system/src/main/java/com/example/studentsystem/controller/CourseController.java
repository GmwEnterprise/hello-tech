package com.example.studentsystem.controller;

import com.example.studentsystem.dao.CourseMapper;
import com.example.studentsystem.domain.Course;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {

    public final CourseMapper courseMapper;

    public CourseController(CourseMapper courseMapper) {this.courseMapper = courseMapper;}

    @GetMapping("/list")
    public PageInfo<Course> studentList(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                        @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(courseMapper.selectAll());
    }

    @PostMapping("/save")
    public void addStudent(@RequestBody Course course) {
        if (course.getId() == null)
            courseMapper.insertSelective(course);
        else
            courseMapper.updateByPrimaryKeySelective(course);
    }

    @PostMapping("/delete/{id}")
    public void deleteLine(@PathVariable("id") Integer id) {
        courseMapper.deleteByPrimaryKey(id);
    }
}
