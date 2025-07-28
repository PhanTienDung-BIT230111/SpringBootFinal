package com.example.projectmanagement.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String avatar;
    private String fullName;
    private String email;
    private String phone;
    private String birthDate;
    private String position;
    private String department;
    private int salary;
    private String startDate;
    private String contractType;
    private String status; // Hoạt động, Nghỉ phép, Thực tập
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "can_edit_project")
    private Boolean canEditProject;

    @Column(name = "can_view_project")
    private Boolean canViewProject;

    @Column(name = "can_manage_staff")
    private Boolean canManageStaff;
    public Staff() {
    }

    public Staff(Long id, String fullName, String email, String phone, String birthDate,
                 String position, String department, int salary, String startDate,
                 String contractType, String status) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.position = position;
        this.department = department;
        this.salary = salary;
        this.startDate = startDate;
        this.contractType = contractType;
        this.status = status;
    }

    // Getters và Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public boolean isCanViewProject() {
        return Boolean.TRUE.equals(canViewProject); // không lỗi nếu null
    }

    public void setCanViewProject(boolean canViewProject) {
        this.canViewProject = canViewProject;
    }

    public boolean isCanEditProject() {
        return Boolean.TRUE.equals(canEditProject);
    }

    public void setCanEditProject(boolean canEditProject) {
        this.canEditProject = canEditProject;
    }

    public boolean isCanManageStaff() {
        return Boolean.TRUE.equals(canManageStaff);
    }

    public void setCanManageStaff(boolean canManageStaff) {
        this.canManageStaff = canManageStaff;
    }
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
