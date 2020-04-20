package edu.Hillel.controllers;

import edu.Hillel.models.Student;
import edu.Hillel.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class StudentController {
    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/showAllStudents")
    public String showAllStudents(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "allStudents";
    }

    @GetMapping("/showStudent/{id}")
    public String showSingleStudent(@PathVariable Integer id, Model model) {
        Optional<Student> findedStudent = studentService.findById(id);
        if (findedStudent.isPresent()) {
            model.addAttribute("student", findedStudent.get());
            model.addAttribute("studentGroups", studentService.getStudentGroups(findedStudent.get().getStudentId()));
            return "singleStudent";
        } else {
            model.addAttribute("message", "ОШИБКА ОТОБРАЖЕНИЯ СТУДЕНТА");
            return "message";
        }
    }

    @GetMapping("/addStudent")
    public String addStudentForm() {
        return "addStudent";
    }

    @PostMapping("/addStudent")
    public String saveStudent(@RequestParam(required = true) String firstname,
                              @RequestParam(required = true) String lastname,
                              Model model) {
        if (studentService.createStudent(firstname, lastname)) {
            model.addAttribute("message", "СТУДЕНТ ДОБАВЛЕН");
        } else {
            model.addAttribute("message", "ОШИБКА СОХРАНЕНИЯ СТУДЕНТА");
        }
        return "message";
    }
}
