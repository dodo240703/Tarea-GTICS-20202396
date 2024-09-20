package com.example.tareagtics20202396.Controllers;

import com.example.tareagtics20202396.Models.Entities.Employee;
import com.example.tareagtics20202396.Models.Repositories.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/empleado")
public class EmployeeController {

    final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/listaEmpleados")
    public String showEmpleados(Model model){
        List<Employee> listaEmpleados = employeeRepository.findAll();
        model.addAttribute("listaEmpleados", listaEmpleados);
        return "Employee/empleados";
    }
}
