package com.example.projectmanagement.service;

import com.example.projectmanagement.entity.Staff;
import com.example.projectmanagement.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    @Autowired
    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    @Override
    public long countByStatus(String status) {
        return staffRepository.countByStatus(status);
    }

    @Override
    public void save(Staff staff) {
        staffRepository.save(staff);
    }

    @Override
    public Staff findById(Long id) {
        return staffRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        staffRepository.deleteById(id);
    }
}
