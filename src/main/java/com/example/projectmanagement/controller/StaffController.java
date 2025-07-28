package com.example.projectmanagement.controller;

import com.example.projectmanagement.entity.Staff;
import com.example.projectmanagement.repository.StaffRepository;
import com.example.projectmanagement.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/home/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @Autowired
    private StaffRepository staffRepository; // ✅ Đã thêm @Autowired

    @GetMapping
    public String listStaff(Model model) {
        List<Staff> staffList = staffService.findAll();

        long activeCount = staffList.stream()
                .filter(s -> "Hoạt động".equalsIgnoreCase(s.getStatus()))
                .count();

        model.addAttribute("staffList", staffList);
        model.addAttribute("activeCount", activeCount);
        return "staff/stafflist";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("staff", new Staff());
        return "staff/staffform";
    }

    @PostMapping("/save")
    public String saveStaff(@ModelAttribute Staff staff,
                            @RequestParam("avatarFile") MultipartFile avatarFile) {
        try {
            if (avatarFile != null && !avatarFile.isEmpty()) {
                String filename = System.currentTimeMillis() + "_" + avatarFile.getOriginalFilename();
                String uploadDir = System.getProperty("user.dir") + "/uploads/avatar";
                Path uploadPath = Paths.get(uploadDir);
                Files.createDirectories(uploadPath); // Tạo thư mục nếu chưa có

                Path filePath = uploadPath.resolve(filename);
                avatarFile.transferTo(filePath.toFile());

                staff.setAvatar(filename);
            } else {
                if (staff.getId() != null) {
                    Staff existing = staffService.findById(staff.getId());
                    if (existing != null) {
                        staff.setAvatar(existing.getAvatar());
                    }
                }
            }

            staffService.save(staff);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/home/staff";
    }

    @PostMapping("/update")
    public String updateStaff(@ModelAttribute Staff staff) {
        staff.setUpdatedAt(LocalDateTime.now());
        staffService.save(staff);
        return "redirect:/home/staff";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Staff staff = staffService.findById(id);
        model.addAttribute("staff", staff);
        return "staff/staffform";
    }

    @GetMapping("/detail/{id}")
    public String viewStaffDetail(@PathVariable Long id, Model model) {
        Staff staff = staffService.findById(id);
        model.addAttribute("staff", staff);
        return "staff/detail";
    }

    @GetMapping("/delete/{id}")
    public String deleteStaff(@PathVariable Long id) {
        staffService.deleteById(id);
        return "redirect:/home/staff";
    }

    @GetMapping("/filter")
    public String filterByPosition(@RequestParam("position") String position, Model model) {
        List<Staff> filteredList = staffRepository.findByPosition(position);
        model.addAttribute("staffList", filteredList);
        model.addAttribute("activeCount", staffRepository.countByStatus("Hoạt động"));
        return "staff/stafflist";
    }
    @GetMapping("/search")
    public String searchStaff(@RequestParam("keyword") String keyword, Model model) {
        List<Staff> staffList = staffRepository.findByFullNameContainingIgnoreCase(keyword);
        model.addAttribute("staffList", staffList);
        model.addAttribute("keyword", keyword);
        return "staff/stafflist"; // hoặc tên template hiển thị danh sách nhân sự
    }
}
