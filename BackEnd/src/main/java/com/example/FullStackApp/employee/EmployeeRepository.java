package com.example.FullStackApp.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("select s from Employee s where s.emailId= ?1")
    Optional<Employee> findEmployeeByEmail(String emailId);
}
