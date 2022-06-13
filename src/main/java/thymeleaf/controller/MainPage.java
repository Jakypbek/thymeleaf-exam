package thymeleaf.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainPage {

    @RequestMapping
    public String viewMainPage() {
        return "main_page";
    }
}