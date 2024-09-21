package com.example.tareagtics20202396.Models.Repositories;

import com.example.tareagtics20202396.Models.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // Filtro con OR para nombre, apellido, puesto, departamento o ciudad
    List<Employee> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrJob_JobNameContainingIgnoreCaseOrDepartment_DepartmentNameContainingIgnoreCaseOrDepartment_Location_CityNameContainingIgnoreCase(
            String firstName,
            String lastName,
            String jobName,
            String departmentName,
            String cityName
    );
}
