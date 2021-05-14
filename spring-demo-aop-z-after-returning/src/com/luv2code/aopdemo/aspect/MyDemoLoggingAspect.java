package com.luv2code.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

	// this is where we add all of our related advices for logging

	@Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
		System.out.println("===>>> Executing @Before advice on methods matching the"
				+ " specified pattern");
		
		// display method signature
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
		System.out.println("Method: " + methodSig);
		
		// display method args
		Object[] args = theJoinPoint.getArgs();
		for(Object arg : args) {
			System.out.println(arg);
		}
	}
	
	@AfterReturning(
		pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
		returning="result") // param name for ret value of method matching
	public void afterReturningFindAccountsAdvice(
			JoinPoint theJoinPoint,
			// here is the param with the above "returning" value as the name
			List<Account> result) { 
		
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("===>>> Executing @AfterReturning on method: " + method);
			
		System.out.println("===>>> Result is: " + result +
				"\nat: " + System.identityHashCode(result));
		
		// lets postprocess the return value (modify it)
		convertAccountNamesToUpperCase(result);
		
		System.out.println("===>>> Results is: " + result +
				"\nat: " + System.identityHashCode(result));
	}

	private void convertAccountNamesToUpperCase(List<Account> result) {
		for(Account acc : result) {
			acc.setName(acc.getName().toUpperCase());
		}
	}
}
