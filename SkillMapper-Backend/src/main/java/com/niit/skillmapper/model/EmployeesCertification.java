package com.niit.skillmapper.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class EmployeesCertification {

	@Id
	private int id;
	private int employeeId;
	private int certificationId;
	
	public EmployeesCertification(){}
	
	public EmployeesCertification(int employeeId,int certificationId)
	{	
		this.employeeId=employeeId;
		this.certificationId=certificationId;
	}
	
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
	public int getCertificationId() {
		return certificationId;
	}
	public void setCertificationId(int certificationId) {
		this.certificationId = certificationId;
	}

	
}
