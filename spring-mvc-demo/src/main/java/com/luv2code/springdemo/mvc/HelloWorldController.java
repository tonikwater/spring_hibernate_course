package com.luv2code.springdemo.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hello")
public class HelloWorldController {

	// need a controller method to show the initial form
	
	@RequestMapping("/showForm")
	public String showForm() {
		return "helloworld-form";
	}
	
	// need a controller method to process the form
	
	@RequestMapping("/processForm")
	public String processForm() {
		return "helloworld";
	}
	
	// another form controller
	
	@RequestMapping("/processFormVersionTwo")
	public String letsShoutDude(HttpServletRequest req, Model model) {
		
		String theName = req.getParameter("studentName");
		theName = theName.toUpperCase();
		String result = "Yo! " + theName;
		model.addAttribute("message", result);
		return "helloworld";
	}
	
	@RequestMapping("/processFormVersionThree")
	public String processFormVersionThree(@RequestParam("studentName") String theName, Model model) {
		
		theName = theName.toUpperCase();
		String result = "Hey my Friend from v3! " + theName;
		model.addAttribute("message", result);
		return "helloworld";
	}
}
