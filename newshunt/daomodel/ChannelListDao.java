package com.newshunt.daomodel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="channel_list")
public class ChannelListDao
{
	@Id
	String id;
	String title;
	String url;
	String description;
	String image; 
	
	public String getId()
	{
		return id;
	}
	public void setId(String id) 
	{
		this.id = id;
	}
	
	public String getUrl() 
	{
		return url;
	}
	public void setUrl(String url) 
	{
		this.url = url;
	}
	
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public String getImage()
	{
		return image;
	}
	public void setImage(String image)
	{
		this.image = image;
	}
	
}