package com.niit.skillmapper.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.skillmapper.model.Certification;
import com.niit.skillmapper.model.Employee;
import com.niit.skillmapper.repository.CertificationRepository;

@RestController
public class CertificationController {

	private CertificationRepository certificationRepository;



	@PostMapping("/api/certification")
	public ResponseEntity<Object>addCertification(@RequestBody Certification certification,HttpSession session)
	{
		Employee emp=(Employee)session.getAttribute("loggedInUser");
		if(emp!=null)
		{
			certification.setEmployeeId(emp.getEmployeeId());
			certificationRepository.addCertification(certification);
			return new ResponseEntity<Object>(certification , HttpStatus.OK);
		}
		else
			return new ResponseEntity<Object>("Please Login first",HttpStatus.UNAUTHORIZED);

	}

	@GetMapping("/api/certification")
	public ResponseEntity<Object>getAllCertification(HttpSession session)
	{
		if(session.getAttribute("loggedInUser")!=null)
		{
			List<Certification> certificationList = certificationRepository.getAllCertification();
			return new ResponseEntity<Object>(certificationList,HttpStatus.OK);
		}
		else
			return new ResponseEntity<Object>("Please Login first",HttpStatus.UNAUTHORIZED); 
	}


	@GetMapping("/api/certification/{employeeId}")
	public ResponseEntity<Object> getCertificationByEmployeeId(@PathVariable("employeeId") int employeeId,HttpSession session){
		if(session.getAttribute("loggedInUser")!=null)
		{
			List<Certification> certificationList = certificationRepository.getCertificationByEmployeeId(employeeId);
			return new ResponseEntity<Object>(certificationList,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Object>("You R Not Authorized, Please login first",HttpStatus.UNAUTHORIZED);
		}
	}


	@PutMapping("/api/certification/{employeeId}")
	public ResponseEntity<Object>updateCertification(@RequestBody Certification certification,@PathVariable("employeeId") int employeeId,HttpSession session)
	{
		if(session.getAttribute("loggedInUser")!=null)
		{
			if(certificationRepository.getCertificationByEmployeeId(employeeId)!=null)
			{
				certificationRepository.updateCertification(certification);		
				return new ResponseEntity<Object>(certification , HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<Object>("Employee does not exists", HttpStatus.NOT_FOUND);
			}
		}
		else
		{
			return new ResponseEntity<Object>("You are Not Authorized, Please login first" , HttpStatus.UNAUTHORIZED);
		}
	}		





}
