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

@Controller
public class AdminMenuManagement 
{
	
	
	@RequestMapping("/AddMenu")
	public String AddMenu()
	{
		return "admin/addMenu";
	}
	
	@RequestMapping(value="/AddUserMenu", method = RequestMethod.POST)
	@ResponseBody
	public byte AddMenu(@RequestBody MenuDao r)
	{
		byte a=0;
    	try
    	{
    		MenuDao p = new MenuDao();
    		p.setId(r.getId());
    		p.setName(r.getName());
    		p.setUrl(r.getUrl());
		   Configuration cfg= new Configuration();
	       cfg.configure("hibernate.cfg.xml");
	       SessionFactory sf = cfg.buildSessionFactory();
	       Session ss = sf.openSession();
	       ss.save(p);
	       ss.beginTransaction().commit();
    	   a=1; 
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
	   return a;
   }
	
	@RequestMapping("/MenuList")
	public String MenuList()
	{
		return "admin/menuList";
	}
	
	@RequestMapping(value = "/ShowMenuList" , method = RequestMethod.POST)
    @ResponseBody
	public List<MenuDao>ShowMenuList()
	{
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		Criteria cr = ss.createCriteria(MenuDao.class);
		List<MenuDao>p=cr.list();
		if(p.isEmpty()!=true)
			return p;  
		
		return null;  
   }
	
	@RequestMapping(value="/updateMenuInfo", method=RequestMethod.POST)
	@ResponseBody
	public byte updateMenuInfo(@RequestBody MenuDao rec, HttpSession hs)
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
	
	@RequestMapping(value="/deleteMenuInfo", method=RequestMethod.POST)
	@ResponseBody
	public byte deleteMenuInfo(@RequestBody MenuDao rec, HttpSession hs)
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
