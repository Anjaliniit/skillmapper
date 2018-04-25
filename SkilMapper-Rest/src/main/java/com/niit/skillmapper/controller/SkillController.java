package com.niit.skillmapper.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.skillmapper.model.Employee;
import com.niit.skillmapper.model.Skill;
import com.niit.skillmapper.repository.EmployeeRepository;
import com.niit.skillmapper.repository.SkillsRepository;

@RestController
@RequestMapping("/api/skill")
public class SkillController {

	@Autowired
	SkillsRepository skillsRepository;
	@Autowired
	EmployeeRepository employeeRepository;

	@PostMapping("/addskill")
	public ResponseEntity<Object>addSkill(@RequestBody Skill skill,HttpSession session)
	{
		Employee employee=(Employee)session.getAttribute("loggedInUser");
		if(session.getAttribute("loggedInUser")!=null)
		{
			skill.setEmployeeId(employee.getEmployeeId());
			if(skillsRepository.addSkills(skill))
				return new ResponseEntity<Object>(skill,HttpStatus.CREATED);
			else
				return new ResponseEntity<Object>("Skill not added",HttpStatus.NO_CONTENT);
		}
		else
			return new ResponseEntity<Object>("Please Login first",HttpStatus.UNAUTHORIZED); 

	}


	@PostMapping(value="/update")
	public ResponseEntity<Object> updateSkill(@RequestBody Skill skill,HttpSession session)
	{
		if(session.getAttribute("loggedInUser")!=null)
		{

			if(skillsRepository.updateSkill(skill))
			{
				return new ResponseEntity<Object>(skill,HttpStatus.CREATED);
			}
			else
			{
				return new ResponseEntity<Object>(skill,HttpStatus.NOT_IMPLEMENTED);
			}
		}
		else
			return new ResponseEntity<Object>("Please Login first",HttpStatus.UNAUTHORIZED); 

	}

	@GetMapping("/delete/{skillId}")
	public ResponseEntity<Object> deleteSkill(@PathVariable("skillId") int skillId,HttpSession session)
	{
		if(session.getAttribute("loggedInUser")!=null)
		{
			if(skillsRepository.deleteSkill(skillId))
			{
				return new ResponseEntity<Object>("Skill is deleted",HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
		}
		else
			return new ResponseEntity<Object>("Please Login first",HttpStatus.UNAUTHORIZED); 
	}



	@GetMapping
	public ResponseEntity<Object> getAllSkills(HttpSession session)
	{
		if(session.getAttribute("loggedInUser")!=null)
		{
			List<Skill> allSkills=skillsRepository.getAllSkills();
			if(allSkills!=null)
			{
				return new ResponseEntity<Object>(allSkills,HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<Object>("No content",HttpStatus.NO_CONTENT);
			}
		}
		else
		{
			return new ResponseEntity<Object>("Please Login first",HttpStatus.UNAUTHORIZED);
		}
	}


	@GetMapping(value="/allEmployeesBySingle/{skillName}")
	public ResponseEntity<List<Employee>> getAllEmployeesBySingleSkillName(@PathVariable("skillName") String skillName,HttpSession session)
	{
		if(session.getAttribute("loggedInUser")!=null)
		{
			List<Employee> allEmployees=skillsRepository.getAllEmployeesBySingleSkillName(skillName);
			if(allEmployees!=null)
			{
				return new ResponseEntity<List<Employee>>(allEmployees,HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
			}
		}
		else
		{
			return new ResponseEntity<List<Employee>>(HttpStatus.UNAUTHORIZED);
		}
	}



	@GetMapping("/allEmployeeByMultiple/{skillnames}")
	public ResponseEntity<List<Employee>> getAllEmployeesByMultipleSkills(@PathVariable("skillnames") String skillnames,HttpSession session)
	{
		if(session.getAttribute("loggedInUser")!=null)
		{
			List<Employee> allEmployees=skillsRepository.getAllEmployeesByMultipleSkillName(skillnames);
			if(allEmployees!=null)
			{
				return new ResponseEntity<List<Employee>>(allEmployees,HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
			}
		}
		else
		{
			return new ResponseEntity<List<Employee>>(HttpStatus.UNAUTHORIZED);
		}
	}


	@GetMapping("/{skillId}")
	public ResponseEntity<Skill> getSkillById(@PathVariable("skillId") int skillId,HttpSession session)
	{
		if(session.getAttribute("loggedInUser")!=null)
		{
		Skill skill=skillsRepository.getSkillsBySkillId(skillId);
		if(skill!=null)
		{
			return new ResponseEntity<Skill>(skill,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Skill>(skill,HttpStatus.NOT_FOUND);
		}
		}
		else
		{             
			return new ResponseEntity<Skill>(HttpStatus.UNAUTHORIZED);
		}
		
	}


	@GetMapping("/skills/{employeeId}")
	public ResponseEntity<Object> getAllSkillsByEmployeeId(@PathVariable("employeeId") int employeeId,HttpSession session)
	{
		if(session.getAttribute("loggedInUser")!=null)
		{
		List<Skill> allSkills=skillsRepository.getAllSkillsByEmployeeId(employeeId);
		if(allSkills!=null)
		{
			return new ResponseEntity<Object>(allSkills,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}
		}
		else
		{
			return new ResponseEntity<Object>("Please login first",HttpStatus.UNAUTHORIZED);
		}
		
	}
















}
