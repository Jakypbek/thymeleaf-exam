package thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import thymeleaf.model.Company;
import thymeleaf.model.Course;
import thymeleaf.model.Group;
import thymeleaf.model.Teacher;
import thymeleaf.service.TeacherService;

import java.util.List;

@Controller
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @ModelAttribute("teacherList")
    public List<Teacher> allTeachers() {
        return teacherService.getAllTeachers();
    }

    @RequestMapping
    public String findAll() {
        return "teacherPage";
    }

    @GetMapping("/save")
    public String saveTeacherPage(Model model) {

        model.addAttribute("emptyTeacher", new Teacher());

        return "saveTeacherPage";
    }

    @PostMapping("/save")
    public String saveTeacher(Teacher teacher) {

        teacherService.save(teacher);

        return "redirect:/api/teachers";
    }

    @GetMapping("/find/by/{courseId}")
    public String findAllGroupsByCourseId(@PathVariable Long courseId, Model model) {

        Teacher teacher = teacherService.findByCourseId(courseId);

        model.addAttribute("teacher", teacher);
        model.addAttribute("courseId", courseId);

        return "courseTeacher";
    }

    @GetMapping("/save/{courseId}")
    public String showCourseSavePage(@PathVariable Long courseId, Model model) {

        model.addAttribute("courseId", courseId);
        model.addAttribute("emptyTeacher", new Teacher());

        return "saveCourse'sTeacher";
    }

    @PostMapping("/save/{courseId}")
    public String saveCourse(Teacher teacher,
                             @PathVariable Long courseId) {

        teacherService.save(courseId, teacher);

        return "redirect:/api/teachers/find/by/" + courseId;
    }

    @GetMapping("/edit/{teacherId}")
    public String editTeacher(@PathVariable Long teacherId, Model model) {

        Teacher teacher = teacherService.findById(teacherId);

        model.addAttribute("teacher", teacher);

        return "editTeacher";
    }
    @PostMapping("/edit/{teacherId}")
    public String saveStudfvent(Teacher teacher, @PathVariable Long teacherId) {


        teacherService.update(teacher, teacherId);

        return "redirect:/api/teachers";
    }
    @GetMapping("/delete/{teacherId}")
    public String saveStuddfvent(@PathVariable Long teacherId) {


        teacherService.delete(teacherId);

        return "redirect:/api/teachers";
    }
}
