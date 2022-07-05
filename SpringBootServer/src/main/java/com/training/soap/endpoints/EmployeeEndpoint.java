package com.training.soap.endpoints;

import java.util.ArrayList;
import java.util.List;

import com.training.soap.entity.Employee;
import com.training.soap.gen.*;
import com.training.soap.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
public class EmployeeEndpoint {
	private static final String NAMESPACE_URI = "http://www.concretepage.com/article-ws";
	@Autowired
	private EmployeeService employeeService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmployeeByIdRequest")
	@ResponsePayload
	public GetEmployeeByIdResponse getEmployee(@RequestPayload GetEmployeeByIdRequest request) {
		GetEmployeeByIdResponse response = new GetEmployeeByIdResponse();
		EmployeeInfo employeeInfo = new EmployeeInfo();
		BeanUtils.copyProperties(employeeService.getEmployeeById(request.getEmployeeId()), employeeInfo);
		response.setEmployeeInfo(employeeInfo);
		return response;
	}
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllEmployeeRequest")
	@ResponsePayload
	public GetAllEmployeeResponse getEmployees() {
		GetAllEmployeeResponse response = new GetAllEmployeeResponse();
		List<EmployeeInfo> employeeInfoList = new ArrayList<>();
		List<Employee> articleList = employeeService.getAllEmployee();
		for (int i = 0; i < articleList.size(); i++) {
			EmployeeInfo ob = new EmployeeInfo();
		     BeanUtils.copyProperties(employeeInfoList.get(i), ob);
			employeeInfoList.add(ob);
		}
		response.getEmployeeInfo().addAll(employeeInfoList);
		return response;
	}	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addEmployeeRequest")
	@ResponsePayload
	public AddEmployeeResponse addEmployee(@RequestPayload AddEmployeeRequest request) {
		AddEmployeeResponse response = new AddEmployeeResponse();
    	ServiceStatus serviceStatus = new ServiceStatus();		
		Employee employee = new Employee();
		employee.setName(request.getName());
		employee.setDesignation(request.getDesignation());
		employee.setDepartment(request.getDepartment());

        boolean flag = employeeService.addEmployee(employee);
        if (flag == false) {
        	serviceStatus.setStatusCode("CONFLICT");
        	serviceStatus.setMessage("Content Already Available");
        	response.setServiceStatus(serviceStatus);
        } else {
			EmployeeInfo employeeInfo = new EmployeeInfo();
	        BeanUtils.copyProperties(employee, employeeInfo);
			response.setEmployeeInfo(employeeInfo);
        	serviceStatus.setStatusCode("SUCCESS");
        	serviceStatus.setMessage("Content Added Successfully");
        	response.setServiceStatus(serviceStatus);
        }
        return response;
	}
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateEmployeeRequest")
	@ResponsePayload
	public UpdateEmployeeResponse updateEmployee(@RequestPayload UpdateEmployeeRequest request) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(request.getEmployeeInfo(), employee);
		employeeService.updateEmployee(employee);
    	ServiceStatus serviceStatus = new ServiceStatus();
    	serviceStatus.setStatusCode("SUCCESS");
    	serviceStatus.setMessage("Content Updated Successfully");
    	UpdateEmployeeResponse response = new UpdateEmployeeResponse();
    	response.setServiceStatus(serviceStatus);
    	return response;
	}
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteEmployeeRequest")
	@ResponsePayload
	public DeleteEmployeeResponse deleteEmployee(@RequestPayload DeleteEmployeeRequest request) {
		Employee employee = employeeService.getEmployeeById(request.getEmployeeId());
    	ServiceStatus serviceStatus = new ServiceStatus();
		if (employee == null ) {
	    	serviceStatus.setStatusCode("FAIL");
	    	serviceStatus.setMessage("Content Not Available");
		} else {
			employeeService.deleteEmployee(employee);
	    	serviceStatus.setStatusCode("SUCCESS");
	    	serviceStatus.setMessage("Content Deleted Successfully");
		}
    	DeleteEmployeeResponse response = new DeleteEmployeeResponse();
    	response.setServiceStatus(serviceStatus);
		return response;
	}	
}
