package com.imaginnovation.employee.service;

import com.imaginnovation.employee.enitiy.Employee;
import com.imaginnovation.employee.response.TaxDeductionResponse;

/**
 * @author Rabiroshan Shubhankar
 * DATE : 02-10-2023
 */
public interface IEmployeeService {

    /**
     * This method is to save a new employee
     * @param employee
     */
    public void saveEmployee(Employee employee);

    /**
     * This method is to calculate the Tax Deduction
     * @param employeeId
     * @return
     */
    public TaxDeductionResponse calculateTaxDeduction(String employeeId);
}
