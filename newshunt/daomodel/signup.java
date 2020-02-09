package com.newshunt.daomodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_account")
public class signup 
{
	@Id
	@Column(name="email_id")
    String email;
	String name;
    String password;
    @Column(name="confirm_password")
    String confirmPassword;
    @Column(name="phone_no")
    String phone;
    String mychannel;
    String favchannel;
    
	public String getMychannel() 
	{
		return mychannel;
	}
	public void setMychannel(String mychannel)
	{
		this.mychannel = mychannel;
	}
	
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String getConfirmPassword() 
	{
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) 
	{
		this.confirmPassword = confirmPassword;
	}
	
	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone) 
	{
		this.phone = phone;
	}
	
	public String getFavchannel() 
	{
		return favchannel;
	}
	public void setFavchannel(String favchannel) 
	{
		this.favchannel = favchannel;
	}	
	
}