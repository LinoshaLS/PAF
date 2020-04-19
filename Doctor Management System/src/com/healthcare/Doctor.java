package com.healthcare;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Doctor {
	
	private Connection connect() {
		
		Connection con = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare","root","");
			System.out.println("Successfully connected");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
		
	}
	
	
public String insertDoctor(String docName, String docNIC, String specialization, String gender, String phoneNo) {
		
		String output = "";
		try {
			Connection con = connect();
			if(con==null) {
				return "Error while connecting to the database for inserting";
			}
			
			//create a prepared statement
			String query = " insert into doctor (`docId`,`docName`,`docNIC`,`specialization`,`gender`,`phoneNo`) values(?,?,?,?,?,?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, docName);
			preparedStmt.setString(3, docNIC);
			preparedStmt.setString(4, specialization);
			preparedStmt.setString(5, gender);
			preparedStmt.setString(6, phoneNo);
			
			//execute the statement
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

public String readDoctor() {
	String output = "";
	try {
		Connection con = connect();
		if(con==null) {
			return "Error while connecting to the database for reading.";
		}
		
		//prepare the html table to be displayed
		output = "<table border=\"1\"><tr><th>Name</th><th>NIC</th><th>Specialization</th><th>Gender</th><th>Phone No</th><th>Update</th><th>Remove</th></tr>";
		
		String query = "select * from doctor";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		//iterate through the rows in the result set
		while(rs.next()) {
			String docId = Integer.toString(rs.getInt("docId"));
			String docName = rs.getString("docName");
			String docNIC = rs.getString("docNIC");
			String specialization = rs.getString("specialization");
			String gender = rs.getString("gender");
			String phoneNo = rs.getString("phoneNo");
			
			//Add into the html table
			output += "<tr><td>"+ docName + "</td>";
			output += "<td>"+ docNIC + "</td>";
			output += "<td>"+ specialization + "</td>";
			output += "<td>"+ gender + "</td>";
			output += "<td>"+ phoneNo + "</td>";
			
			//buttons
			output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn-secondary\"></td>"
					+ "<td><form method=\"post\" action=\"Doctor.jsp\">"
					+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\" class=\"btn btn-danger\">"
					+ "<input name=\"docId\" type=\"hidden\" value=\"" + docId + "\">"+"</form></td></tr>";		
		}
		con.close();
		
		//Complete the html
		output += "</table>";
	}
	catch(Exception e) {
		output = "Error while reading the doctor list.";
		System.err.println(e.getMessage());
	}
	return output;
}

public String updateDoctor(int docId, String docName, String docNIC, String specialization, String gender, String phoneNo) {
	
	String output = "";
	
	try {
		Connection con= connect();
		
		if(con==null) {
			return "Error while connecting to the database";
		}
		
		//create a prepared statement
		String query = "UPDATE doctor SET docName=?,docNIC=?,specialization=?,gender=?,phoneNo=? WHERE docId=?";
		
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		//binding values
		preparedStmt.setString(1, docName);
		preparedStmt.setString(2, docNIC);
		preparedStmt.setString(3, specialization);
		preparedStmt.setString(4, gender);
		preparedStmt.setString(5, phoneNo);
		preparedStmt.setInt(6, docId);
		
		//execute the statement
		preparedStmt.execute();
		con.close();
		
		output = "Updated successfully";
	}
	catch(Exception e) {
		output = "Error while updating";
		System.err.println(e.getMessage());
	}
	return output;
}

public String deleteDoctor(String docId) {
	String output = "";
	
	try {
		Connection con = connect();
		
		if(con == null) {
			return "Error while connecting to the database for deleting.";
		}
		
		//creating a prepared statement
		String query = "delete from doctor where docId=?";
		
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		//binding values
		preparedStmt.setInt(1,Integer.parseInt(docId));
		
		//execute the statement
		preparedStmt.execute();
		con.close();
		
		output = "Deleted successfully";
	}
	catch(Exception e) {
		output = "Error while dleting";
		System.err.println(e.getMessage());
	}
	
	return output;
}

}
