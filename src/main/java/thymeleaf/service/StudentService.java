package thymeleaf.service;

import org.springframework.stereotype.Service;
import thymeleaf.model.Group;
import thymeleaf.model.Student;
import thymeleaf.repository.GroupRepository;
import thymeleaf.repository.StudentRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    public StudentService(StudentRepository studentRepository, GroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }


    public List<Student> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    public void save(Student student) {
        studentRepository.save(student);
    }

    public List<Student> findByGroupId(Long groupId) {
        return studentRepository.findByGroupId(groupId);
    }

    @Transactional
    public void save(Long groupId, Student student) {
        Group group = groupRepository.findById(groupId);

        student.setGroup(group);
        group.setStudent(student);

        studentRepository.save(student);
    }

    public Student findById(Long studentId) {
        return studentRepository.findById(studentId);
    }

    public void update(Student student, Long studentId) {
        studentRepository.updated(student, studentId);
    }

    public void delete(Long studentId) {
        studentRepository.deleteById(studentId);
    }
}
