package com;

import model.payment;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


import com.google.gson.*; 

import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document; 

@Path("/Payment")
public class paymenetService {
	
	payment p1 = new payment();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayment() {
		
		return "hello";
	}
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)

	public String insertPayment(@FormParam("customerId") String customerId,
			@FormParam("pamentRef") String pamentRef,
			@FormParam("paymentAmount") String paymentAmount,
			@FormParam("paymentDesc") String paymentDesc
			
			)
	{
	String output = p1.insertPayment(customerId, pamentRef, paymentAmount, paymentDesc);
	
	return output;
	
}
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatpayment(String pamentRef) {
		
		JsonObject payObject = new JsonParser().parse(itemData).getAsJsonObject();
		
		String customerId = payObject.get("customerId").getAsString();
		 String pamentRef = payObject.get("pamentRef").getAsString();
		 String paymentAmount = payObject.get("paymentAmount").getAsString();
		 String paymentDesc = payObject.get("paymentDesc").getAsString();
		 
		 
		 String output = payObject.updatpayment(customerId, pamentRef, paymentAmount, paymentDesc);
		 return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String deletepayement(String pamentRef)
	{ 
		Document doc = Jsoup.parse(pamentRef, "", Parser.xmlParser());
		
		String pamentRef = doc.select("pamentRef").text();
	
		String output = p1.deletepayement(pamentRef);
		return output; 
	
	}
		
		
	}

