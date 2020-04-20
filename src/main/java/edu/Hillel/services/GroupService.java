package edu.Hillel.services;

import edu.Hillel.models.Courses;
import edu.Hillel.models.StudentGroups;

import java.util.List;
import java.util.Optional;

public interface GroupService {
    boolean createGroup(String name, Courses course);
    Optional<StudentGroups> findById(int id);
    boolean addStudentToGroup(int idStudent, int idGroup);
    List<StudentGroups> findAll();
}
