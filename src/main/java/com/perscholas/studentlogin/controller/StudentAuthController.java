package com.perscholas.studentlogin.controller;

import com.perscholas.studentlogin.dto.StudentDTO;
import com.perscholas.studentlogin.model.Student;
import com.perscholas.studentlogin.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentAuthController {
    private StudentService studentService;

    @Autowired
    public StudentAuthController(StudentService studentService){
        this.studentService =studentService;
    }

    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        StudentDTO studentDTO = new StudentDTO();
        model.addAttribute("student", studentDTO);

        return "register";
    }


    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("student") StudentDTO studentDTO, BindingResult result, Model model){
        Student existingStudent =
                studentService.findStudentByEmail(studentDTO.getEmail());

        if(existingStudent != null && existingStudent.getEmail() != null && !existingStudent.getEmail().isEmpty()){
            result.rejectValue("email", null, "There is already an account " +
                    "registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("student", studentDTO);
            return "/register";
        }

        studentService.saveStudent(studentDTO);
        return "redirect:/register?success";
    }

    @GetMapping("/students")
    public String students(Model model){
        List<StudentDTO> studentDTOS = studentService.findAllStudents();

        model.addAttribute("students", studentDTOS);

        return "students";
    }
}
