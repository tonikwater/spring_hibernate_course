package com.luv2code.springdemo.mvc.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

// we reference the actual class implementing the validation
@Constraint(validatedBy = CourseCodeConstraintValidator.class)
// we specify that our annotation can be used on METHODs and FIELDs
@Target({ElementType.METHOD, ElementType.FIELD})
// we want to process our annotation at runtime
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseCode {

	// define annotation attributes:
	
	// define default course code
	public String[] value() default {"GANG", "LUV"};
	
	// define default error message
	public String message() default "must start with GANG or LUV";
	
	// define default groups
	// idk this is for grouping with other constraints
	public Class<?>[] groups() default {};
	
	// define default payload
	// can be used to provide additional information about the error message
	public Class<? extends Payload>[] payload() default {};
}
