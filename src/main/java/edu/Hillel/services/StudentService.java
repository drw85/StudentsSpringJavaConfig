package edu.Hillel.services;

import edu.Hillel.models.StudentGroups;
import edu.Hillel.models.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
boolean createStudent(String firstname, String lastname);
Optional<Student> findById(int id);
List<Student> findAll();
List<StudentGroups> getStudentGroups(int idStudent);
}
