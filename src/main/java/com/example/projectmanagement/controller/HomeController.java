package com.example.projectmanagement.controller;

import com.example.projectmanagement.service.ProjectService;
import com.example.projectmanagement.service.ContractService;
import com.example.projectmanagement.service.StaffService;
import com.example.projectmanagement.entity.Project;
import com.example.projectmanagement.entity.User;
import com.example.projectmanagement.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // ✅ import đúng
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProjectService projectService;
    
    @Autowired
    private ContractService contractService;
    
    @Autowired
    private StaffService staffService;
    
    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String home(Model model) {
        // Debug: Lấy user hiện tại
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = null;
        if (authentication != null && authentication.isAuthenticated() && 
            !"anonymousUser".equals(authentication.getName())) {
            loggedInUser = userService.getUserByTenDangNhap(authentication.getName());
        }
        
        System.out.println("=== DEBUG HOME ===");
        System.out.println("User: " + (loggedInUser != null ? loggedInUser.getTenDangNhap() : "null"));
        System.out.println("User ID: " + (loggedInUser != null ? loggedInUser.getId() : "null"));
        System.out.println("User Role: " + (loggedInUser != null ? loggedInUser.getVaiTro() : "null"));
        System.out.println("=== END DEBUG HOME ===");
        
        // Thống kê theo adminId nếu user đã đăng nhập
        if (loggedInUser != null) {
            long runningCount = projectService.findByAdminId(loggedInUser.getId()).stream()
                    .filter(p -> "Đang thực hiện".equals(p.getStatus()))
                    .count();
            long contractPending = contractService.findByAdminId(loggedInUser.getId()).stream()
                    .filter(c -> "Chờ duyệt".equals(c.getStatus()))
                    .count();
            long staffCount = staffService.findByAdminId(loggedInUser.getId()).size();
            
            model.addAttribute("runningCount", runningCount);
            model.addAttribute("contractPending", contractPending);
            model.addAttribute("staffCount", staffCount);
        } else {
            model.addAttribute("runningCount", 0);
            model.addAttribute("contractPending", 0);
            model.addAttribute("staffCount", 0);
        }
        
        model.addAttribute("totalRevenue", "2.5B VND");

        List<Project> recentProjects = projectService.findRecentProjects(5);
        model.addAttribute("recentProjects", recentProjects);

        return "home/home";
    }
}
