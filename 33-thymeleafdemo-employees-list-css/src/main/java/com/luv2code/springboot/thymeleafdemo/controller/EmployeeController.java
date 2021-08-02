package com.luv2code.springboot.thymeleafdemo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luv2code.springboot.thymeleafdemo.model.Employee;

@Controller()
@RequestMapping("/employees")
public class EmployeeController {

	private List<Employee> theEmployees;
	
	@PostConstruct
	private void loadData() {
		Employee e1 = new Employee(1, "Leslie", "Andrews", "leslie@luv2code.com");
		Employee e2 = new Employee(1, "Emma", "Baumgarten", "leslie@luv2code.com");
		Employee e3 = new Employee(1, "Avani", "Gupta", "leslie@luv2code.com");

		theEmployees = new ArrayList<>();
		
		theEmployees.add(e1);
		theEmployees.add(e2);
		theEmployees.add(e3);
	}
	
	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		
		theModel.addAttribute("theEmployees", theEmployees);
		
		return "list-employees";
	}
}
