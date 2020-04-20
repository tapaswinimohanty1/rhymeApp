package com.sc.advice;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CentralizedPointCutAspect {
	@Pointcut("bean(*Service) || bean(*Controller)")
	private void allServices(){
		
	}
	
	@Pointcut("bean(*Controller)")
	private void controllers(){
		
	}
	
	@Pointcut("within(com.sc.service.*)")
	private void packagesToInclude() {
	}
	
	@Pointcut("within(com.sc.controller..*)")
	private void controllerPackages() {
	}
	
	@Pointcut("(packagesToInclude() || controllerPackages()) && (allServices() || controllers()) ")
	public void serviceClassesToLog() {
	}
}