package thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import thymeleaf.model.Company;
import thymeleaf.model.Course;
import thymeleaf.model.Group;
import thymeleaf.service.GroupService;

import java.util.List;

@Controller
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @ModelAttribute("groupList")
    public List<Group> allGroups() {
        return groupService.getAllGroups();
    }

    @RequestMapping
    public String findAll() {
        return "groupPage";
    }

    @GetMapping("/save")
    public String saveGroupPage(Model model) {

        model.addAttribute("emptyGroup", new Group());

        return "saveGroupPage";
    }

    @PostMapping("/save")
    public String saveGroup(Group group) {

        groupService.save(group);

        return "redirect:/api/groups";
    }

    @GetMapping("/find/by/{courseId}")
    public String findAllGroupsByCourseId(@PathVariable Long courseId, Model model) {

        List<Group> groups = groupService.findByCourseId(courseId);

        model.addAttribute("groups", groups);
        model.addAttribute("courseId", courseId);

        return "courseGroups";
    }

    @GetMapping("/save/{courseId}")
    public String showGroupSavePage(@PathVariable Long courseId, Model model) {

        model.addAttribute("courseId", courseId);
        model.addAttribute("emptyGroup", new Group());

        return "saveCourse'sGroup";
    }

    @PostMapping("/save/{courseId}")
    public String saveGroup(Group group,
                             @PathVariable Long courseId) {

        groupService.save(courseId, group);

        return "redirect:/api/groups/find/by/" + courseId;
    }

    @GetMapping("/edit/{groupId}")
    public String editCompany(@PathVariable Long groupId, Model model) {

        Group group = groupService.findById(groupId);

        model.addAttribute("group", group);

        return "editGroup";
    }
    @PostMapping("/edit/{groupId}")
    public String saveStudfvent(Group group, @PathVariable Long groupId) {


        groupService.update(group, groupId);

        return "redirect:/api/groups";
    }
    @GetMapping("/delete/{groupId}")
    public String saveStuddfvent(@PathVariable Long groupId) {


        groupService.delete(groupId);

        return "redirect:/api/groups";
    }
}
