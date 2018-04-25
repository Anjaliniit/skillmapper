/*package com.niit.skillmapper.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.niit.skillmapper.config.ApplicationContextConfig;
import com.niit.skillmapper.model.Employee;
import com.niit.skillmapper.model.Role;
import com.niit.skillmapper.repository.EmployeeRepository;
import javax.transaction.Transactional;


@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {ApplicationContextConfig.class })
public class EmployeeRepositoryTest {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private Employee employee;
	
	@Autowired
	Role role;
	
	@Before
	public void setup() {
		
		if(employeeRepository.getEmployeeByEmployeeId(29827)!=null) {
			employeeRepository.deleteEmployee(29827);
		}

		if(employeeRepository.getEmployeeByEmployeeId(21426)!=null) {
			employeeRepository.deleteEmployee(21426);
		}
		
		if(employeeRepository.getEmployeeByEmployeeId(17321)!=null) {
			employeeRepository.deleteEmployee(17321);
		}
		
		Employee employee1=new Employee();
		employee1.setEmailId("anjali.singhal@niit.com");
		employee1.setEmployeeId(21426);
		employee1.setFirstName("Anjali");
		employee1.setLastName("Singhal");
		employee1.setGender("Female");
		employee1.setManagerId(17321);
		employee1.setNoOfStudentTaught(500);
		employee1.setPassword("12345");
		employee1.setNoOfYrExperience(6);
		employee1.setStatus("false");
		employee1.setDateOfJoining("01/09/2010");
		employee1.setDepartment("CEB");
		employee1.setLocation("New Delhi");
		employee1.setRegion("North");
		employee1.setBusinessUnit("Sales");
		employeeRepository.addEmployee(employee1);
		
		
		
		Employee employee2=new Employee();
		employee2.setEmailId("rajesh.kumar@niit.com");
		employee2.setEmployeeId(17321);
		employee2.setFirstName("Rajesh");
		employee2.setLastName("Kumar");
		employee2.setGender("male");
		employee2.setManagerId(16859);
		employee2.setNoOfStudentTaught(500);
		employee2.setPassword("12345");
		employee2.setNoOfYrExperience(6);
		employee2.setStatus("false");
		employee2.setDateOfJoining("01/09/2010");
		employee2.setDepartment("CEB");
		employee2.setLocation("New Delhi");
		employee2.setRegion("North");
		employee2.setBusinessUnit("Sales");
		employeeRepository.addEmployee(employee2);	
	}
	
	
	@After
	public void teardown() {
		
			employeeRepository.deleteEmployee(employeeRepository.getEmployeeByEmployeeId(21426).getEmployeeId());
			employeeRepository.deleteEmployee(employeeRepository.getEmployeeByEmployeeId(17321).getEmployeeId());
			if(employeeRepository.getEmployeeByEmployeeId(29827)!=null) {
				employeeRepository.deleteEmployee(employeeRepository.getEmployeeByEmployeeId(29827).getEmployeeId());
			
		}

	}


		
	
	@Test
	public void testaddEmployee()
	{
		employee.setEmailId("aradhana.@niit.com");
		employee.setEmployeeId(29827);
		employee.setFirstName("Aradhana");
		employee.setLastName("");
		employee.setGender("Female");
		employee.setManagerId(17321);
		employee.setNoOfStudentTaught(500);
		employee.setPassword("12345");
		employee.setNoOfYrExperience(6);
		employee.setStatus("false");
		employee.setDateOfJoining("01/09/2010");
		employee.setDepartment("CEB");
		employee.setLocation("New Delhi");
		employee.setRegion("North");
		employee.setBusinessUnit("Sales");
		
		assertEquals("Creation of Employee Successful",true,employeeRepository.addEmployee(employee));
	}
	
	@Test
	public void testaddEmployeeFailure() {

		employee.setEmailId("aradhana.@niit.com");
		employee.setEmployeeId(29827);
		employee.setFirstName("Aradhana");
		employee.setLastName("");
		employee.setGender("Female");
		employee.setManagerId(17321);
		employee.setNoOfStudentTaught(500);
		employee.setPassword("12345");
		employee.setNoOfYrExperience(6);
		employee.setStatus("false");
		employee.setDateOfJoining("01/09/2010");
		employee.setDepartment("CEB");
		employee.setLocation("New Delhi");
		employee.setRegion("North");
		employee.setBusinessUnit("Sales");
		assertNotEquals("Creation of employee Failed",true,employeeRepository.getEmployeeByEmployeeId(29827));
	}

	@Ignore
	@Test
	public void testupdateEmployee()
	{
		
		employee.setEmailId("aradhana.@niit.com");
		employee.setEmployeeId(29827);
		employee.setFirstName("Aradhana");
		employee.setLastName("");
		employee.setGender("Female");
		employee.setManagerId(17321);
		employee.setNoOfStudentTaught(500);
		employee.setPassword("12345");
		employee.setNoOfYrExperience(6);
		employee.setStatus("false");
		employee.setDateOfJoining("01/09/2010");
		employee.setDepartment("CEB");
		employee.setLocation("New Delhi");
		employee.setRegion("North");
		employee.setBusinessUnit("Sales");		
		employee=employeeRepository.getEmployeeByEmployeeId(29827);
		employeeRepository.addEmployee(employee);

		employee.setNoOfStudentTaught(500);
		employee.setManagerId(17321);
		employee.setPassword("45678");
		employee.setNoOfYrExperience(7);
		employeeRepository.updateEmployee(employee);
		assertEquals("45678",employeeRepository.getEmployeeByEmployeeId(29827).getPassword());
	}
	

	@Test
	public void testGetAllEmployee()
	{
		List<Employee> list=employeeRepository.getAllEmployees();
		assertEquals("reteriving employees data",true,list.size()>0);
	}
	
	@Test
	public void testGetAllEmployeeFailure()
	{
		List<Employee> list=employeeRepository.getAllEmployees();
		assertEquals("reteriving employees data failure",false,list.size()<0);
	}
	
	

	@Test
	public void testGetEmployeeById()
	{
		
		employee=employeeRepository.getEmployeeByEmployeeId(29827);
		assertNotNull("Retrieval of employee failed.", employee);
	}
	
	
	
	@Test
	public void testDeleteEmployee()
	{
		boolean status=false;
		if(employeeRepository.getEmployeeByEmployeeId(29827)!=null) {
			status=employeeRepository.deleteEmployee(29827);
		}
		assertEquals("Employee is successfully deleted",false,status);
	}	
	
		
	
	@Test
	public void testUserAuthentication() {

		assertNotNull("Authentication failed for legitimate user.",employeeRepository.validate(29827,"12345"));	
	}


	@Test
	public void testEmployeeAuthenticationFailure() {

		assertNotEquals("Authentication logic is not correct. Please check. User with incorrect password is also being logged in", true,employeeRepository.validate(29827,"12345"));		
	}


	
	
	
	
	
	

}*/
