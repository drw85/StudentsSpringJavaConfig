package edu.Hillel.controllers;

import edu.Hillel.models.Courses;
import edu.Hillel.models.Student;
import edu.Hillel.models.StudentGroups;
import edu.Hillel.services.GroupService;
import edu.Hillel.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class GroupController {
    GroupService groupService;
    StudentService studentService;

    public GroupController(GroupService groupService, StudentService studentService) {
        this.groupService = groupService;
        this.studentService = studentService;
    }

    @GetMapping("/showAllGroups")
    public String showAllGroups(Model model) {
        model.addAttribute("groups", groupService.findAll());
        return "allGroupes";
    }

    @GetMapping("/showGroup/{id}")
    public String showSingleGroup(@PathVariable Integer id, Model model) {
        Optional<StudentGroups> findedGroup = groupService.findById(id);
        if (findedGroup.isPresent()) {
            model.addAttribute("group", findedGroup.get());
            return "singleGroup";
        } else {
            model.addAttribute("message", "ОШИБКА ОТОБРАЖЕНИЯ ГРУППЫ");
            return "message";
        }
    }

    @GetMapping("/addGroup")
    public String addGroupForm(Model model) {
        model.addAttribute("courses", Courses.values());
        return "addGroup";
    }

    @PostMapping("/addGroup")
    public String saveGroup(@RequestParam(required = true) String name,
                            @RequestParam(required = true) Courses course,
                            Model model) {
        if (groupService.createGroup(name, course)) {
            model.addAttribute("message", "ГРУППА ДОБАВЛЕНА");
        } else {
            model.addAttribute("message", "ОШИБКА СОХРАНЕНИЯ ГРУППЫ");
        }
        return "message";
    }

    @GetMapping("/addStudentToGroup")
    public String addStudentToGroupForm(Model model) {
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("groups", groupService.findAll());
        return "addStudentToGroup";
    }

    @PostMapping("/addStudentToGroup")
    public String saveStudentToGroup(@RequestParam(required = true) Integer idStudent,
                                     @RequestParam(required = true) Integer idGroup, Model model) {
        Optional<Student> findedStudent = studentService.findById(idStudent);
        Optional<StudentGroups> findedGroup = groupService.findById(idGroup);
        if (findedStudent.isPresent() && findedGroup.isPresent()) {
            if (!findedGroup.get().getStudents().contains(findedStudent.get())) {
                groupService.addStudentToGroup(findedStudent.get().getStudentId(), findedGroup.get().getGroupId());
            } else {
                model.addAttribute("message", "ЭТОТ СТУДЕНТ УЖЕ В ЭТОЙ ГРУППЕ");
                return "message";
            }
        } else {
            model.addAttribute("message", "ОШИБКА ВНЕСЕНИЯ СТУДЕНТА В ГРУППУ");
            return "message";
        }
        model.addAttribute("message", "СТУДЕНТ " + findedStudent.get().toString() +
                " ДОБАВЛЕН В ГРУППУ " + findedGroup.get().getGroupName());
        return "message";
    }
}
