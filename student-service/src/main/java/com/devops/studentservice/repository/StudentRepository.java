package com.devops.studentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devops.studentservice.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
