package com.example.studentsystem.controller;

import com.example.studentsystem.dao.AcademyMapper;
import com.example.studentsystem.domain.Academy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/academy")
public class AcademyController {

    private final AcademyMapper academyMapper;

    public AcademyController(AcademyMapper academyMapper) {this.academyMapper = academyMapper;}

    @GetMapping("/list")
    public List<Academy> list() {
        return academyMapper.selectAll();
    }
}
