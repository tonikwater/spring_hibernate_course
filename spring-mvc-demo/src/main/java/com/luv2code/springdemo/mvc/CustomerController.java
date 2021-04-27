package com.luv2code.springdemo.mvc;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	// add an initbinder to trim Strings
	// to resolve validation issue with customers name
	// this method will be called for every web req comming to this controller
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		System.out.println("* init binder *");
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	

	@RequestMapping("/showForm")
	public String showForm(Model theModel) {
		
		System.out.println("/showForm");
		
		theModel.addAttribute("customer", new Customer());
		
		return "customer-form";
	}
	
	
	@RequestMapping("/processForm")
	public String processForm(
			// @Valid actually activates the Validation we specified
			// in the Customer class
			@Valid
			@ModelAttribute("customer") Customer theCustomer,
			// the result of the validation is placed inside here,
			// this param has to follow the Validation Object
			BindingResult theBindingResult) {
		
		System.out.println("/processForm");
		
		System.out.println("last name: [" +
		theCustomer.getLastName() + "]");
		
		System.out.println("Binding result: " + theBindingResult + "\n\n");
		
		if(theBindingResult.hasErrors()) {
			return "customer-form";
		}
		return "customer-confirmation";
	}
}
