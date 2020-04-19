




<%@page import="model.payment"%>
<%
if (request.getParameter("pamentRef") != null)
{
	
	
	payment p1 = new payment();
	///p1.connect();
	
	String stsMsg = p1.insertPayment(request.getParameter("customerId"),
			 request.getParameter("pamentRef"),
			 request.getParameter("paymentAmount"),
			 request.getParameter("paymentDesc")
			 );
	
	session.setAttribute("statusMsg", stsMsg);
	 
	 
// session.setAttribute("customerId", request.getParameter("customerId"));
 //session.setAttribute("pamentRef", request.getParameter("pamentRef"));
 //session.setAttribute("paymentAmount", request.getParameter("paymentAmount"));
 //session.setAttribute("paymentDesc", request.getParameter("paymentDesc"));

}

if (request.getParameter("pamentRef") != null)
{
	payment p2 = new payment();
String stsMsg = p2.deletepayement(request.getParameter("pamentRef"));
session.setAttribute("statusMsg", stsMsg);
}

%>




<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment</title>
</head>
<body>
<h1>Payment</h1>


<form method="post" action="paymentForm.jsp">

Customer ID:<input name="customerId" type="text"><br>
Payment Ref. No: <input name="pamentRef" type="text"><br>
Payment(Rs.):<input name="paymentAmount" type="text"><br>
Description:<input name="paymentDesc" type="text"><br>

<input name="paymentSubmit" type="submit" value="save">

</form>
<% out.print(session.getAttribute("statusMsg")); %>
<br>
<%-- <table border="1">


<tr>
	<th>Customer ID</th>
	<th>Payment Ref. No</th>
	<th>Payment(Rs.)</th>
	<th>Description</th>
	<th>Update</th>
	<th>remove</th>	
</tr>
<tr>

	
 <td><%out.print(session.getAttribute("customerId")); %></td>
 <td><%out.print(session.getAttribute("pamentRef")); %></td>
 <td><%out.print(session.getAttribute("paymentAmount")); %></td>
 <td><%out.print(session.getAttribute("paymentDesc")); %></td>


	
 <td><input name="paymentBtnUpdate" type="button" value="update"></td>
 <td><input name="paymentBtnRemove" type="button" value="remove"></td>

</tr>
</table> --%>

<%
payment itemObj = new payment();
 out.print(itemObj.readPayment());
%>

</body>
</html>