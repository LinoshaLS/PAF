<%@ page import="com.healthcare.Doctor" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  
<% 

	//Inserting
	if (request.getParameter("docName") != null)  {
	
		Doctor docObj = new Doctor();
		String stsMsg = docObj.insertDoctor(request.getParameter("docName"),request.getParameter("docNIC"),request.getParameter("specialization"),
			 	request.getParameter("gender"), request.getParameter("phoneNo"));
	
		session.setAttribute("statusMsg",stsMsg);
	
		}

	 //Deleting
	 if (request.getParameter("docId") != null)  {   
		Doctor docObj = new Doctor();  
		String stsMsg = docObj.deleteDoctor(request.getParameter("docId"));
		session.setAttribute("statusMsg", stsMsg);  
		} 
%> 




<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title>Doctor Registration</title>
</head>
<body>
<div class="text-center">
	<h1>Doctor Management</h1>
</div>
 <form method="post" action="Doctor.jsp" >
 	<div class="form-group row">
 	 	<label class="col-sm-1 col-form-label">Name</label>
     	<div class="col-sm-8">
      		<input name="docName" type="text" class="form-control">
      	</div>
    </div>
    <div class="form-group row">
     	<label class="col-sm-1 col-form-label">NIC</label>
     	<div class="col-sm-8">
      		<input name="docNIC" type="text" class="form-control">
      	</div>
    </div>
    <div class="form-group row">
     	<label class="col-sm-1 col-form-label">Specialization</label>
     	<div class="col-sm-8">
      		<input name="specialization" type="text" class="form-control">
      	</div>
    </div>
    <div class="form-group row">   
      	<label class="col-sm-1 col-form-label">Gender</label> 
      	<div class="col-sm-8">
       		<input name="gender" type="text" class="form-control">
       	</div>
    </div>
    <div class="form-group row">
      	<label class="col-sm-1 col-form-label">Phone No</label> 
      	<div class="col-sm-8">
       		<input name="phoneNo" type="text" class="form-control">
        </div>
    </div>
    <input name="btnSubmit" type="submit" value="Save" class="btn btn-primary col-sm-3">  
 </form>

<div class="alert alert-success"> 
<% 
	out.print(session.getAttribute("statusMsg"));
%>
</div> 

 
 <br>
 <%
 	Doctor docObj = new Doctor();
    out.print(docObj.readDoctor());
 %>
</body>
</html>