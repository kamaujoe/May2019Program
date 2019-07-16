package com.mastek.training.hrapp.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
@Table(name="JPA_DEPARTMENT")
@NamedQueries({
	@NamedQuery (name = "Department.findByLocation",
				query = "select d from Department d where d.location = :location")
 
})
public class Department implements Serializable {
		
		
		@Value("-1")
		private int deptno;
		
		@Value("Default Name")
		private String name;
		
		@Value("Default Location")
		private String location;
		
		
		//-> default constructor
		public Department() {
			System.out.println("Department created");
			
		}
		
		//-> OneToMany: one department has many Employees
		private Set<Employee> members = new HashSet<>();
		
		
		
		//-> GETTERS AND SETTERS FOR OneToMany <-\\
		
		//->@OneToMany: used on the get method of set to configure associations
				//-> fetch=LAZY: delay the initialization until method getmembers() is called using additional select query [default value]
					  //->EAGER: Initialize the collection on the entity find Post load event
				//-> cascade=ALL: the Enitity operation done on Department would be performed on each associated employee object
						//-> ALL: [Persist, Merge, Delete, Refresh]
		//-> mappedBy: identifies the propertyname in child class where the JoinColumn configuration is present, JoinColumn :: ForeignKey
		@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="currentDepartment")
		public Set<Employee> getMembers() {
			return members;
		}

		public void setMembers(Set<Employee> members) {
			this.members = members;
		}




		@Id
		@Column(name = "department_number")
		@GeneratedValue(strategy=GenerationType.AUTO)
		public int getDeptno() {
			return deptno;
		}


		public void setDeptno(int deptno) {
			this.deptno = deptno;
		}


		@Column(name = "department_name", nullable=false, length=25)
		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		@Column(name = "location", nullable=false, length=25)
		public String getLocation() {
			return location;
		}


		public void setLocation(String location) {
			this.location = location;
		}




		//-> toString method
		@Override
		public String toString() {
			return "Department [deptno=" + deptno + ", name=" + name + ", location=" + location + "]";
		}

		
		


		
		

	
}
