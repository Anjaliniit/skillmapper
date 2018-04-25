package com.niit.skillmapper.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.stereotype.Component;


@Entity
@Component
public class Skill implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int skillId;
	private String skillName;
	private int employeeId;
	private int noOfStudentTaught;
	private int noOfYrExperience;
	private int rating;
	
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getNoOfStudentTaught() {
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
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getSkillId() {
		return skillId;
	}
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	
}
