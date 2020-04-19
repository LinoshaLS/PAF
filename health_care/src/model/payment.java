package model;


import java.sql.*;
import java.sql.DriverManager;

public class payment{

	
	public Connection connect()
	{
	 Connection con = null;

	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");
	 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare",
	 "root", "");
	 //For testing
	 System.out.print("Successfully connected");
	 }
	 catch(Exception e)
	 {
	 e.printStackTrace();
	 }

	 return con; 
	}
	
	public String insertPayment(String cust, String payRef, String payAmount , String desc) {
		
		
		String output ="";
		try {
		
		Connection con = connect();
		if(con==null) {
			return "Error while connecting to the database for inserting";
		}
		
		
		String query = "insert into payment(`customerId`,`pamentRef`,`paymentAmount`,`paymentDesc`)" +"values(?, ?, ?, ?)";
		
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		
		//preparedStmt.setInt(1, 0);
		preparedStmt.setString(1,cust);
		preparedStmt.setString(2, payRef);
		preparedStmt.setString(3, payAmount);
		preparedStmt.setString(4, desc);
		
		preparedStmt.execute();
		con.close();
		
		output = "Inserted successfully";
	}
	catch(Exception e) {
		
	output = "Error while inserting";
	System.err.println(e.getMessage());
	
	}
		
		return output;
	}
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	public String readPayment(){
		
		String output="";
		
		
		
		try
		{
		 Connection con = connect();
		if (con == null)
		 {
		 return "Error while connecting to the database for reading.";
		 }
		
		output = "<table border=\"1\"><tr><th>Customer ID</th>"
				+ "<th>Payment Ref. No</th><th>Payment(Rs.)</th>"
				+"<th>Description</th>"
				+"<th>Update</th><th>Remove</th></tr>";
		
		
		String query = "select * from payment";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query); 
		 
		 while (rs.next()) 
		 {
			String customerId = rs.getString("customerId");
			String pamentRef = rs.getString("pamentRef");
			String paymentAmount = rs.getString("paymentAmount");
			String paymentDesc = rs.getString("paymentDesc");
			
			output +="<tr><td>" + customerId + "</td>";
			output +="<td>" + pamentRef + "</td>";
			output +="<td>" + paymentAmount + "</td>";
			output +="<td>" + paymentDesc + "</td>";
			
			
			output +="<td><input name=\btnUpdate\" "
					+ "type=\"button\" value=\"Update\"></td>"
					+ "<td><form method=\"post\" action=\"paymentForm.jsp\">"
					+ "<input name=\"btnRemove\""
					+ "type=\"submit\" value=\"Remove\">"
					+ "<input name=\"pamentRef\" type=\"hidden\""
					+ "value=\"" + pamentRef + "\"> "+ "</form></td></tr>";
					
		 
		 }
		 con.close();
		 
		 output +="</table>";
		 }
		
		
		
		
		catch (Exception e)
		{
		output = "Error while reading the items.";
		 System.err.println(e.getMessage()); 
		
		
	}
	
	return output;
	
	
	
	}
	
	public String deletepayement(String pamentRef)
	{
	 String output = "";
	 
	 
	 try {
		 Connection con = connect();
		 
		 if (con == null)
		 {
		 return "Error while connecting to the database for deleting.";
		 } 
		 
		 String query = "delete from items where pamentRef=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		 preparedStmt.setInt(2, Integer.parseInt(pamentRef));
		 
		 preparedStmt.execute();
		 con.close(); 
		 
		 output = "Deleted successfully"; 
	 }
	 catch (Exception e) {
		 
		 output = "Error while deleting payment.";
		 System.err.println(e.getMessage()); 
	 }
	 return output;
	

	}
	


	
}
