package com.mastek.training.hrapp.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="JPA_Project")
@NamedQueries({
	@NamedQuery (name = "Project.findByCustomer",
				query = "select c from Project c where c.customerName = :customer")
 
})
public class Project implements Serializable {

	
	@Value("-1")
	private int projectID;
	
	@Value("Default Name")
	private String name;
	
	@Value("Default Location")
	private String customerName;
	
	//-> default constructor
	public Project() {
		System.out.println("Project created");
	}
	
	//-> ManyToMany
	private Set<Employee> team=new HashSet<>();
	
	

	//-> GETTERS AND SETTERS for ManyToMany
	
	//-> mappedBy: check the configuration for Many to Many association in Employee class getAssignemnets() method
	@ManyToMany(mappedBy="assignments")
	public Set<Employee> getTeam() {
		return team;
	}

	public void setTeam(Set<Employee> team) {
		this.team = team;
	}

	@Id
	@Column(name ="project_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	@Column(name = "project_name", nullable=false, length=25)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "customer_name", nullable=false, length=25)
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	@Override
	public String toString() {
		return "Project [projectID=" + projectID + ", name=" + name + ", customerName=" + customerName + "]";
	}


	
	
	
}
