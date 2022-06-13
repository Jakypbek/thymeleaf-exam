package thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import thymeleaf.model.Company;
import thymeleaf.model.Course;
import thymeleaf.model.Student;
import thymeleaf.service.StudentService;

import java.util.List;

@Controller
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @ModelAttribute("studentList")
    public List<Student> allStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping
    public String findAll() {
        return "studentPage";
    }

    @GetMapping("/save")
    public String saveStudentPage(Model model) {

        model.addAttribute("emptyStudent", new Student());

        return "saveStudentPage";
    }

    @PostMapping("/save")
    public String saveStudent(Student student) {

        studentService.save(student);

        return "redirect:/api/students";
    }

    @GetMapping("/find/by/{groupId}")
    public String findAllStudentsByCourseId(@PathVariable Long groupId, Model model) {

        List<Student> students = studentService.findByGroupId(groupId);

        model.addAttribute("students", students);
        model.addAttribute("groupId", groupId);

        return "groupStudents";
    }

    @GetMapping("/save/{groupId}")
    public String showCourseSavePage(@PathVariable Long groupId, Model model) {

        model.addAttribute("groupId", groupId);
        model.addAttribute("emptyStudent", new Student());

        return "saveGroup'sStudent";
    }

    @PostMapping("/save/{groupId}")
    public String saveCourse(Student student,
                             @PathVariable Long groupId) {

        studentService.save(groupId, student);

        return "redirect:/api/students/find/by/" + groupId;
    }

    @GetMapping("/edit/{studentId}")
    public String editstudent(@PathVariable Long studentId, Model model) {

        Student student = studentService.findById(studentId);

        model.addAttribute("student", student);

        return "editStudent";
    }
    @PostMapping("/edit/{studentId}")
    public String saveStudfvent(Student student, @PathVariable Long studentId) {


        studentService.update(student, studentId);

        return "redirect:/api/students";
    }
    @GetMapping("/delete/{studentId}")
    public String saveStuddfvent(@PathVariable Long studentId) {


        studentService.delete(studentId);

        return "redirect:/api/students";
    }
}
