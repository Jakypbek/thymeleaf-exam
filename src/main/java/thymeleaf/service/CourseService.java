package thymeleaf.service;

import org.springframework.stereotype.Service;
import thymeleaf.model.Company;
import thymeleaf.model.Course;
import thymeleaf.model.Group;
import thymeleaf.repository.CompanyRepository;
import thymeleaf.repository.CourseRepository;
import thymeleaf.repository.GroupRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;
    private final GroupRepository groupRepository;

    public CourseService(CourseRepository courseRepository, CompanyRepository companyRepository, GroupRepository groupRepository) {
        this.courseRepository = courseRepository;
        this.companyRepository = companyRepository;
        this.groupRepository = groupRepository;
    }


    public List<Course> findByCompanyId(Long companyId) {
        return courseRepository.findByCompanyId(companyId);
    }

    public List<Course> getAllCourses() {
        return courseRepository.getAllCourses();
    }

    public void save(Course course) {
        courseRepository.save(course);
    }

    @Transactional
    public void save(Long companyId, Course course) {
        Company company = companyRepository.findById(companyId);

        course.setCompany(company);
        company.setCourse(course);

        courseRepository.save(course);
    }

    public List<Course> findByGroupId(Long groupId) {
        return courseRepository.findByGroupId(groupId);
    }

    @Transactional
    public void saveGr(Long groupId, Course course) {
        Group group = groupRepository.findById(groupId);

        course.setGroup(group);
        group.setCourse(course);

        courseRepository.save(course);

//        groupRepository.update(group);
//        System.out.println(3);

    }
    @Transactional
    public Course findById(Long courseId) {
        return courseRepository.findById(courseId);
    }

    @Transactional
    public void update(Course course, Long courseId) {
        courseRepository.updated(course, courseId);
    }

    public void delete(Long courseId) {
        courseRepository.delete(courseId);
    }

    public Company findByCourseId(Long courseId) {
        return courseRepository.findByCourseId(courseId);
    }

    public void savea(Course course, Company company) {
        course.setCompany(company);
        company.setCourse(course);
        courseRepository.savea(course, company);
    }
}
