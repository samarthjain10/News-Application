package com.newshunt.admin.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newshunt.daomodel.MenuDao;
import com.newshunt.daomodel.signup;

@Controller
public class UserList 
{
	@RequestMapping("/userManagement")
	public String userlist()
	{
		return "admin/userlist";
	}
	
	@RequestMapping(value = "/userList" , method = RequestMethod.POST)
    @ResponseBody
	public List<signup>userList()
	{
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		Criteria cr = ss.createCriteria(signup.class);
		List<signup>p=cr.list();
		if(p.isEmpty()!=true)
			return p;  
		
		return null;  
   }
	
	@RequestMapping(value="/updateUserInfo", method=RequestMethod.POST)
	@ResponseBody
	public byte updateUserInfo(@RequestBody signup rec, HttpSession hs)
	{		
		byte flag = 0;
		try
		{
			Configuration cfg= new Configuration();
		    cfg.configure("hibernate.cfg.xml");
		    SessionFactory sf = cfg.buildSessionFactory();
		    Session ss = sf.openSession();
		    ss.update(rec);
		    ss.beginTransaction().commit();
		    flag = 1; 
		}
		catch(Exception e) 
		{			  
			e.printStackTrace();		  
		}
		return flag;
	}
	
	@RequestMapping(value="/deleteUserInfo", method=RequestMethod.POST)
	@ResponseBody
	public byte deleteUserInfo(@RequestBody signup rec, HttpSession hs)
	{		
		byte flag = 0;
		try
		{
			Configuration cfg= new Configuration();
		    cfg.configure("hibernate.cfg.xml");
		    SessionFactory sf = cfg.buildSessionFactory();
		    Session ss = sf.openSession();
		    ss.delete(rec);
		    ss.beginTransaction().commit();
		    flag = 1; 
		}
		catch(Exception e) 
		{			  
			e.printStackTrace();		  
		}
		return flag;
	}
}
