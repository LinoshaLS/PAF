package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import DTO.Department;
import DTO.Doctor;
import DTO.Response;
import Util.JsonConverter;
import model.ResourceDAO;

import java.util.List;

@Path("/RMS")
public class ResourceService {

	ResourceDAO doctorRepo = new ResourceDAO();
	ResourceDAO departmentRepo =new ResourceDAO();
	
	@POST //add Doctor to the System
	@Path("/doctor")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertDoctorDetails(@FormParam("address")String address,
										@FormParam("first_name")String first_name,
										@FormParam("last_name")String last_name,
										@FormParam("email")String email)//get doctor details from form param
	{
		return new Response(doctorRepo.insertDoctorDetails(address,first_name,last_name,email));
	}

	@GET// get Doctor details by id
	@Path("/doctor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response readDoctorDetails(@PathParam("id") String id)
	{

		Doctor doctor = doctorRepo.getDoctor(id);
		if(doctor !=null){
			return javax.ws.rs.core.Response.status(200).type(MediaType.APPLICATION_JSON)
					.entity(doctor).build();
		}else{
			return javax.ws.rs.core.Response.status(404).build();
		}

	}
	 
	 @GET//list the all Doctor in doctor table
	 @Path("/doctor")
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	public String readAllDoctorDetails()
	 {

		 List<Doctor> doctors = doctorRepo.readDoctorDetails();
		 JsonConverter converter = new JsonConverter();
		 String output = converter.convertToJson(doctors);

		 return  output;

	 }
	 
	 
	 @PUT//update the new Doctor
	 @Path("/doctor/{id}")
	 @Consumes(MediaType.APPLICATION_JSON) 
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response updateDoctorDetails(@PathParam("id") String id, Doctor doctor)
	 {

		 return new Response(doctorRepo.updateDoctorDetails(id, doctor));
		 
	 } 
	 
	 
	 @DELETE//delete Doctor from the doctor table
	 @Path("/doctor/{id}")
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response deleteDoctorDetails(@PathParam("id") String id) {

		 return new Response(doctorRepo.deleteDoctorDetails(id));
	 }



	@POST//insert data to department table
	@Path("/department")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertDepartmentDetails(@FormParam("department_Name")String department)//add data from form param
	{
		return new Response(departmentRepo.insertDepartmentDetails(department));
	}

	@GET//get department by id
	@Path("/department/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response readDepartmentDetails(@PathParam("id") String id)
	{

		Department department = departmentRepo.getDepartment(id);
		if(department !=null){
			return javax.ws.rs.core.Response.status(200).type(MediaType.APPLICATION_JSON)
					.entity(department).build();
		}else{
			return javax.ws.rs.core.Response.status(404).build();
		}

	}

	@GET//list the all the department
	@Path("/department")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String readAllDepartmentDetails()
	{

		List<Department> departments= departmentRepo.readDepartmentDetails();
		JsonConverter converter = new JsonConverter();
		String output = converter.convertToJsonDepartment(departments);

		return  output;

	}

	@PUT//update the department table
	@Path("/department/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateDepartmentDetails(@PathParam("id") String id,Department department)
	{

		return new Response(departmentRepo.updateDepartmentDetails(id, department));

	}

	@DELETE//delete department from department table
	@Path("/department/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteDepartmentDetails(@PathParam("id") String id) {

		return new Response(departmentRepo.deleteDepartmentDetails(id));
	}
}
