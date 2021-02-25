package com.example.studentsystem.controller;

import com.example.studentsystem.common.Enums;
import com.example.studentsystem.dao.StudentMapper;
import com.example.studentsystem.dao.StudentsCourseSelectionMapper;
import com.example.studentsystem.domain.Student;
import com.example.studentsystem.domain.StudentsCourseSelection;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentMapper studentMapper;
    private final StudentsCourseSelectionMapper selectionMapper;

    public StudentController(StudentMapper studentMapper, StudentsCourseSelectionMapper selectionMapper) {
        this.studentMapper = studentMapper;
        this.selectionMapper = selectionMapper;
    }

    @GetMapping("/list")
    public PageInfo<Student> studentList(@RequestParam(required = false) String name,
                                         @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                         @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        Student condition = new Student();
        condition.setName(name);
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(studentMapper.selectByCondition(condition));
    }

    @GetMapping("/graduationList")
    public PageInfo<Student> graduationList(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(studentMapper.selectGraduationList());
    }

    @PostMapping("/graduate/{studentId}")
    public Map<String, Object> graduate(@PathVariable("studentId") Integer studentId) {
        List<StudentsCourseSelection> selections = selectionMapper.selectStudentStatus(studentId);
        boolean success = false;
        String message;
        if (selections.size() == 0) {
            message = "无法毕业";
        } else {
            if (selections.stream().anyMatch(item -> item.getStatus() == Enums.CourseSelectionStatus.CourseLearning)) {
                // 还有正在开的课
                message = "无法毕业，选课未完结";
            } else {
                int scoreTotal = selections.stream()
                                           .filter(StudentsCourseSelection::getPass)
                                           .mapToInt(StudentsCourseSelection::getScoreTotal)
                                           .sum();
                if (scoreTotal >= 15) {
                    success = true;
                    message = "恭喜毕业!";
                } else {
                    message = "学分不够，无法毕业";
                }
            }
        }
        HashMap<String, Object> result = new HashMap<>(2);
        result.put("success", success);
        result.put("message", message);
        return result;
    }

    @PostMapping("/save")
    public void addStudent(@RequestBody Student student) {
        if (student.getId() == null)
            studentMapper.insertSelective(student);
        else
            studentMapper.updateByPrimaryKeySelective(student);
    }

    @PostMapping("/delete/{id}")
    public void deleteLine(@PathVariable("id") Integer id) {
        studentMapper.deleteByPrimaryKey(id);
    }
}
