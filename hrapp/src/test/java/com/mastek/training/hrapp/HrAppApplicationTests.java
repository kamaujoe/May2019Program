package com.mastek.training.hrapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mastek.training.hrapp.apis.EmployeeService;
import com.mastek.training.hrapp.entities.Employee;


//-> Initialized the JUnit Test with Spring Boot Application Environement
//-> Spring Boot is used to test Spring Application context with all the test cases identified

@RunWith(SpringRunner.class)
@SpringBootTest
public class HrAppApplicationTests {
	
	//-> declare "EmployeeService" class
	@Autowired //-> scan in memory all the components and provide the best match object in the component.
	EmployeeService empService;
	
	@Autowired
	Employee emp;
	
	
	@Test
	public void exampleProjectTest() {
		System.out.println("Employee Created");
	}
	
//	@Test
//	public void addEmployeeUsingService() {			
//		empService.registerEmployee(emp);
//	}
	
	
	@Test
	public void contextLoads() {
		System.out.println("System Test Executed");
	}
	
	
	//-> Database
	
	@Test
	public void addEmployeeUsingService() {
		emp.setEmpno(5); //->
		emp.setName("New Employee 5");
		emp.setSalary(4545);
		emp = empService.registerOrUpdateEmployee(emp);
		assertNotNull(emp);
		
	}
	
	
	//-> FIND
	@Test
	public void findByEmpnoUsingService() {
		int empno = 1;
		assertNotNull(empService.findByEmpno(empno));
	}
	
	
	//-> DELETE
	
	@Test
	public void deleteByEmpnoUsingService() {
		
		int empno = 2;
		empService.deleteByEmpno(empno);
		assertNull(empService.findByEmpno(empno));
	}
	
	@Test
	public void checkFetchBySalary() {
		List<Employee> emps = empService.fetchEmployeesBySalaryRange(0, 2325);
		
		for (Employee employee : emps) {
			System.err.println(employee);
		}
		
		assertEquals(emps.size(),8);
	}
	
	@Test
	public void simpleTest() {
		System.out.println("System Test Executed");
	}
	



}



