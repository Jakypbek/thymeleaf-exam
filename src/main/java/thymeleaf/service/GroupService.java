package thymeleaf.service;

import org.springframework.stereotype.Service;
import thymeleaf.model.Course;
import thymeleaf.model.Group;
import thymeleaf.repository.CourseRepository;
import thymeleaf.repository.GroupRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;

    public GroupService(GroupRepository groupRepository, CourseRepository courseRepository) {
        this.groupRepository = groupRepository;
        this.courseRepository = courseRepository;
    }

    public List<Group> getAllGroups() {
        return groupRepository.getAllGroups();
    }

    public void save(Group group) {
        groupRepository.save(group);
    }

    public List<Group> findByCourseId(Long courseId) {
        return groupRepository.findByCourseId(courseId);
    }

    @Transactional
    public void save(Long courseId, Group group) {
        Course course = courseRepository.findById(courseId);

        course.setGroup(group);
        group.setCourse(course);

        groupRepository.save(group);
    }

    public Group findById(Long groupId) {
        return groupRepository.findById(groupId);
    }

    public void update(Group group, Long groupId) {
        groupRepository.updated(group, groupId);
    }

    public void delete(Long groupId) {
        groupRepository.delete(groupId);
    }
}
