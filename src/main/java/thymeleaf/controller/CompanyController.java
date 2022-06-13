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
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;
    private final CourseService courseService;

    public CompanyController(CompanyService companyService, CourseService courseService) {
        this.companyService = companyService;
        this.courseService = courseService;
    }

    @ModelAttribute("companyList")
    public List<Company> allCompanies() {
        return companyService.getAllCompanies();
    }

    @RequestMapping
    public String findAll() {
        return "companyPage";
    }

    @GetMapping("/save")
    public String saveCompanyPage(Model model) {

        model.addAttribute("emptyCompany", new Company());

        return "saveCompanyPage";
    }

    @PostMapping("/save")
    public String saveStudent(Company company) {

        companyService.save(company);

        return "redirect:/api/companies";
    }

//    @GetMapping("/find/by/{companyId}")
//    public String findAllStudentsByCourseId(@PathVariable Long companyId, Model model) {
//
//        List<Course> courses = courseService.findByCompanyId(companyId);
//
//        model.addAttribute("courses", courses);
//        model.addAttribute("companyId", companyId);
//
//        return "courses";
//    }

    @GetMapping("/find/by/{courseId}")
    public String findAllStudfentsByCourseId(@PathVariable Long courseId, Model model) {

        Company company = courseService.findByCourseId(courseId);

        model.addAttribute("company", company);
        model.addAttribute("courseId", courseId);

        return "courseCompany";
    }

    @GetMapping("/edit/{companyId}")
    public String editCompany(@PathVariable Long companyId, Model model) {

        Company company = companyService.findById(companyId);

        model.addAttribute("company", company);

        return "editCompany";
    }
    @PostMapping("/edit/{companyId}")
    public String saveStudfvent(Company company, @PathVariable Long companyId) {


        companyService.update(company, companyId);

        return "redirect:/api/companies";
    }
    @GetMapping("/delete/{companyId}")
    public String saveStuddfvent(@PathVariable Long companyId) {


        companyService.delete(companyId);

        return "redirect:/api/companies";
    }
}
