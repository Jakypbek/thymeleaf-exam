package thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import thymeleaf.model.Company;
import thymeleaf.model.Course;
import thymeleaf.service.CompanyService;
import thymeleaf.service.CourseService;

import java.util.List;

@Controller
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;
    private final CompanyService companyService;

    public CourseController(CourseService courseService, CompanyService companyService) {
        this.courseService = courseService;
        this.companyService = companyService;
    }

    @ModelAttribute("courseList")
    public List<Course> allCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping
    public String findAll() {
        return "coursePage";
    }

    @GetMapping("/save")
    public String saveCoursePage(Model model) {


        model.addAttribute("emptyCourse", new Course());
        model.addAttribute("emptyCompany", new Company());

        return "saveCoursePage";
    }

    @PostMapping("/save")
    public String saveStudent(Course course, Company company) {

        courseService.savea(course, company);


        return "redirect:/api/courses";
    }

    @GetMapping("/find/by/{companyId}")
    public String findAllStudentsByCourseId(@PathVariable Long companyId, Model model) {

        List<Course> courses = courseService.findByCompanyId(companyId);

        model.addAttribute("courses", courses);
        model.addAttribute("companyId", companyId);

        return "companyCourses";
    }

    @GetMapping("/save/{companyId}")
    public String showCourseSavePage(@PathVariable Long companyId, Model model) {

        model.addAttribute("companyId", companyId);
        model.addAttribute("emptyCourse", new Course());

        return "saveCompany'sCourse";
    }

    @PostMapping("/save/{companyId}")
    public String saveCourse(Course course,
                              @PathVariable Long companyId) {

        courseService.save(companyId, course);

        return "redirect:/api/courses/find/by/" + companyId;
    }
    @GetMapping("/find/by/gr/{groupId}")
    public String findAllgroupsByCourseId(@PathVariable Long groupId, Model model) {

        List<Course> courses = courseService.findByGroupId(groupId);

        model.addAttribute("courses", courses);
        model.addAttribute("groupId", groupId);

        return "groupCourses";
    }

    @GetMapping("/save/gr/{groupId}")
    public String showCourafdcseSavePage(@PathVariable Long groupId, Model model) {

        model.addAttribute("groupId", groupId);
        model.addAttribute("emptyCourseGr", new Course());

        return "saveGroup'sCourse";
    }

    @PostMapping("/save/gr/{groupId}")
    public String saveCousfvrse(Course course,
                             @PathVariable Long groupId) {

        courseService.saveGr(groupId, course);

        return "redirect:/api/courses/find/by/gr/" + groupId;
    }

    @GetMapping("/edit/{courseId}")
    public String editComdfpany(@PathVariable Long courseId, Model model) {

        Course course = courseService.findById(courseId);

        model.addAttribute("course", course);

        return "editCourse";
    }
    @PostMapping("/edit/{courseId}")
    public String saveStudfvent(Course course, @PathVariable Long courseId) {


        courseService.update(course, courseId);

        return "redirect:/api/courses";
    }
    @GetMapping("/delete/{courseId}")
    public String saveStuddfvent(@PathVariable Long courseId) {


        courseService.delete(courseId);

        return "redirect:/api/courses";
    }
}
