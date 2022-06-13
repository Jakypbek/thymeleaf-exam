package thymeleaf.service;

import org.springframework.stereotype.Service;
import thymeleaf.model.Course;
import thymeleaf.model.Teacher;
import thymeleaf.repository.CourseRepository;
import thymeleaf.repository.TeacherRepository;

import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    public TeacherService(TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.getAllTeachers();
    }

    public void save(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public Teacher findByCourseId(Long courseId) {
        return teacherRepository.findByCourseId(courseId);
    }

    public void save(Long courseId, Teacher teacher) {
        Course course = courseRepository.findById(courseId);

        teacher.setCourse(course);
        course.setTeacher(teacher);

        teacherRepository.save(teacher);
    }

    public Teacher findById(Long teacherId) {
        return teacherRepository.findById(teacherId);
    }

    public void update(Teacher teacher, Long teacherId) {
        teacherRepository.updated(teacher, teacherId);
    }

    public void delete(Long teacherId) {
        teacherRepository.delete(teacherId);
    }
}
