package com.example.studentsystem.domain;

import com.example.studentsystem.common.Enums;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Student implements Serializable {
    private Integer id;

    private String name;

    private LocalDateTime birthday;

    private LocalDate enrollmentDate;

    private Integer academy;

    private Enums.StudentStatus status;

    // 查询参数

    private Academy academyEntity;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Integer getAcademy() {
        return academy;
    }

    public void setAcademy(Integer academy) {
        this.academy = academy;
    }

    public Enums.StudentStatus getStatus() {
        return status;
    }

    public void setStatus(Enums.StudentStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", birthday=").append(birthday);
        sb.append(", enrollmentDate=").append(enrollmentDate);
        sb.append(", academy=").append(academy);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public Academy getAcademyEntity() {
        return academyEntity;
    }

    public void setAcademyEntity(Academy academyEntity) {
        this.academyEntity = academyEntity;
    }
}