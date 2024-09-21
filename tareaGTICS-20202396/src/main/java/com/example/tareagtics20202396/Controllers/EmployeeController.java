package com.example.tareagtics20202396.Controllers;

import com.example.tareagtics20202396.Models.Entities.Employee;
import com.example.tareagtics20202396.Models.Entities.Job;
import com.example.tareagtics20202396.Models.Repositories.EmployeeRepository;
import com.example.tareagtics20202396.Models.Repositories.JobRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/empleado")
public class EmployeeController {

    final EmployeeRepository employeeRepository;
    final JobRepository jobRepository;

    public EmployeeController(EmployeeRepository employeeRepository, JobRepository jobRepository) {
        this.employeeRepository = employeeRepository;
        this.jobRepository = jobRepository;
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

    @GetMapping("/crear")
    public String crearEmpleado(Model model, Employee employee) {
        List<Employee> listaEmpleados = employeeRepository.findAll();
        List<Job> listaJobs = jobRepository.findAll();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaActual = LocalDate.now().format(formatter);

        model.addAttribute("fechaActual", fechaActual);
        model.addAttribute("listaEmpleados", listaEmpleados);
        model.addAttribute("listaJobs", listaJobs);
        return "Employee/crearEmployee";
    }

    @GetMapping("/editar")
    public String editarEmpleado(Model model, @RequestParam("id") int id) {
        List<Employee> listaEmpleados = employeeRepository.findAll();
        List<Job> listaJobs = jobRepository.findAll();

        model.addAttribute("listaEmpleados", listaEmpleados);
        model.addAttribute("listaJobs", listaJobs);

        Optional<Employee> optEmpleado = employeeRepository.findById(id);
        if(optEmpleado.isPresent()) {
            Employee employee = optEmpleado.get();
            model.addAttribute("empleado", employee);
            return "Employee/editarEmployee";
        }else {
            return "redirect:/empleado/listaEmpleados";
        }


    }

    @PostMapping("/guardar")
    public String guardar(Employee employee, RedirectAttributes attr) {
        if(employee.getEmployeeId()==null){
            attr.addFlashAttribute("msg","El empleado se ha creado exitosamente");
        }else {
            attr.addFlashAttribute("msg","El empleado se ha actualizado exitosamente");
        }
        employeeRepository.save(employee);

        return "redirect:/empleado/listaEmpleados";
    }

    @GetMapping("/borrar")
    public String borrarEmpleado(Model model, @RequestParam("id") int id, RedirectAttributes attr) {
        Optional<Employee> optEmpleado = employeeRepository.findById(id);

        if(optEmpleado.isPresent()) {

            try{
                employeeRepository.deleteById(id);
                attr.addFlashAttribute("msg","El empleado se ha borrado exitosamente");
            }catch (Exception e){
                attr.addFlashAttribute("msg2","No es posible borrar el empleado");
            }

        }
        return "redirect:/empleado/listaEmpleados";
    }
}
