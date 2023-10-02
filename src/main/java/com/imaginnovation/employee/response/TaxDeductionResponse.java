package com.imaginnovation.employee.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Rabiroshan Shubhankar
 * DATE : 02-10-2023
 */
@Data
@AllArgsConstructor
public class TaxDeductionResponse {
    private String employeeCode;
    private String firstName;
    private String lastName;
    private BigDecimal yearlySalary;
    private BigDecimal taxAmount;
    private BigDecimal cessAmount;
}
