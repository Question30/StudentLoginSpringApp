package com.perscholas.studentlogin.service;

import com.perscholas.studentlogin.dto.StudentDTO;
import com.perscholas.studentlogin.model.Student;

import java.util.List;

public interface StudentService {
    void saveStudent(StudentDTO studentDTO);
    Student findStudentByEmail(String email);
    List<StudentDTO> findAllStudents();
}
