package com.imaginnovation.employee.service.impl;

import com.imaginnovation.employee.enitiy.Employee;
import com.imaginnovation.employee.repo.EmployeeRepository;
import com.imaginnovation.employee.response.TaxDeductionResponse;
import com.imaginnovation.employee.service.IEmployeeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Rabiroshan Shubhankar
 * DATE : 02-10-2023
 */
@Service
@Log4j2
public class EmployeeService implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public void saveEmployee(Employee employee) {
        log.debug("saveEmployee() in class EmployeeService -- Started");
        employeeRepository.save(employee);
        log.debug("saveEmployee() in class EmployeeService -- Ended");
    }

    @Override
    public TaxDeductionResponse calculateTaxDeduction(String employeeId) {
       log.debug("caluclateTaxDeduction in EmployeeService -- Started");
        Optional<Employee> employeeOptional = employeeRepository.findByEmployeeId(employeeId);

        if (employeeOptional.isEmpty()) {
            return null;
        }

        Employee employee = employeeOptional.get();

        // Calculate financial year start and end dates
        LocalDate financialYearStart = LocalDate.of(LocalDate.now().getYear(), Month.APRIL, 1);
        LocalDate financialYearEnd = LocalDate.of(LocalDate.now().getYear() + 1, Month.MARCH, 31);

        // Calculate the number of months in the current financial year
        long numberOfMonthsWorked = ChronoUnit.MONTHS.between(
                employee.getDoj().isAfter(financialYearStart) ? employee.getDoj() : financialYearStart,
                financialYearEnd.plusDays(1)
        );

        // Calculate total yearly salary
        BigDecimal yearlySalary = employee.getSalary().multiply(BigDecimal.valueOf(numberOfMonthsWorked));

        // Determine the applicable tax slab
        BigDecimal taxAmount = BigDecimal.ZERO;

        if (yearlySalary.compareTo(BigDecimal.valueOf(250000)) > 0) {
            taxAmount = yearlySalary.min(BigDecimal.valueOf(500000)).subtract(BigDecimal.valueOf(250000))
                    .multiply(BigDecimal.valueOf(0.05));
        }

        if (yearlySalary.compareTo(BigDecimal.valueOf(500000)) > 0) {
            taxAmount = taxAmount.add(yearlySalary.min(BigDecimal.valueOf(1000000)).subtract(BigDecimal.valueOf(500000))
                    .multiply(BigDecimal.valueOf(0.10)));
        }

        if (yearlySalary.compareTo(BigDecimal.valueOf(1000000)) > 0) {
            taxAmount = taxAmount.add(yearlySalary.subtract(BigDecimal.valueOf(1000000))
                    .multiply(BigDecimal.valueOf(0.20)));
        }

        // Apply 2% cess on the amount exceeding 2,500,000 (2.5 lkh)
        BigDecimal cessAmount = BigDecimal.ZERO;
        if (yearlySalary.compareTo(BigDecimal.valueOf(2500000)) > 0) {
            cessAmount = yearlySalary.subtract(BigDecimal.valueOf(2500000)).multiply(BigDecimal.valueOf(0.02));
        }

        // Calculate total tax amount, including cess
        BigDecimal totalTaxAmount = taxAmount.add(cessAmount);

        // Prepare the response
        TaxDeductionResponse response = new TaxDeductionResponse(
                employee.getEmployeeId(),
                employee.getFirstName(),
                employee.getLastName(),
                yearlySalary,
                taxAmount,
                cessAmount
        );
       log.debug("caluclateTaxDeduction in EmployeeService -- Ended");

        return response;
    }
}
