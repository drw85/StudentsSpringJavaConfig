package edu.Hillel.services;

import edu.Hillel.models.StudentGroups;
import edu.Hillel.repositories.GroupRepository;
import org.springframework.stereotype.Component;

import java.util.OptionalInt;

@Component
public class GroupIdGeneratorGetNextByIteration implements GroupIdGenerator {
    GroupRepository groupRepository;

    public GroupIdGeneratorGetNextByIteration(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public int generateGroupdId() {
        if (groupRepository.findAll().size() == 0) {
            return 1;
        }
        OptionalInt max = groupRepository.findAll().stream()
                .mapToInt(StudentGroups::getGroupId)
                .max();
        if (max.isPresent()) {
            return max.getAsInt() + 1;
        } else {
            throw new RuntimeException();
        }
    }
}
