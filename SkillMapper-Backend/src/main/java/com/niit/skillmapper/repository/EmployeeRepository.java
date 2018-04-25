package com.niit.skillmapper.repository;

import java.util.List;

import com.niit.skillmapper.model.Employee;

public interface EmployeeRepository {


	boolean addEmployee(Employee employee);
	boolean updateEmployee(Employee employee);
	boolean deleteEmployee(int employeeId);
	List<Employee> getAllEmployees();
	Employee getEmployeeByEmployeeId(int employeeId);
	List<Employee> getAllEmployeesByManagerId(int managerId);
	public boolean validate(int employeeId, String password);
	public boolean exists(int employeeId);
	boolean approveEmployee(int employeeId);	
	
	
	
	List<Employee> getAllEmployeesByLocation(String location);
	List<Employee> getAllEmployeesByBusinessUnit(String businessUnit);
	List<Employee> getAllEmployeesBySupervisor(String supervisorName);
	List<Employee> getAllEmployeesByRegion(String region);
	
}
