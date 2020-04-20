package edu.Hillel.repositories;

import edu.Hillel.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findById(int id);
    Student save(Student student);
    List<Student> findAll();
//    boolean alreadyPresent(Student student);
}
