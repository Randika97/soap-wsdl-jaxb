package com.training.soap.repository;

import java.util.List;

import com.training.soap.entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long>  {
	Employee findByEmployeeId(long articleId);
    List<Employee> findByNameAndDepartment(String name, String department);
}
