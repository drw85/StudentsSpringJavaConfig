package edu.Hillel.services;

import edu.Hillel.models.Courses;
import edu.Hillel.models.Student;
import edu.Hillel.models.StudentGroups;
import edu.Hillel.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GroupServiceImpl implements GroupService {
    @Autowired
            GroupService groupService;

    GroupRepository groupRepository;
    GroupIdGenerator groupIdGenerator;
    StudentService studentService;

    public GroupServiceImpl(GroupRepository groupRepository,
                            GroupIdGenerator groupIdGenerator,
                            StudentService studentService) {
        this.groupRepository = groupRepository;
        this.groupIdGenerator = groupIdGenerator;
        this.studentService = studentService;
    }


    @Override
    public boolean createGroup(String name, Courses course) {
        StudentGroups groupToSave = new StudentGroups(groupIdGenerator.generateGroupdId(), name + " (" + course.getName() + ")", course);
        return groupRepository.save(groupToSave) != null;
    }

    @Override
    public Optional<StudentGroups> findById(int id) {
        return groupRepository.findById(id);
    }

    @Override
    public boolean addStudentToGroup(int idStudent, int idGroup) {
        Optional<Student> findedStudent = studentService.findById(idStudent);
        Optional<StudentGroups> findedGroup = findById(idGroup);
        if (findedStudent.isPresent() && findedGroup.isPresent()) {
            findedGroup.get().addStudent(findedStudent.get());
            return groupRepository.save(findedGroup.get()) != null;
        } else {
            return false;
        }
    }

    @Override
    public List<StudentGroups> findAll() {
        return groupRepository.findAll();
    }
}
