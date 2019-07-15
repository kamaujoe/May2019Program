package com.mastek.training.hrapp.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.mastek.training.hrapp.entities.Employee;

@Component
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
	
	//-> declare the custom query method, with same name as query
	public List<Employee> findBySalary(@Param("min") double min,//-> declare parameter name
										@Param("max") double max);

}
