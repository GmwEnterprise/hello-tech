package com.example.studentsystem.domain;

import com.example.studentsystem.common.Enums;

import java.io.Serializable;
import java.time.LocalDateTime;

public class StudentsCourseSelection implements Serializable {
    private Integer id;

    private Integer student;

    private Integer course;

    private LocalDateTime selectedTime;

    private Enums.CourseSelectionStatus status;

    private Boolean pass;

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
}