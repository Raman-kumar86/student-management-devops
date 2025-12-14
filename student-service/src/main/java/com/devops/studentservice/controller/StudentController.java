package com.devops.studentservice.controller;

import com.devops.studentservice.entity.Student;
import com.devops.studentservice.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return service.save(student);
    }

    @GetMapping
    public List<Student> getStudents() {
        return service.getAll();
    }
}
