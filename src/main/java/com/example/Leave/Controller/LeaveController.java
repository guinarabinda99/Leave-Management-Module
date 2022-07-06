package com.example.Leave.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Leave.Models.Designation;
import com.example.Leave.Models.EmpLeaveStatus;
import com.example.Leave.Models.Employee;
import com.example.Leave.Models.LeaveType;
import com.example.Leave.Repository.EmployeeLeaveStatusRepository;
import com.example.Leave.Repository.EmployeeRepository;
import com.example.Leave.Repository.LeaveTypeRepository;

@Controller
public class LeaveController {
	
	@Autowired
	private EmployeeRepository empRepo;
	
	@Autowired
	private LeaveTypeRepository leaveTypeRepo;
	
	@Autowired
	private EmployeeLeaveStatusRepository empleavests;
	
	
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("LeaveType", leaveTypeRepo.findLeaveType());
		//model.addAttribute("Desg", designationRepository.findById(id));
		return "index";
	}

	@RequestMapping(value = "find-employee-by-empCode", method = RequestMethod.GET)
	@ResponseBody
	public Employee findEmployeeByEmpcode(@RequestParam("empCode") Long empCode){
		return empRepo.findByEmpCode(empCode);
	}
	@RequestMapping(value = "find-employee-Leave-details",method = RequestMethod.GET)
	@ResponseBody
	public EmpLeaveStatus find(@RequestParam("empCode") Long empCode) {
		return empleavests.getByEmpCode(empCode);
		
	}
	
	@PostMapping("/save")
	public String save(Model model,
			@RequestParam(value = "leaveId",required = false) Long leaveId,
			@RequestParam(value = "document",required = false) String document,
			@RequestParam(value = "empCode",required = false) Long empCode,
			@RequestParam(value = "reason",required = false) String reason,
			@RequestParam(value = "fromDate", required = false) String FromDate,
			@RequestParam(value = "toDate", required = false) String ToDate,
			@RequestParam(value = "cl", required = false) int cl,
			@RequestParam(value = "ml", required = false) int ml,
			@ModelAttribute("LeaveType") LeaveType leaveType,
			@ModelAttribute("Designation") Designation desg,
			@ModelAttribute("EmpLeaveStatus") EmpLeaveStatus emps) {
		try {
			emps=empleavests.getByEmpCode(empCode);
			 System.out.println(leaveId);
			if(emps==null) {
				System.out.println("Casual Comming");
				emps=new EmpLeaveStatus();
				emps.setEmployee(empRepo.findByEmpCode(empCode));
				emps.setFromdt(FromDate);
				emps.setTodt(ToDate);
				emps.setReason(reason);
				if(leaveId==1) {
					emps.setMedl(ml);
					emps.setDocName(document);
				}
				else {
					emps.setCasl(cl);
				}
				emps.setLeaveType(leaveTypeRepo.findByLeaveId(leaveId));
				empleavests.save(emps);
			}
			else {
				System.out.println("Update Casual");
				emps.setFromdt(FromDate);
				emps.setTodt(ToDate);
				emps.setReason(reason);
				
				if(leaveId==1) {
					int med=emps.getMedl();
					emps.setMedl(ml+med);
					emps.setDocName(document);
				}
				else {
					int cas=emps.getCasl();
					emps.setCasl(cl+cas);
				}
				empleavests.save(emps);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		    
		return "redirect:/";
	}
}
