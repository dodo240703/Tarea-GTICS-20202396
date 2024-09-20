package com.example.tareagtics20202396.Models.Repositories;

import com.example.tareagtics20202396.Models.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
