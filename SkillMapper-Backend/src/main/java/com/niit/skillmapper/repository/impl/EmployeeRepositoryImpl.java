package com.niit.skillmapper.repository.impl;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.skillmapper.model.Employee;
import com.niit.skillmapper.model.Role;
import com.niit.skillmapper.repository.EmployeeRepository;


@Repository
@Transactional
public class EmployeeRepositoryImpl implements EmployeeRepository {

	private static final Logger log = LoggerFactory.getLogger(EmployeeRepositoryImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean addEmployee(Employee employee) {

		try{
			sessionFactory.getCurrentSession().save(employee);	
			log.debug("employee added successfully");
			return true;
		}
		catch(HibernateException exception)
		{
			log.error(exception.getMessage());
			return false;
		}
	}

	public boolean updateEmployee(Employee employee) {

		try{
			sessionFactory.getCurrentSession().update(employee);	
			log.debug("employee updated successfully");	
			return true;
		}
		catch(HibernateException exception)
		{
			log.error(exception.getMessage());
			return false;
		}

	}

	public boolean deleteEmployee(int employeeId) {

		try{
			sessionFactory.getCurrentSession().delete(getEmployeeByEmployeeId(employeeId));	
			log.debug("employee deleted successfully");	
			return true;
		}
		catch(HibernateException exception)
		{
			log.error(exception.getMessage());
			return false;
		}
	}

	public List<Employee> getAllEmployees() {
		try{
			log.debug("Employee data is going to fetch");
			List<Employee> employeeList=sessionFactory.getCurrentSession().createQuery("from Employee").list();
			return employeeList;
		}
		catch(HibernateException exception)
		{
			log.error(exception.getMessage());
			return null;
		}

	}

	public Employee getEmployeeByEmployeeId(int employeeId) {

		try{
			log.debug("Employee data is going to fetch by managerId");
			Employee employee=(Employee)sessionFactory.getCurrentSession().createQuery("from Employee where employeeId=:id").setParameter("id",employeeId).uniqueResult();
			if(employee!=null)
			return employee;
			else
				return null;
		}
		catch(HibernateException exception)
		{
		
			log.error(exception.getMessage());
			return null;
		}
	}

	public List<Employee> getAllEmployeesByManagerId(int managerId) {
		try{
			log.debug("Employee data is going to fetch by managerId");
			List<Employee> employeeList=(List<Employee>)sessionFactory.getCurrentSession().createQuery("from Employee where managerId=:id").setParameter("id",managerId).getResultList();
			return employeeList;
		}
		catch(HibernateException exception)
		{
			
			log.error(exception.getMessage());
			return null;
		}





	}

	public boolean validate(int employeeId, String password) {

		try{

			Query<Employee> query=sessionFactory.getCurrentSession().createQuery("from Employee where employeeId=:empId").setParameter("empId",employeeId);
			Employee user=(Employee)query.uniqueResult();
			if (user!=null)
			{   if(user.getPassword().equals(password))
				return true;
			else return false;
			}
			else
				return false;

		}
		catch(HibernateException exception)
		{
			log.error(exception.getMessage());
			return false;
		}
	}

	public boolean exists(int employeeId) {

		try{

			Query<Employee> query=sessionFactory.getCurrentSession().createQuery("from Employee where employeeId=:empId").setParameter("empId",employeeId);
			Employee user=(Employee)query.uniqueResult();
			if (user!=null)  
				return true;
			else
				return false;

		}
		catch(HibernateException exception)
		{
			log.error("no employees exists with employeeid"+employeeId+exception.getMessage());
			return false;
		}		
	}

	public boolean approveEmployee(int employeeId) {

		try{

			Session session =sessionFactory.getCurrentSession();
			Employee employee=(Employee)session.createQuery("from Employee where employeeId=:id").setParameter("id",employeeId).uniqueResult();
			employee.setStatus("active");
			session.update(session);
			return true;		
		}
		catch(HibernateException exception)
		{
			log.error("No Employee exits with this id"+employeeId+exception.getMessage());
			return false;
		}		


	}

	@Override
	public List<Employee> getAllEmployeesByLocation(String location) {
		try{
			log.debug("Employee data is going to fetch by location");
			List<Employee> employeeList=(List<Employee>)sessionFactory.getCurrentSession().createQuery("from Employee where location=:location").setParameter("location",location).getResultList();
			return employeeList;
		}
		catch(HibernateException exception)
		{
			
			log.error(exception.getMessage());
			return null;
		}

	}

	@Override
	public List<Employee> getAllEmployeesByBusinessUnit(String businessUnit) {
		try{
			log.debug("Employee data is going to fetch by businessUnit");
			List<Employee> employeeList=(List<Employee>)sessionFactory.getCurrentSession().createQuery("from Employee where businessUnit=:businessUnit").setParameter("businessUnit",businessUnit).getResultList();
			return employeeList;
		}
		catch(HibernateException exception)
		{
			
			log.error(exception.getMessage());
			return null;
		}
	}

	@Override
	public List<Employee> getAllEmployeesBySupervisor(String supervisorName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> getAllEmployeesByRegion(String region) {
		try{
			log.debug("Employee data is going to fetch by region");
			List<Employee> employeeList=(List<Employee>)sessionFactory.getCurrentSession().createQuery("from Employee where region=:region").setParameter("region",region).getResultList();
			return employeeList;
		}
		catch(HibernateException exception)
		{
			
			log.error(exception.getMessage());
			return null;
		}
	}

}
