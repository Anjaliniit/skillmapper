package com.niit.skillmapper.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.skillmapper.model.Employee;
import com.niit.skillmapper.repository.EmployeeRepository;
import com.niit.skillmapper.service.EmailService;

@RestController
public class EmployeeController {

	@Autowired
	EmailService emailService;
	
	@Autowired
	private EmployeeRepository employeeRespository;

	@GetMapping("/")
	public String home()
	{
		return "index";
	}


	@GetMapping("/api/employee")
	public ResponseEntity<Object>getAllEmployee(HttpSession session)
	{
		if(session.getAttribute("loggedInUser")!=null)
		{
			List<Employee> employeeList = employeeRespository.getAllEmployees();
			return new ResponseEntity<Object>(employeeList,HttpStatus.OK);
		}
		else
			return new ResponseEntity<Object>("Please Login first",HttpStatus.UNAUTHORIZED); 
	}


	@GetMapping("/api/employee/{employeeId}")
	public ResponseEntity<Object> getEmployeeById(@PathVariable("employeeId") int employeeId,HttpSession session){
		if(session.getAttribute("loggedInUser")!=null)
		{
			Employee employee = employeeRespository.getEmployeeByEmployeeId(employeeId);
			if(employee==null)
				return new ResponseEntity<Object>("No User Found",HttpStatus.NOT_FOUND);
			else
				return new ResponseEntity<Object>(employee,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Object>("You R Not Authorized, Please login first",HttpStatus.UNAUTHORIZED);
		}
	}


	@PostMapping("/api/employee")
	public ResponseEntity<Employee>createEmployee(@RequestBody Employee employee)
	{
		if(employeeRespository.getEmployeeByEmployeeId(employee.getEmployeeId())!=null)
		{
			return new ResponseEntity<Employee>(employee , HttpStatus.CONFLICT);
		}
		else
		{
			boolean status=employeeRespository.addEmployee(employee);
			if(status)
			{	
				emailService.createdEmployeeMessage(employee,"Your account will be activated!");
				return new ResponseEntity<Employee>(employee , HttpStatus.CREATED);
			}
			else
			{
				return new ResponseEntity<Employee>(employee , HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}



	@GetMapping("/api/employee/employeelist/{managerId}")
	public ResponseEntity<Object> getEmployeeByManagerId(@PathVariable("managerId") int managerId,HttpSession session){
		if(session.getAttribute("loggedInUser")!=null)
		{
			Employee employee = employeeRespository.getEmployeeByEmployeeId(managerId);
			if(employee==null)
				return new ResponseEntity<Object>("Employee does not exists",HttpStatus.NOT_FOUND);
			else
			{
				List<Employee> employeeList = employeeRespository.getAllEmployeesByManagerId(managerId);
				return new ResponseEntity<Object>(employeeList,HttpStatus.OK);
			}
		}
		else
		{
			return new ResponseEntity<Object>("You are Not Authorized, Please login first",HttpStatus.UNAUTHORIZED);
		}
	}


	@PutMapping("/api/employee/approve/{employeeId}")
	public ResponseEntity<Object> approveEmployee(@PathVariable("employeeId") int employeeId,HttpSession session){
		if(session.getAttribute("loggedInUser")!=null)
		{
			Employee employee = employeeRespository.getEmployeeByEmployeeId(employeeId);
			if(employee==null)
				return new ResponseEntity<Object>("No Employee Found",HttpStatus.NOT_FOUND);
			else 
			{
				employeeRespository.approveEmployee(employeeId);
				return new ResponseEntity<Object>("Employee is approved",HttpStatus.OK);
			}
		}
		else
		{
			return new ResponseEntity<Object>("You are Not Authorized, Please login first",HttpStatus.UNAUTHORIZED);
		}
	}


	@PutMapping("/api/employee/{employeeId}")
	public ResponseEntity<Object>updateEmployee(@RequestBody Employee employee,@PathVariable("employeeId") int employeeId,HttpSession session)
	{
		if(session.getAttribute("loggedInUser")!=null)
		{
			if(employeeRespository.getEmployeeByEmployeeId(employeeId)!=null)
			{
				employeeRespository.updateEmployee(employee);			
				return new ResponseEntity<Object>(employee , HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<Object>("Employee does not exists", HttpStatus.NOT_FOUND);
			}
		}
		else
		{
			return new ResponseEntity<Object>("You are Not Authorized, Please login first" , HttpStatus.UNAUTHORIZED);
		}
	}		


	@DeleteMapping("/api/employee/{employeeId}")
	public ResponseEntity<Object>deleteEmployee(@PathVariable("employeeId") int employeeId,HttpSession session)
	{
		if(session.getAttribute("loggedInUser")!=null)
		{
			if(employeeRespository.getEmployeeByEmployeeId(employeeId)!=null)
			{
				employeeRespository.deleteEmployee(employeeId);			
				return new ResponseEntity<Object>("Employee is deleted successfully" , HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<Object>("Employee does not exists", HttpStatus.NOT_FOUND);
			}
		}
		else
		{
			return new ResponseEntity<Object>("You are Not Authorized, Please login first" , HttpStatus.UNAUTHORIZED);
		}
	}		
}






