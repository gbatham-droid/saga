package com.example.service_b.service_b.repository;

import com.example.service_b.service_b.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {}
