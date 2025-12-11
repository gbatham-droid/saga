package com.example.service_c.service_c.repository;

import com.example.service_c.service_c.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {}
