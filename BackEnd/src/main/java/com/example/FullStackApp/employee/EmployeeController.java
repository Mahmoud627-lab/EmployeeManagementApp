package com.example.FullStackApp.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping(path = "/api/v1")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {  //the reference is passed inside of the controller
        this.employeeService = employeeService;
    }


    @GetMapping(path = "/employees")
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @PostMapping(path = "/employees")
    public void registerNewEmployee(@RequestBody Employee employee){
        employeeService.addEmployee(employee);
    }

    @GetMapping(path = "/employees/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable("id") Long id){
        return employeeService.getEmployeeById(id);
    }


    @PutMapping(path = "{id}")
    public void updateEmployee(
            @PathVariable ("id") Long id,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String emailId) {
        employeeService.updateEmployee(id, firstName, lastName, emailId);
    }

    @DeleteMapping(path = "/employees/{id}")
    public void deleteEmployee(@PathVariable ("id") Long id){
        employeeService.deleteEmployee(id);
    }
}
