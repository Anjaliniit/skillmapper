package com.niit.skillmapper;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class EmployeeController {
	
	@GetMapping("/")
	public void home()
	{
		return "index";
	}
	
	
}
