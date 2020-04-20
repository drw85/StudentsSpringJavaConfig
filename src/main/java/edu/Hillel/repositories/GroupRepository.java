package edu.Hillel.repositories;

import edu.Hillel.models.StudentGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<StudentGroups, Integer> {
    Optional<StudentGroups> findById(int id);

    StudentGroups save(StudentGroups group);

    List<StudentGroups> findAll();
}
