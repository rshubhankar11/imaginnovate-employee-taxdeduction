# imaginnovate-employee-taxdeduction

## This is Spring boot project 

### Dependencies used in the Project

- Spring Starter Web 
- Spring Starter Data JPA
- H2
- Lombok
- Devtools
- Spring Starter Validation

### Project Description:

- I have used latest spring version which is 3.1.4 with java version as 17.
  - There are only 2 API end points 
    - To create Employee (http://localhost:8080/api/v1/employees)
      Sample JSON :
      ```json
              {
                    "id": 1,
                    "employeeId": "EMP12345",
                    "firstName": "John",
                    "lastName": "Doe",
                    "email": "johndoe@example.com",
                     "phoneNumbers": ["123-456-7890", "987-654-3210"],
                    "doj": "2023-01-15",
                    "salary": 50000000.00
              }
      ```
    - To Get the Tax Deduction Value (http://localhost:8080/api/v1/employees/tax-deduction/EMP12346)
     ```Json
          {
              "employeeCode": "EMP12346",
              "firstName": "John",
              "lastName": "Doe",
              "yearlySalary": 600000000.00,
              "taxAmount": 119862500.000,
              "cessAmount": 11950000.0000
          }
    ```