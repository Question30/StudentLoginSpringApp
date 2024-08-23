package com.perscholas.studentlogin.repository;

import com.perscholas.studentlogin.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByEmail(String email);
}
