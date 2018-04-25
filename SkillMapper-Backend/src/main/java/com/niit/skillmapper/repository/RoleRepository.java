package com.niit.skillmapper.repository;

import java.util.List;

import com.niit.skillmapper.model.Role;

public interface RoleRepository {

	boolean addRole(Role role);
	boolean updateEmployeeRole(String roleName,int employeeId );
	Role getRole(int employeeId);

	
	
	
	
}
