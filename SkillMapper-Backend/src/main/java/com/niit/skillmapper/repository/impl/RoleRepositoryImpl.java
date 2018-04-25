package com.niit.skillmapper.repository.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.skillmapper.model.Employee;
import com.niit.skillmapper.model.Role;
import com.niit.skillmapper.repository.EmployeeRepository;
import com.niit.skillmapper.repository.RoleRepository;

@Repository
@Transactional
public class RoleRepositoryImpl implements RoleRepository {

	private static final Logger log = LoggerFactory.getLogger(RoleRepositoryImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private Role role;
	

	@Override
	public boolean addRole(Role role) {
		try{
			sessionFactory.getCurrentSession().save(role);	
			log.debug("role added successfully");

			return true;
		}
		catch(HibernateException exception)
		{
			log.error(exception.getMessage());
			return false;
		}
}

	@Override
	public boolean updateEmployeeRole(String roleName, int employeeId) {
		try{
			role.setRoleName(roleName);
			role.setEmployeeId(employeeId);
			sessionFactory.getCurrentSession().update(role);	
			log.debug("role added successfully");

			return true;
		}
		catch(HibernateException exception)
		{
			log.error(exception.getMessage());
			return false;
		}

	
	
	}

	@Override
	public Role getRole(int employeeId) {
		try{
			log.debug("Role data is going to fetch by employeeId");
			Role role=(Role)sessionFactory.getCurrentSession().createQuery("from Role where employeeId=:id").setParameter("id",employeeId).uniqueResult();
			return role;
		}
		catch(HibernateException exception)
		{
			log.error(exception.getMessage());
			return null;
		}
	
	}

	

		
}
