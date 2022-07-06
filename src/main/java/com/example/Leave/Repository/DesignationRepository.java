package com.example.Leave.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Leave.Models.Designation;

public interface DesignationRepository extends JpaRepository<Designation, Long> {

//	@Query( "From Designation where DesgId=:desgId")
//	Designation findByDesignationId(@Param(value = "desgId") Long desgId);
//	
	@Query(value = "select * From emp_designation where desg_Id=:desgId ",nativeQuery = true)
	Designation findByDesignationId(@Param(value = "desgId") Long desgId);
}
