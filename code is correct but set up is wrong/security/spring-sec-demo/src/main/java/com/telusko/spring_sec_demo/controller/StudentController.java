package com.telusko.spring_sec_demo.controller;

import com.telusko.spring_sec_demo.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    List<Student> students = new ArrayList<>(List.of(
            new Student(1, "Navin", "java"),
            new Student(2, "Kiran", "BlockChain")
    ));

    @GetMapping("students")
    public List<Student> getStudents() {
        return students;
    }

    @PostMapping("students")
    public String addStudents(@RequestBody Student student) {
        students.add(student);
        return "Success";
    }

    @GetMapping("csrf-token")
    public CsrfToken getCsrfTokebn(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }
}
