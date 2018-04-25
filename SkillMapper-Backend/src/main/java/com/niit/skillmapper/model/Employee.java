package com.niit.skillmapper.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Entity
@Component
public class Employee implements Serializable {

	@Id
	private int employeeId;
	@NotEmpty(message="First name cannot be empty")
	private String firstName;
	private String lastName;
	@Column(unique=true)
	@Email
	@NotEmpty(message="EmailId cannot be empty")
	private String emailId;
	private double noOfYrExperience;
	private int noOfStudentTaught;
	private String status;
	@NotEmpty(message="password can not be null")
	private String password;
	private int managerId;
	private String gender;
	private String location;
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	private String department;
	private String dateOfJoining;
	private String businessUnit;
	private String region;
	private String roleName;
	
	private long contactDetails;
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public double getNoOfYrExperience() {
		return noOfYrExperience;
	}
	public void setNoOfYrExperience(double noOfYrExperience) {
		this.noOfYrExperience = noOfYrExperience;
	}
	public int getNoOfStudentTaught() {
		return noOfStudentTaught;
	}
	public void setNoOfStudentTaught(int noOfStudentTaught) {
		this.noOfStudentTaught = noOfStudentTaught;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public String getBusinessUnit() {
		return businessUnit;
	}
	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
	public long getContactDetails() {
		return contactDetails;
	}
	public void setContactDetails(long contactDetails) {
		this.contactDetails = contactDetails;
	}
	
	
	
	
	
}
