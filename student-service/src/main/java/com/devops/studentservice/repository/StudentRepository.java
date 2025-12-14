package com.devops.studentservice.repository;

import com.devops.studentservice.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
