package com.newshunt.loginsignup.controller;

import java.util.ArrayList;
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

import com.newshunt.daomodel.signup;

@Controller
public class LoginSignup
{
	
	@RequestMapping("/")
	public String login() 
	{
		return "login";
	}

	@RequestMapping("/signup")
	public String signup() 
	{
		return "signup";
	}
	
	@RequestMapping(value="/saveUserInfo" , method=RequestMethod.POST)
	@ResponseBody
	public int saveUserInfo(@RequestBody signup rec)
	{		
		int a=0;
		try
		{
			if(rec.getName().equals("") || rec.getEmail().equals("") || rec.getPhone().equals("") || rec.getPassword().equals("") || rec.getConfirmPassword().equals(""))
			{
				a=1;
			}
			else
			{
				Configuration cfg= new Configuration();
				cfg.configure("hibernate.cfg.xml");
				SessionFactory sf = cfg.buildSessionFactory();
				Session ss = sf.openSession();
				Criteria cr = ss.createCriteria(signup.class);
				List<signup>z=cr.list();
				for(signup m:z)
				{
					if(rec.getEmail().equals(m.getEmail()))
					{
						a=2;
				    }
				}
				if(rec.getPassword().equals(rec.getConfirmPassword()) && a!=2)
				{
					a=3; 				
					ss.save(rec);
					ss.beginTransaction().commit();   	   
				}
			}
		}
		catch(Exception e) 
		{	  
			a=1;
			e.printStackTrace();  
		}
		return a;
	}
	
	@ResponseBody
	@RequestMapping(value="/checkInfo", method=RequestMethod.POST)
	public int checkInfo(@RequestBody signup r, HttpSession session)
	{
		String un="";
		int flag=0;
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
        Session ss = sf.openSession();
        Criteria cr = ss.createCriteria(signup.class);
        List<signup>p=cr.list();
        for(signup x:p)
        {
        	if(r.getEmail().equals(x.getEmail()) && r.getPassword().equals(x.getPassword()))
        	{
        		flag=1;
        		un = r.getEmail();
        		session.setAttribute("un",un);
        	}
        	else if(r.getEmail().equals("admin") && r.getPassword().equals("admin"))
        	{
        		flag=2;
        	}
        }
        return flag;
	}
	
	@RequestMapping(value="/username", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList username(HttpSession session)
	{
		String uname = session.getAttribute("un").toString();
		ArrayList<String>al=new ArrayList();
		al.add(uname);
	    return al;
	}
	
}