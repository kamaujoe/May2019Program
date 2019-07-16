package com.mastek.training.hrapp.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")//-> one copy for each test case
@Entity //-> declares the class as an Entity
@Table(name="JPA_EMPLOYEE") //-> declaring the table name for the class
@EntityListeners({EmployeeLifecycleListener.class})
@NamedQueries({
	
	@NamedQuery(name ="Employee.findBySalary",
			query = "select e from Employee e where e.salary between :min and :max")
})
public class Employee implements Serializable {//->Manage serialization of Objects
	
	@Value("-1")
	private int empno;
	
	@Value("Default Employee")
	private String name;
	
	@Value("100.0")
	private double salary;
	
	
	//-> ManyToMnay Associations
	private Set<Project> assignments = new HashSet<>();
	
	
	//-> GETTERS AND SETTERS for ManyToMany
	//-> @ManyToMany: configure the association for both the entities
	//-> @JoinTable: provides all the configuration for the third table
	//-> name: name of he Join Table
	//-> joinColumns: Foreign Key column name for the current class 
	//-> inverseJoinColumns: Foreign key column for other class
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name="JPA_ASSIGNMENTS", joinColumns=@JoinColumn(name="FK_EMPNO"), inverseJoinColumns=@JoinColumn(name="FK_PROJECTID"))
	public Set<Project> getAssignments() {
		return assignments;
	}

	public void setAssignments(Set<Project> assignments) {
		this.assignments = assignments;
	}



	//-> Mapping ManyToOne: Each Employee belongs to one department
	private Department currentDepartment;

	//-> GETTERS AND SETTERS  for ManyToOne
	
	//-> @ManyToOne: associating the many class to one object
	//-> @JoinColumn: configure the ForeignKey column for the association between two entities
	@ManyToOne
	@JoinColumn(name="FK_DepartmentId")
	public Department getCurrentDepartment() {
		return currentDepartment;
	}

	public void setCurrentDepartment(Department currentDepartment) {
		this.currentDepartment = currentDepartment;
	}
	
	
	//-> add default constructor for this class. (must have a default constructor for Spring as well as getters and setters)
	





	public Employee() {
		System.out.println("Employee Created");
	}
	
	
	
	
	//-> GENERATE GETTERS and SETTERS for EMPLOYEE CLASS
	
	@Id //-> declare the property as primary Key
	@Column(name="employee_number")//-> declare the name of the column.
	@GeneratedValue(strategy=GenerationType.AUTO)//-> used for Auto-Numbering the primary key
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	
	@Column(name="employee_name", nullable=false, length=45)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}



	//-> Generate toString to print Employee object informating instead of its memory location
	@Override
	public String toString() {
		return "Employee [empno=" + empno + ", name=" + name + ", salary=" + salary + "]";
	}
	
	
	
	

}
