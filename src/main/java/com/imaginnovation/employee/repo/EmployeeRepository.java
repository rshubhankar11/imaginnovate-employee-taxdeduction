package com.imaginnovation.employee.repo;

import com.imaginnovation.employee.enitiy.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Rabiroshan Shubhankar
 * DATE : 02-10-2023
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmployeeId(String employeeId);
}

