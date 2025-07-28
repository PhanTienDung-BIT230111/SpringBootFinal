package com.example.projectmanagement.controller;

import com.example.projectmanagement.service.ProjectService;
import com.example.projectmanagement.entity.Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // ✅ import đúng
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("runningCount", projectService.countByStatus("Đang thực hiện"));
        model.addAttribute("contractPending", 0);
        model.addAttribute("staffCount", 0);
        model.addAttribute("totalRevenue", "2.5B VND");

        List<Project> recentProjects = projectService.findRecentProjects(5);
        model.addAttribute("recentProjects", recentProjects);

        return "home/home";
    }
}
