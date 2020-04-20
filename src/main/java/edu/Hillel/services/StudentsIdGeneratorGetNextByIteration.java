package edu.Hillel.services;

import edu.Hillel.models.Student;
import edu.Hillel.repositories.StudentRepository;
import org.springframework.stereotype.Component;

import java.util.OptionalInt;

@Component
public class StudentsIdGeneratorGetNextByIteration implements StudentIdGenerator {
    StudentRepository studentRepository;

    public StudentsIdGeneratorGetNextByIteration(
            StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public int generateStudentId() {
        if (studentRepository.findAll().size() == 0) {
            return 1;
        }
        OptionalInt max = studentRepository.findAll()
                .stream()
                .mapToInt(Student::getStudentId)
                .max();
        if (max.isPresent()) {
            return max.getAsInt() + 1;
        } else {
            throw new RuntimeException();
        }
    }
}
