package model;

import DTO.Department;
import DTO.Doctor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResourceDAO {

	private Connection connect()  {
		
		Connection con = null; 
	 
	  try   
	  {     
		  Class.forName("com.mysql.jdbc.Driver");              
		  con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rms_db", "root", "");//database connection
		  
	  }   
	  catch (Exception e)   
	  {e.printStackTrace();} 
	 
	  return con;  
	  } 
	
	public boolean insertDoctorDetails(String address, String first_name, String last_name, String email)//insert doctor function
	{   
		String output = "";
	 try   
	  {    
		  Connection con = connect(); 
	 
	   if (con == null)   
	   {
		   return false;
	   }
	 
	      String query = " insert into doctors (address,first_name, last_name,email ) "
	    		  + " values ( ?, ?, ?,? )";

	      PreparedStatement preparedStmt = con.prepareStatement(query);


	      preparedStmt.setString(1, address);
	      preparedStmt.setString(2, first_name);
	      preparedStmt.setString(3, last_name);
	      preparedStmt.setString(4, email);


	      preparedStmt.execute();
	      con.close();

	      output = "Inserted successfully";
		  return true;

	  }catch (Exception e)

	 	{
		   output = "Error while inserting the Details";
		   System.err.println(e.getMessage());
		   return false;
		}

	}
	
	
		public List<Doctor> readDoctorDetails()//list all the doctor function
	{   
		String output = ""; 

		try   
		{   
			Connection con = connect(); 
		
			if (con == null)    
			{
				return null;
			} 
			
	 
	     output = "<table border=\"1\"><tr><th>Hospital Name</th><th>Address</th><th>Contact No</th><th>Email</th><th>Update</th><th>Remove</th></tr>";
			List<Doctor> doctors = new ArrayList<>();
	     	String query = "select * from doctors";
	     	Statement stmt = con.createStatement();
	     	ResultSet rs = stmt.executeQuery(query);


	 
	     while (rs.next())    
	     {     
	    	 String id = Integer.toString(rs.getInt("id"));
	    	 String address = rs.getString("address");
	    	 String firstName = rs.getString("first_name");
	    	 String lastName = rs.getString("last_name");
	    	 String Email = rs.getString("email");

			 Doctor doctor = new Doctor(id, address, firstName, lastName,Email);
			 doctors.add(doctor);


	         } 
	
	     con.close(); 
	 	return doctors;

	     }

		catch (Exception e)  
		{    
		return null;
		}



	  } 
	
	public boolean updateDoctorDetails(String ID, Doctor doctor)//update doctor function
	{   
		String output = ""; 
	 
		try   
		{    
				Connection con = connect(); 
	 
				if (con == null)   
				{
					return false;
				} 
	 
	   String query = "UPDATE doctors SET address=?,first_name=?,last_name=?,email=? WHERE id=?"; 
	 
	   PreparedStatement preparedStmt = con.prepareStatement(query);

	   preparedStmt.setString(1, doctor.getAddress());
	   preparedStmt.setString(2, doctor.getFirst_name());
	   preparedStmt.setString(3, doctor.getLast_name());
	   preparedStmt.setString(4, doctor.getEmail());
	   preparedStmt.setInt(5, Integer.parseInt(ID));

	 
	   preparedStmt.execute();   
	   con.close(); 
	 
	   output = "Updated successfully";
	   return true;
	}   
		catch (Exception e)   
	{    
			output = "Error while updating the Details.";    
			System.err.println(e.getMessage());   
			return false;
	}
	  
	}
	
	public boolean deleteDoctorDetails(String id)//delete doctor function
	{   
		String output = ""; 
	 
	  try   
	  {    
		  Connection con = connect(); 
	 
	   if (con == null)    
	   {
		   return false;
	   }
	 
	   String query = "delete from doctors where id=?";
	 
	   PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	   preparedStmt.setInt(1, Integer.parseInt(id));
	 
	   preparedStmt.execute();    
	   con.close(); 
	 
	   output = "Deleted successfully"; 
	   return true;
	  }   
	  catch (Exception e)   
	  {    
		  output = "Error while deleting the Details.";    
		  System.err.println(e.getMessage());
		  return false;
	  }
	}


	public Doctor getDoctor(String id)//doctor get by id
	{
		String output = "";

		try
		{
			Connection con = connect();

			if (con == null)
			{
				return null;
			}


			output = "<table border=\"1\"><tr><th>Hospital Name</th><th>Address</th><th>Contact No</th><th>Email</th><th>Update</th><th>Remove</th></tr>";
			Doctor doctor = null;
			String query = "select * from doctors where id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, Integer.parseInt(id));
			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next())
			{
				String address = rs.getString("address");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String Email = rs.getString("email");

				doctor = new Doctor(id, address, firstName, lastName,Email);
			}
			con.close();
			return doctor;
		}

		catch (Exception e)
		{
			return null;
		}

	}

	public boolean insertDepartmentDetails(String department_Name)//insert department function
	{
		String output = "";
		try
		{
			Connection con = connect();

			if (con == null)
			{
				return false;
			}

			String query = " insert into department (department_Name) "
					+ " values ( ? )";

			PreparedStatement preparedStmt = con.prepareStatement(query);


			preparedStmt.setString(1, department_Name);



			preparedStmt.execute();
			con.close();

			output = "Inserted successfully";
			return true;

		}catch (Exception e)

		{
			output = "Error while inserting the Details";
			System.err.println(e.getMessage());
			return false;
		}

	}

	public List<Department> readDepartmentDetails()// list all department function
	{
		String output = "";

		try
		{
			Connection con = connect();

			if (con == null)
			{
				return null;
			}


			output = "<table border=\"1\"><tr><th>Hospital Name</th><th>Address</th><th>Contact No</th><th>Email</th><th>Update</th><th>Remove</th></tr>";
			List<Department> departments = new ArrayList<>();
			String query = "select * from department";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);



			while (rs.next())
			{
				String id = Integer.toString(rs.getInt("id"));
				String department_Name = rs.getString("department_Name");


				Department department = new Department(id, department_Name);
				departments.add(department);


			}

			con.close();
			return departments;

		}

		catch (Exception e)
		{
			return null;
		}



	}

	public boolean updateDepartmentDetails(String ID, Department department)//update department details function
	{
		String output = "";

		try
		{
			Connection con = connect();

			if (con == null)
			{
				return false;
			}

			String query = "UPDATE department SET department_Name=? WHERE id=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setString(1,department.getDepartment_Name());
			preparedStmt.setInt(2, Integer.parseInt(ID));


			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
			return true;
		}
		catch (Exception e)
		{
			output = "Error while updating the Details.";
			System.err.println(e.getMessage());
			return false;
		}

	}

	public boolean deleteDepartmentDetails(String id)//delete department function
	{
		String output = "";

		try
		{
			Connection con = connect();

			if (con == null)
			{
				return false;
			}

			String query = "delete from department where id=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, Integer.parseInt(id));

			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
			return true;
		}
		catch (Exception e)
		{
			output = "Error while deleting the Details.";
			System.err.println(e.getMessage());
			return false;
		}
	}

	public Department getDepartment(String id)//get department by id function
	{
		String output = "";

		try
		{
			Connection con = connect();

			if (con == null)
			{
				return null;
			}


			output = "<table border=\"1\"><tr><th>Hospital Name</th><th>Address</th><th>Contact No</th><th>Email</th><th>Update</th><th>Remove</th></tr>";
			Department department = null;
			String query = "select * from department where id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, Integer.parseInt(id));
			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next())
			{
				String department_Name = rs.getString("department_Name");

				department = new Department(id,department_Name);
			}
			con.close();
			return department;
		}

		catch (Exception e)
		{
			return null;
		}

	}

}
