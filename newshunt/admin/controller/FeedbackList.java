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

import com.newshunt.daomodel.FeedbackDao;
import com.newshunt.daomodel.MenuDao;

@Controller
public class FeedbackList 
{
	@RequestMapping("/userfeedback")
	public String userfeedback()
	{
		return "admin/userfeedback";
	}
	
	@RequestMapping(value = "/feedbackList" , method = RequestMethod.POST)
    @ResponseBody
	public List<FeedbackDao>feedbackList()
	{
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		Criteria cr = ss.createCriteria(FeedbackDao.class);
		List<FeedbackDao>p=cr.list();
		if(p.isEmpty()!=true)
			return p;  
		
		return null;  
   }
	
	@RequestMapping(value="/deleteFeedbackInfo", method=RequestMethod.POST)
	@ResponseBody
	public byte deleteFeedbackInfo(@RequestBody FeedbackDao rec, HttpSession hs)
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
