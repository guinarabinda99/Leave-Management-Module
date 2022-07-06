package com.example.Leave.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity(name = "Employee_Leave_Status")
public class EmpLeaveStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "DocName")
	private String docName;
	
	@Column(name = "FromDate")
	private String fromdt;
	
	@Column(name = "ToDate")
	private String todt;
	
	@Column(name = "Leave_Reason")
	private String reason;
	
	private int casl;
	private int medl;
	
	@Lob
	private byte[] docData;
	
	@ManyToOne
	@JoinColumn(name = "emp_code")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name = "leave_Id")
	private LeaveType leaveType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	

	public String getFromdt() {
		return fromdt;
	}

	public void setFromdt(String fromdt) {
		this.fromdt = fromdt;
	}

	public String getTodt() {
		return todt;
	}

	public void setTodt(String todt) {
		this.todt = todt;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public byte[] getDocData() {
		return docData;
	}

	public void setDocData(byte[] docData) {
		this.docData = docData;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public LeaveType getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}

	public int getCasl() {
		return casl;
	}

	public void setCasl(int casl) {
		this.casl = casl;
	}

	public int getMedl() {
		return medl;
	}

	public void setMedl(int medl) {
		this.medl = medl;
	}
	
	

}
