package com.training.soap.service;

import java.util.ArrayList;
import java.util.List;

import com.training.soap.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.soap.repository.EmployeeRepository;
@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	@Override
	public Employee getEmployeeById(long employeeId) {
		Employee obj = employeeRepository.findByEmployeeId(employeeId);
		return obj;
	}	
	@Override
	public List<Employee> getAllEmployee(){
		List<Employee> list = new ArrayList<>();
		employeeRepository.findAll().forEach(e -> list.add(e));
		return list;
	}
	@Override
	public synchronized boolean addEmployee(Employee employee){
	   List<Employee> list = employeeRepository.findByNameAndDepartment(employee.getName(), employee.getDepartment());
       if (list.size() > 0) {
    	   return false;
       } else {
		   employee = employeeRepository.save(employee);
    	   return true;
       }
	}
	@Override
	public void updateEmployee(Employee employee) {
		employeeRepository.save(employee);
	}
	@Override
	public void deleteEmployee(Employee employee) {
		employeeRepository.delete(employee);
	}
}
