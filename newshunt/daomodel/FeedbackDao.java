package com.newshunt.daomodel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="feedback")
public class FeedbackDao
{
	@Id
	String email;
	String subject;
	String message;
	
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	
	public String getSubject() 
	{
		return subject;
	}
	public void setSubject(String subject)
	{
		this.subject = subject;
	}
	
	public String getMessage() 
	{
		return message;
	}
	public void setMessage(String message) 
	{
		this.message = message;
	}	
}