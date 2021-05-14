package com.luv2code.aopdemo.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;
import com.luv2code.aopdemo.AroundWithLoggerDemoApp;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
	
	private static Logger myLogger = Logger.getLogger(MyDemoLoggingAspect.class.getName());

	// this is where we add all of our related advices for logging
	
	@Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
	public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable{
		
		String method = theProceedingJoinPoint.getSignature().toShortString();
		myLogger.info("===>>> Executing @Around on method: " + method);
		
		long begin = System.currentTimeMillis();
		Object result = theProceedingJoinPoint.proceed(); // call method
		// when method returns the code will continue here ...
		long end = System.currentTimeMillis();
		
		long dur = end - begin;
		
		myLogger.info("===>>> Duration: " + dur / 1000.0 + " seconds.");
		
		return result;
	}

	@Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
		myLogger.info("===>>> Executing @Before advice on methods matching the"
				+ " specified pattern");
		
		// display method signature
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
		myLogger.info("Method: " + methodSig);
		
		// display method args
		myLogger.info("Params:");
		Object[] args = theJoinPoint.getArgs();
		for(Object arg : args) {
			myLogger.info(arg.toString());
		}
	}
	
	// @After work like the finally block in "try-catch"
	@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("===>>> Executing @After (finally) on method: " + method);
	}
	
	@AfterThrowing(
			pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing="theExc")
	public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc) {
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("===>>> Executing @AfterThrowing on method: " + method);
		myLogger.info("===>>> Exception is: " + theExc);
	}
	
	@AfterReturning(
		pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
		returning="result") // param name for ret value of method matching
	public void afterReturningFindAccountsAdvice(
			JoinPoint theJoinPoint,
			// here is the param with the above "returning" value as the name
			List<Account> result) { 
		
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("===>>> Executing @AfterReturning on method: " + method);
			
		myLogger.info("===>>> Result is: " + result +
				"\nat: " + System.identityHashCode(result));
		
		// lets postprocess the return value (modify it)
		convertAccountNamesToUpperCase(result);
		
		myLogger.info("===>>> Results is: " + result +
				"\nat: " + System.identityHashCode(result));
	}

	private void convertAccountNamesToUpperCase(List<Account> result) {
		for(Account acc : result) {
			acc.setName(acc.getName().toUpperCase());
		}
	}
}
