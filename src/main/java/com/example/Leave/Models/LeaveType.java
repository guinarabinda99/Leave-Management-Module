package com.example.Leave.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "LeaveType")
public class LeaveType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long leaveId;
	
	@Column(name = "LeaveName")
	private String LeaveTypeName;

	public Long getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(Long leaveId) {
		this.leaveId = leaveId;
	}

	public String getLeaveTypeName() {
		return LeaveTypeName;
	}

	public void setLeaveTypeName(String leaveTypeName) {
		LeaveTypeName = leaveTypeName;
	}
	
	

}
