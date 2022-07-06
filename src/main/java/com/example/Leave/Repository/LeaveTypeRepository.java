package com.example.Leave.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Leave.Models.LeaveType;

public interface LeaveTypeRepository extends JpaRepository<LeaveType, Long>{
	
	@Query(value = "Select leave_id,leave_name From leave_type",nativeQuery = true)
	List<Object[]> findLeaveType();
	
	@Query( "From LeaveType where leaveId=:leaveId")
	LeaveType findByLeaveId(@Param(value = "leaveId") Long leaveId);

}
