package edu.Hillel.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class StudentGroups {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int groupId;
    private String groupName;
    private Courses course;
    @ManyToMany
    private List<Student> students;

    public StudentGroups(int groupId, String groupName, Courses course) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.course = course;
        students = new ArrayList<>();
    }

    public StudentGroups() {
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int id) {
        this.groupId = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Courses getCourse() {
        return course;
    }

    public void setCourse(Courses course) {
        this.course = course;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public boolean addStudent(Student studentToAdd){
        return this.students.add(studentToAdd);
    }
}
