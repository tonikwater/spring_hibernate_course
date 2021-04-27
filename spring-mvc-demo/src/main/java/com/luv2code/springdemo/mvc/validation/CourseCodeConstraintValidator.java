package com.luv2code.springdemo.mvc.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements
//second param type defines on which datatype annotation can be used
// in this case 'String'
	ConstraintValidator<CourseCode, String>{

	private String[] coursePrefixes;
	
	@Override
	public void initialize(CourseCode theCourseCode) {
		coursePrefixes = theCourseCode.value();
	}
	
	@Override
	public boolean isValid(
			// this is the value which will be checked
			String arg0,
			// we can place additional error messages here
			ConstraintValidatorContext arg1)
	{
		
		// if there is a courseCode it has to start with the prefix
		if(arg0 != null) {
			for(String cp : coursePrefixes) {
				if(arg0.startsWith(cp)) {
					return true;
				}
			}
		}else {
			return true; // otherwise we dont care
		}
		return false;
	}

}
