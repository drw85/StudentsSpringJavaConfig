package edu.Hillel.services;

import edu.Hillel.models.StudentGroups;
import edu.Hillel.models.Student;
import edu.Hillel.repositories.GroupRepository;
import edu.Hillel.repositories.StudentRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class StudentServiceImpl implements StudentService {
    StudentIdGenerator studentIdGenerator;
    StudentRepository studentRepository;
    GroupRepository groupRepository;

    public StudentServiceImpl(StudentIdGenerator studentIdGenerator,
                              StudentRepository studentRepository,
                              GroupRepository groupRepository) {
        this.studentIdGenerator = studentIdGenerator;
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public boolean createStudent(String firstname, String lastname) {
        Student student = new Student(studentIdGenerator.generateStudentId(), firstname, lastname);
//        if (!studentRepository.alreadyPresent(student)) {
            return studentRepository.save(student)!=null;
//        } else {
//            return false;
//        }
    }

    @Override
    public Optional<Student> findById(int id) {
        return studentRepository.findById(id);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<StudentGroups> getStudentGroups(int idStudent) {
        if (studentRepository.findById(idStudent).isPresent()) {
            return groupRepository.findAll().stream()
                    .filter(group -> group.getStudents().contains(studentRepository.findById(idStudent).get()))
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }
}
