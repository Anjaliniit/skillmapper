package com.niit.skillmapper.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.skillmapper.model.Employee;
import com.niit.skillmapper.model.Skill;
import com.niit.skillmapper.repository.EmployeeRepository;
import com.niit.skillmapper.repository.SkillsRepository;

@Repository
@Transactional
public class SkillsRepositoryImpl implements SkillsRepository {

	private static final Logger log = LoggerFactory.getLogger(SkillsRepositoryImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public boolean addSkills(Skill skills) {
		try{
			List<Skill> list=new ArrayList<>();
			String skillNames[]=skills.getSkillName().split(",");
			for(String skill:skillNames)
			{
				if(skill!=null)
				{
					String skillname[]=skill.trim().split(" ");
					for(String s:skillname)
					{
						if(!(s.equals("and")||s.equals("or")||s.equals("the")))		
						{
							Skill newSkill=new Skill(); 
							newSkill.setSkillName(s);
							newSkill.setEmployeeId(skills.getEmployeeId());
							newSkill.setNoOfStudentTaught(skills.getNoOfStudentTaught());
							newSkill.setNoOfYrExperience(skills.getNoOfYrExperience());
							newSkill.setRating(skills.getRating());
							list.add(newSkill);
						}
					}
				}
			}
			for(Skill newskill :list)
			sessionFactory.getCurrentSession().save(newskill);


			log.debug("skill added successfully");
			return true;
		}
		catch(HibernateException exception)
		{
			log.error(exception.getMessage());
			return false;
		}	
	}

	@Override
	public List<String> getAllSkill(String skills) {
		try{
			log.debug("get all skill by skill name");
			List<String> allSkills=new ArrayList<>();
			String[] allStrings=skills.split(",");
			for(String s:allStrings)
			{
				String[] str=s.split(" ");
				if(str!=null)
				{
					for(String ss:str)
					{
						if(!(ss.equals("and")||ss.equals("or")||ss.equals("the")))
						{
							allSkills.add(ss.trim());
						}
					}
				}
			}
			return allSkills;
		}
		catch(HibernateException exception)
		{
			log.error(exception.getMessage());
			return null;
		}	
	}

	@Override
	public List<Skill> getAllSkills() {
		try{
			log.debug("get all skill");
			return sessionFactory.getCurrentSession().createQuery("From Skill").list();
		}
		catch(HibernateException exception)
		{
			log.error(exception.getMessage());
			return null;
		}	

	}


	@Override
	public List<Employee> getAllEmployeesBySingleSkillName(String skillName) {

		try{
			Query query = sessionFactory.getCurrentSession().createQuery("From Skill where skillName=:skillName").setParameter("skillName",skillName);
			List<Skill> list = query.list();
			List<Employee> employeeList = new ArrayList<>();
			Employee emp = new Employee();
			for(Skill skill : list)
			{
				emp = employeeRepository.getEmployeeByEmployeeId(skill.getEmployeeId());
				employeeList.add(emp);
			}
			return employeeList;
		}
		catch(HibernateException exception)
		{
			log.error(exception.getMessage());
			return null;
		}
	}


	@Override
	public List<Employee> getAllEmployeesByMultipleSkillName(String skillName) {
		List<String> skillNames=getAllSkill(skillName);
		try{
			Query query = sessionFactory.getCurrentSession().createQuery("From Skill where skillName in (:skillnames)");
			query.setParameterList("skillnames", skillNames);
			List<Skill> list = query.list();
			List<Employee> empList = new ArrayList<>();
			Employee emp = new Employee();
			for(Skill skill : list)
			{
				emp = employeeRepository.getEmployeeByEmployeeId(skill.getEmployeeId());
				if(!(empList.isEmpty()))
				{
					for(Employee e:empList)
					{
						if(e.getEmployeeId()!=emp.getEmployeeId())
						{
							empList.add(emp);
						}
					}
				}
				else
				{
					empList.add(emp);
				}
			}
			return empList;
		}
		catch(HibernateException exception)
		{
			log.error(exception.getMessage());
			return null;
		}

	}

	@Override
	public boolean deleteSkill(int skillId) {
		try
		{
			sessionFactory.getCurrentSession().delete(getSkillsBySkillId(skillId));
			return true;
		}
		catch(HibernateException exception)
		{
			log.error(exception.getMessage());
			return false;
		}
	}

	@Override
	public boolean updateSkill(Skill skill) {
		try
		{
			sessionFactory.getCurrentSession().update(skill);
			return true;
		}
		catch(HibernateException exception)
		{
			log.error(exception.getMessage());
			return false;
		}
	}

	@Override
	public Skill getSkillsBySkillId(int skillId) {
		try
		{
			return sessionFactory.getCurrentSession().get(Skill.class, skillId);
		}
		catch(HibernateException exception)
		{
			log.error(exception.getMessage());
			return null;
		}
	}

	@Override
	public List<Skill> getAllSkillsByEmployeeId(int empId) {
		try{
			Query query = sessionFactory.getCurrentSession().createQuery("From Skill where employeeId=?");
			query.setInteger(0, empId);
			List<Skill> skills = query.list();
			return skills;
		}
		catch(HibernateException exception)
		{
			log.error(exception.getMessage());
			return null;
		}
	}

}


