package com.newshunt.accountmanagement.controller;

import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newshunt.daomodel.signup;
@Controller

public class AccountInfo 
{
	@RequestMapping("/accountInfo")
	public String accountInfo()
	{
		return "accountPage";
	}
	
	@RequestMapping(value="/updateAccountInfo", method=RequestMethod.POST)
	@ResponseBody
	public byte updateAccountInfo(@RequestBody signup rec, HttpSession hs)
	{		
		byte flag = 0;
		try
		{
			String un = hs.getAttribute("un").toString();
			Configuration cfg= new Configuration();
		    cfg.configure("hibernate.cfg.xml");
		    SessionFactory sf = cfg.buildSessionFactory();
		    Session ss = sf.openSession();
		    Criteria cr = ss.createCriteria(signup.class);
		    cr.add(Restrictions.eq("email", un));
		    signup r = (signup)cr.uniqueResult();
		    r.setName(rec.getName());
		    r.setPassword(rec.getPassword());
		    r.setPhone(rec.getPhone());
		    ss.beginTransaction().commit();
		    flag = 1; 
		}
		catch(Exception e) 
		{			  
			e.printStackTrace();		  
		}
		return flag;
	}
	
	@RequestMapping(value="/fetchAccountInfo", method=RequestMethod.POST)
	@ResponseBody
	public signup fetchinfo(HttpSession hs)
	{
		String un = hs.getAttribute("un").toString();
		Configuration cfg= new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		Criteria cr = ss.createCriteria(signup.class);
		cr.add(Restrictions.eq("email", un));
		signup r = (signup)cr.uniqueResult();
		return r;		  		  
	}
	
}