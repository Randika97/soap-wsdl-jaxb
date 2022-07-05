package com.training.soap.service;

import java.util.List;

import com.training.soap.entity.Employee;

public interface EmployeeService {
     List<Employee> getAllEmployee();
     Employee getEmployeeById(long employeeId);
     boolean addEmployee(Employee employee);
     void updateEmployee(Employee employee);
     void deleteEmployee(Employee employee);
}
