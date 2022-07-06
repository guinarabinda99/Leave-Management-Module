package com.example.Leave.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Leave.Models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	@Query( "From Employee where empCode=:code")
	Employee findByEmpCode(@Param(value = "code") Long empCode);
}
