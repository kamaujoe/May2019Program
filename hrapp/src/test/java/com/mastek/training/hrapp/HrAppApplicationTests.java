package com.mastek.training.hrapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mastek.training.hrapp.apis.DepartmentService;
import com.mastek.training.hrapp.apis.EmployeeService;
import com.mastek.training.hrapp.apis.ProjectService;
import com.mastek.training.hrapp.entities.Department;
import com.mastek.training.hrapp.entities.Employee;
import com.mastek.training.hrapp.entities.Project;


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
	
	@Autowired
	DepartmentService deptService;
	
	@Autowired
	Department dept;
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	Project project;
	
	
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
	

//-> DEPARTMENT TESTS
	

	
	//-> ADD
	@Test
	public void addDepartmentUsingService() {
		dept.setDeptno(34); //->
		dept.setName("New department 4");
		dept.setLocation("Leeds 5");
		
		dept = deptService.registerOrUpdateDepartment(dept);
		assertNotNull(dept);
	}
	
	//-> FIND
	@Test
	public void findByDeptnoUsingService() {
		int deptno = 1;
		assertNotNull(deptService.findBydeptno(deptno));
	}
	
	//-> DELETE
	@Test
	public void deleteByDeptnoUsingService() {
		
		int deptno = 2;
		deptService.deleteBydeptno(deptno);
		assertNull(deptService.findBydeptno(deptno));
	}
	
	@Test
	public void checkFetchByLocation() {
		List<Department> dept = deptService.findByLocation("Leeds");
		for (Department department : dept) {
			System.out.println(department);
		}
		assertEquals(dept.size(), 4);
	}
	//-> CUSTOM QUERY
	
	
	
	
	
	
//-> PROJECT TESTS
	
	//-> ADD
	@Test
	public void addProjectUsingService() {
		project.setProjectID(24); //->
		project.setName("MIB");
		project.setCustomerName("John");
		
		dept = deptService.registerOrUpdateDepartment(dept);
		assertNotNull(dept);
	}
	
	//-> FIND
	@Test
	public void findByProjectIDUsingService() {
		int projectID = 1;
		assertNotNull(projectService.findByprojectID(projectID));
	}
	
	//-> DELETE
	@Test
	public void deleteByProjectIDUsingService() {
		
		int projectID = 2;
		projectService.deleteByprojectID(projectID);
		assertNull(projectService.findByprojectID(projectID));
	}
	
	@Test
	public void checkFetchByCustomer() {
		List<Project> proj = projectService.findByCustomer("John");
		for (Project project : proj) {
			System.out.println(project);
		}
		
	}
	
	
	
	
//-> ASSOCIATIONS TEST CASES
	
	@Test
	public void manageAssociations() {
		Department d1 = new Department();
		d1.setName("Admin");
		d1.setLocation("UK");
		
		Employee emp1 = new Employee();
		emp1.setName("Admin Emp 1");
		emp1.setSalary(3433);
		
		Employee emp2 = new Employee();
		emp2.setName("Admin Emp 2");
		emp2.setSalary(34456);
		
		Project p1 = new Project();
		p1.setName("UK Project");
		p1.setCustomerName("UK Customer");
		
		Project p2 = new Project();
		p2.setName("USA Project");
		p2.setCustomerName("USA Customer");
		
		//-> OneToMany: one department has many Employees
		d1.getMembers().add(emp1);
		d1.getMembers().add(emp2);
		
		//-> ManyToOne for each employee to assign the department
		emp1.setCurrentDepartment(d1);
		emp2.setCurrentDepartment(d1);
		
		
		//-> ManyToMany
		emp1.getAssignments().add(p1);
		emp1.getAssignments().add(p2);
		emp2.getAssignments().add(p1);
		
		deptService.registerOrUpdateDepartment(d1);
		
		
	}
	
	
	
	
	
	


}



