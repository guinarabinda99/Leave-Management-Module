<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<link rel="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<link rel="https://cdn.datatables.net/1.12.1/css/jquery.dataTables.min.css">
<script type="text/javascript" src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
<meta charset="ISO-8859-1">
<script>
function findEmployeeByEmpCode() {
	
    var empCode=$("#empCode").val();
    //alert(empCode);
	$.ajax({
		type : "GET",
		url : "find-employee-by-empCode", 
		data : {
			"empCode" : empCode
		},
		success : function(response) {
			
			$('#empName').val(response.empName);
			$('#desg').val(response.designation.designationName);
			},error : function(error) {
			 
		}
	});
}

function validate() {

	var d1 = new Date($('#fromDate').val());
	var d2 = new Date($('#toDate').val());
	var today = new Date();
	
	if($('#empCode').val() == '') {
		alert('Please enter emp code');
		return false;
	}
	if($('#fromDate').val() == '') {
		alert('Please enter from date');
		return false;
	}
	if(d1 < today) {
		alert('From date must not be a previous date');
		return false;
	}
	if($('#toDate').val() == '') {
		alert('Please enter to date');
		return false;
	}
	if(d2 < today) {
		alert('To date must not be a previous date');
		return false;
	}
	if(d2 < d1) {
		alert('To date must not be prior to from date');
		return false;
	}
	if($('#reason').val() == '') {
		alert('Please enter reason');
		return false;
	}
	
	var d = Math.abs(d2-d1)/(1000 * 60 * 60 * 24) + 1;
	//alert($('#ml').val())
	//alert($('#cl').val())
	if($('#leaveId').val() == 1) {
		let m = $('#ml').val();
		//alert(m)
		if(m<0) {
			alert("You don't have enough medical leaves");
			return false;
		}
		$('#ml').val(d);
	}
	else if($('#leaveId').val() == 2) {
		let c = $('#cl').val();
		//alert(c)
		if(c<0) {
			alert("You don't have enough casual leaves");
			return false;
		}
		$('#cl').val(d);
	}
	return true;
}
function searchData(){
	 var empCode=$("#empCode").val();
	 $.ajax({
			type : "GET",
			url : "find-employee-Leave-details", 
			data : {
				"empCode" : empCode
			},
			success : function(response) {

				$('#ml').val(response.employee.designation.maxML - response.medl);
				$('#cl').val(response.employee.designation.maxCL-response.casl);
				$('#tblBody').empty().append('<tr><th>' + response.casl + '/' + response.employee.designation.maxCL + '</th><th>' + response.medl + '/' + response.employee.designation.maxML + '</th></tr>');
				},error : function(error) {
				 
			}
		});
}
function documentHide(){
	let l=$('#leaveId').val()
	if(l==2){
		$('#docId').hide()
		$('#document').hide()
		}else{
			$('#docId').show()
			$('#document').show()
			}
}
</script>
<title>Leave Application</title>
</head>
<body>
	<h1>Leave Application Form</h1>
	
	<form action="save" method="post">
	
		Emp Code <span class="mandatory">*</span> &nbsp; <input type="text" id="empCode" name="empCode" onchange="findEmployeeByEmpCode();searchData()"> &nbsp;&nbsp;
		
		Emp Name &nbsp; <input type="text" readonly="readonly" id="empName" name="empName"> &nbsp;&nbsp;
		
		Emp Designation &nbsp; <input type="text" readonly="readonly" id="desg" name="desg"> <br><br>
		
		Leave Type <span class="mandatory">*</span> &nbsp;
		 <select name="leaveId" id="leaveId" onclick="documentHide()">
			<!-- <option value="0">-Select-</option> -->
			<c:forEach items="${LeaveType}" var="leave1">
				<option value="${leave1[0]}">${leave1[1]}</option>
			</c:forEach>
		</select> &nbsp;&nbsp;
			<label for="document" id="docId">Document</label>
		 &nbsp; <input type="file" id="document" name="document"> <br><br>
		
		From Date <span class="mandatory">*</span>  &nbsp; <input type="date" id="fromDate" name="fromDate"> &nbsp;&nbsp;
		
		To Date <span class="mandatory">*</span> &nbsp; <input type="date" id="toDate" name="toDate"> <br><br>
		
		Reason <span class="mandatory">*</span> <input type="text" id="reason" name="reason"> <br><br>
		
		<input type="hidden" id="cl" name="cl">
		<input type="hidden" id="ml" name="ml">
		
		<input type="submit" value="Apply" onclick="return validate()"> &nbsp;&nbsp;
		<input type="reset" value="Reset">
		
	</form>
	
	<hr>
	
	<h3>Emp. Leave Status</h3>
	<table border="1" id="dataTable">
		<thead>
			<tr>
				<th>CL</th>
				<th>ML</th>
			</tr>
		</thead>
		<tbody id="tblBody"></tbody>
	</table>
</body>
</html>