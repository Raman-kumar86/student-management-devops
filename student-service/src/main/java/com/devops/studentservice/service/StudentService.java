package com.devops.studentservice.service;

import com.devops.studentservice.entity.Student;
import com.devops.studentservice.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student save(Student student) {
        return repository.save(student);
    }

    public List<Student> getAll() {
        return repository.findAll();
    }
}
