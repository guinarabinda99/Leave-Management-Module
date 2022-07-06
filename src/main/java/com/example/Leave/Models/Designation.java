package com.example.Leave.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Emp_Designation")
public class Designation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long DesgId;
	
	@Column(name = "designation_Name")
	private String designationName;
	
	@Column(name = "max_CL")
	private int maxCL;
	
	@Column(name = "max_ML")
	private int maxML;

	

	public Long getDesgId() {
		return DesgId;
	}

	public void setDesgId(Long desgId) {
		DesgId = desgId;
	}

	public String getDesignationName() {
		return designationName;
	}

	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}

	public int getMaxCL() {
		return maxCL;
	}

	public void setMaxCL(int maxCL) {
		this.maxCL = maxCL;
	}

	public int getMaxML() {
		return maxML;
	}

	public void setMaxML(int maxML) {
		this.maxML = maxML;
	}
	
	

}
