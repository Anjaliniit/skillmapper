package com.niit.skillmapper.repository;

import java.util.List;

import com.niit.skillmapper.model.Employee;
import com.niit.skillmapper.model.Skill;

public interface SkillsRepository {
	
	boolean addSkills(Skill skill);
	List<Skill>getAllSkills();
	List<String>getAllSkill(String skillName);
	List<Employee>getAllEmployeesBySingleSkillName(String skillName);
	List<Employee>getAllEmployeesByMultipleSkillName(String skillName);
	boolean deleteSkill(int skillId);
	boolean updateSkill(Skill skill);
	Skill getSkillsBySkillId(int skillId);
	List<Skill> getAllSkillsByEmployeeId(int empId);
	


	
	
}
