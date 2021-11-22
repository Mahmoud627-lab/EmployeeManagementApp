package com.example.FullStackApp.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository)
    {
        this.employeeRepository = employeeRepository;
    }



    //GET
    public List<Employee> getEmployees()
    {
        return employeeRepository.findAll();
    }

    //POST
    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    //GET BY ID
    public Optional<Employee> getEmployeeById(Long id) {
        boolean exists = employeeRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("employee with id" + id + "does not exists");
        }
        return employeeRepository.findById(id);
    }


    @Transactional
    public void updateEmployee(Long id, String firstName, String lastName, String emailId) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("employee with id" + id + "does not exists"));
        if (firstName != null && firstName.length() > 0 && !Objects.equals(employee.getFirstName(), firstName)) {
            employee.setFirstName(firstName);
        }

        if (lastName != null && lastName.length() > 0 && !Objects.equals(employee.getLastName(), lastName)) {
            employee.setLastName(lastName);
        }

        if (emailId != null && emailId.length() > 0 && !Objects.equals(employee.getEmailId(), emailId)) {
            Optional<Employee> employeeOptional = employeeRepository.findEmployeeByEmail(emailId);
            if (employeeOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            employee.setEmailId(emailId);
        }

    }


    public void deleteEmployee(Long id) {
        boolean exixts = employeeRepository.existsById(id);
        if (!exixts) {
            throw new IllegalStateException("student with id" + id + "does not exists");
        }
        employeeRepository.deleteById(id);
    }
}
