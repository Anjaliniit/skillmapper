package com.niit.skillmapper.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class EmployeesSkills {

	@Id
	private int id;
	private int employeeId;
	private int skillId;
/*	private int noOfStudentTaught;
	private int noOfYrExperience;*/
	private int rating;
	
	
	//getter-setter
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getSkillId() {
		return skillId;
	}
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
	/*public int getNoOfStudentTaught() {
		return noOfStudentTaught;
	}
	public void setNoOfStudentTaught(int noOfStudentTaught) {
		this.noOfStudentTaught = noOfStudentTaught;
	}
	public int getNoOfYrExperience() {
		return noOfYrExperience;
	}
	public void setNoOfYrExperience(int noOfYrExperience) {
		this.noOfYrExperience = noOfYrExperience;
	}*/
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
	
	
	
	
	
	
	
	
}
