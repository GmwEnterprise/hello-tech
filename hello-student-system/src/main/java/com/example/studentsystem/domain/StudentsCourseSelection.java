package com.example.studentsystem.domain;

import com.example.studentsystem.common.Enums;

import java.io.Serializable;
import java.time.LocalDateTime;

public class StudentsCourseSelection implements Serializable {
    private Integer id;

    private Integer student; // 学生主键

    private Integer course; // 课程主键

    private LocalDateTime selectedTime;

    private Enums.CourseSelectionStatus status; // 选课情况

    private Boolean pass; // 是否合格

    // 查询信息

    private Student studentEntity;
    private Course courseEntity;

    private Enums.StudentStatus studentStatus;
    private Integer scoreTotal;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudent() {
        return student;
    }

    public void setStudent(Integer student) {
        this.student = student;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public LocalDateTime getSelectedTime() {
        return selectedTime;
    }

    public void setSelectedTime(LocalDateTime selectedTime) {
        this.selectedTime = selectedTime;
    }

    public Enums.CourseSelectionStatus getStatus() {
        return status;
    }

    public void setStatus(Enums.CourseSelectionStatus status) {
        this.status = status;
    }

    public Boolean getPass() {
        return pass;
    }

    public void setPass(Boolean pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", student=").append(student);
        sb.append(", course=").append(course);
        sb.append(", selectedTime=").append(selectedTime);
        sb.append(", status=").append(status);
        sb.append(", pass=").append(pass);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public Student getStudentEntity() {
        return studentEntity;
    }

    public void setStudentEntity(Student studentEntity) {
        this.studentEntity = studentEntity;
    }

    public Course getCourseEntity() {
        return courseEntity;
    }

    public void setCourseEntity(Course courseEntity) {
        this.courseEntity = courseEntity;
    }

    public Enums.StudentStatus getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(Enums.StudentStatus studentStatus) {
        this.studentStatus = studentStatus;
    }

    public Integer getScoreTotal() {
        return scoreTotal;
    }

    public void setScoreTotal(Integer scoreTotal) {
        this.scoreTotal = scoreTotal;
    }
}