package com.example.tareagtics20202396.Controllers;

import com.example.tareagtics20202396.Models.Entities.Employee;
import com.example.tareagtics20202396.Models.Repositories.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/empleado")
public class EmployeeController {

    final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/listaEmpleados")
    public String showEmpleados(Model model, @RequestParam(value = "query", required = false) String query) {
        List<Employee> listaEmpleados;

        if (query != null && !query.isEmpty()) {
            listaEmpleados = employeeRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrJob_JobNameContainingIgnoreCaseOrDepartment_DepartmentNameContainingIgnoreCaseOrDepartment_Location_CityNameContainingIgnoreCase(
                    query, query, query, query, query);
        } else {
            listaEmpleados = employeeRepository.findAll();
        }

        model.addAttribute("listaEmpleados", listaEmpleados);
        model.addAttribute("query", query);  // Para mantener el texto en el campo de búsqueda
        return "Employee/empleados";
    }
}
