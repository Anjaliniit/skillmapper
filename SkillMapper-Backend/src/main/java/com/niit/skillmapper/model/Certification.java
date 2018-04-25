package com.niit.skillmapper.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Entity
@Component
public class Certification implements Serializable{
	
	
	@Id
	private int certificationId;
	@NotEmpty
	private String certificationName;
	@NotEmpty
	private String technology;
	private int employeeId;

	//getter-setter
	
	public int getCertificationId() {
		return certificationId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public void setCertificationId(int certificationId) {
		this.certificationId = certificationId;
	}

	public String getCertificationName() {
		return certificationName;
	}

	public void setCertificationName(String certificationName) {
		this.certificationName = certificationName;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}
	
	

}
