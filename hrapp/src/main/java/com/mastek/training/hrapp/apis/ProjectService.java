package com.mastek.training.hrapp.apis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import com.mastek.training.hrapp.entities.Project;

import com.mastek.training.hrapp.repositories.ProjectRepository;

@Component
@Scope("singleton")
public class ProjectService {
	
	public ProjectService() {
		System.out.println("Project service created");
	}
	
	
	//-> DataBase
	
	@Autowired
	private ProjectRepository  projectRepository;
	
	public Project registerOrUpdateProject(Project proj) {
		
		proj = projectRepository.save(proj);
		System.err.println("Project Registered"+proj);
		return proj;	
	}
	
	public Project findByprojectID(int projectID) {
		
		try {
			return projectRepository.findById(projectID).get();
			
		} 
		catch (Exception e) { e.printStackTrace();
		return null;
		}
	}

	public void deleteByprojectID(int projectID) {
		
		projectRepository.deleteById(projectID);
		
	}
	
	public List<Project>findByCustomer(String customer){
		return projectRepository.findByCustomer(customer);
	}
	
	public ProjectRepository getProjectRepository() {
		return projectRepository;
	}
	
	public void setProjectRepository(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

}
