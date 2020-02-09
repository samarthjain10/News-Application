package com.newshunt.daomodel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin_menu")
public class AdminMenuDao
{
	@Id
	int id;
    String name;
	String url;
	
    public int getId()
    {
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}

	public String getUrl() 
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
}
