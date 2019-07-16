package com.mastek.training.hrapp.apis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mastek.training.hrapp.entities.Department;
import com.mastek.training.hrapp.repositories.DepartmentRepository;

@Component
@Scope("singleton")
public class DepartmentService {

	@Autowired
	private DepartmentRepository  departmentRepository;
	
	
	public DepartmentService() {
		System.out.println("Department service created");
	}
	
	
	//-> DataBase
	

	
	public Department registerOrUpdateDepartment(Department dept) {
		
		dept = departmentRepository.save(dept);
		System.err.println("Department Registered"+dept);
		return dept;	
	}
	
	public Department findBydeptno(int deptno) {
		
		try {
			return departmentRepository.findById(deptno).get();
			
		} 
		catch (Exception e) { e.printStackTrace();
		return null;
		}
	}

	public void deleteBydeptno(int deptno) {
		
		departmentRepository.deleteById(deptno);
		
	}
	
	public List<Department>findByLocation(String location){
		return departmentRepository.findByLocation(location);
	}
	
	public DepartmentRepository getDepartmentRepository() {
		return departmentRepository;
	}
	
	public void setDepartmentRepository(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}



}
