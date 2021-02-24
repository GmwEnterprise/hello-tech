package com.example.studentsystem.controller;

import com.example.studentsystem.dao.CourseMapper;
import com.example.studentsystem.dao.StudentMapper;
import com.example.studentsystem.dao.StudentsCourseSelectionMapper;
import com.example.studentsystem.domain.Course;
import com.example.studentsystem.domain.Student;
import com.example.studentsystem.domain.StudentsCourseSelection;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/selection")
public class CourseSelectionController {

    public final StudentsCourseSelectionMapper selectionMapper;
    public final StudentMapper studentMapper;
    public final CourseMapper courseMapper;

    public CourseSelectionController(StudentsCourseSelectionMapper selectionMapper, StudentMapper studentMapper, CourseMapper courseMapper) {
        this.selectionMapper = selectionMapper;
        this.studentMapper = studentMapper;
        this.courseMapper = courseMapper;
    }

    @GetMapping("/list")
    public PageInfo<StudentsCourseSelection> studentList(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                         @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(selectionMapper.selectAll());
    }

    @PostMapping("/save")
    public void addStudent(@RequestBody StudentsCourseSelection selection) {
        if (selection.getId() == null)
            selectionMapper.insertSelective(selection);
        else
            selectionMapper.updateByPrimaryKeySelective(selection);
    }

    @PostMapping("/delete/{id}")
    public void deleteLine(@PathVariable("id") Integer id) {
        selectionMapper.deleteByPrimaryKey(id);
    }

    @GetMapping("/itemList")
    public Map<String, Object> itemList() {
        List<Student> students = studentMapper.selectByCondition(null);
        List<Course> courses = courseMapper.selectAll();
        Map<Integer, List<Course>> map = new HashMap<>();
        courses.forEach(course -> {
            if (map.containsKey(course.getAcademy())) {
                map.get(course.getAcademy()).add(course);
            } else {
                map.put(course.getAcademy(), new ArrayList<>(Collections.singletonList(course)));
            }
        });
        HashMap<String, Object> result = new HashMap<>();
        result.put("students", students);
        result.put("courses", map);
        return result;
    }
}
