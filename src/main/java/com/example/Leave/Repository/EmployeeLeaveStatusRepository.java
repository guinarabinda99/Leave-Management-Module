package com.example.Leave.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Leave.Models.EmpLeaveStatus;
import com.example.Leave.Models.Employee;

public interface EmployeeLeaveStatusRepository extends JpaRepository<EmpLeaveStatus, Long>{

	@Query(value = "SELECT * FROM assesment.employee_leave_status es,assesment.employee e where es.emp_code=:code",nativeQuery = true)
	EmpLeaveStatus getByEmpCode(@Param(value = "code") Long code);
	
	@Query(value = "SELECT * FROM assesment.employee_leave_status es,assesment.employee e where es.emp_code=:code",nativeQuery = true)
	EmpLeaveStatus getByEmpLeaveCount(@Param(value = "code") Long code);
	
	
}
