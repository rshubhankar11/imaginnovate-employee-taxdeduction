package com.imaginnovation.employee.controller;

import com.imaginnovation.employee.enitiy.Employee;
import com.imaginnovation.employee.repo.EmployeeRepository;
import com.imaginnovation.employee.response.TaxDeductionResponse;
import com.imaginnovation.employee.service.IEmployeeService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * This is the main Controller class for storing Employee and calculating
 * Employee Tax
 *
 *
 * @author Rabiroshan Shubhankar
 * DATE : 02-10-2023
 */
@RestController
@RequestMapping("api/v1/employees")
@Log4j2
public class EmployeeController {



    @Autowired
    private IEmployeeService employeeService;


    /**
     * Endpoint to store employee details
     * @param employee
     * @return
     */
    @PostMapping
    public ResponseEntity<?> storeEmployee(@RequestBody @Valid Employee employee) {
        // Validation errors are handled automatically due to @Valid annotation
        employeeService.saveEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body("Employee details stored successfully.");
    }


    /**
     *  This is the endpoint to return tax deduction for the current financial year
     * @param employeeId
     * @return
     */
    @GetMapping("/tax-deduction/{employeeId}")
    public ResponseEntity<?> calculateTaxDeduction(@PathVariable String employeeId) {

        TaxDeductionResponse response= employeeService.calculateTaxDeduction(employeeId);

        return ResponseEntity.ok(response);
    }

}

