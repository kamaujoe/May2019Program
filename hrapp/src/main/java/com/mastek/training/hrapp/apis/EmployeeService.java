package com.mastek.training.hrapp.apis;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mastek.training.hrapp.entities.Employee;


@Component //-> indicate to spring to create an object of this class as component
@Scope("singleton")//-> @scope: singleton: one object for application [default], prototype: one object copy for each usage
public class EmployeeService {
	
	
	//-> create an EmployeeService object
	public EmployeeService() {
		System.out.println("Employee Service Created");
	}
	

	//-> created registerEmployee method 
	public  Employee registerEmployee(Employee emp) {
		
		System.out.println("Employee Registered"+emp);
		return emp;
		
		
	}
	

}
