package com.newshunt.admin.controller;

import java.util.HashSet;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newshunt.daomodel.AdminMenuDao;
import com.newshunt.daomodel.ChannelListDao;
import com.newshunt.daomodel.signup;

@Controller
public class AdminHome 
{
	@RequestMapping("/AdminHome")
	public String adminhome()
	{
		return "admin/adminHome";
	}
	
	@RequestMapping(value = "/AdminMenuList" , method = RequestMethod.POST)
    @ResponseBody
	public List<AdminMenuDao>adminMenuList()
	{
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		Criteria cr = ss.createCriteria(AdminMenuDao.class);
		List<AdminMenuDao>p=cr.list();
		if(p.isEmpty()!=true)
			return p;  
		
		return null;  
   }
	
	@RequestMapping(value = "/userListCount" , method = RequestMethod.POST)
    @ResponseBody
	public int userListCount()
	{
		int count=0;
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		Criteria cr = ss.createCriteria(signup.class);
		List<signup>p=cr.list();
		for(signup x:p)
        {
        	count++;
        }  
		return count;  
   }
	
	@RequestMapping(value = "/ChannelListCount" , method = RequestMethod.POST)
    @ResponseBody
	public int ChannelListCount()
	{
		int count=0;
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		Criteria cr = ss.createCriteria(ChannelListDao.class);
		List<ChannelListDao>p=cr.list();
		for(ChannelListDao x:p)
        {
        	count++;
        }  
		
		return count;  
   }
	
	@RequestMapping(value="/SuscribedListCount", method=RequestMethod.POST)
	@ResponseBody
	public int SuscribedListCount()
	{
		int flag=0;
		HashSet<String> hs=new HashSet();
		Configuration cfg= new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();	    
		Criteria criteria = ss.createCriteria(signup.class);
		List<signup>info =criteria.list();
		for(signup w : info) 
		{
			String m[] = w.getMychannel().split(",");		
			for(String v: m) 
			{
				hs.add(v);
		    }
		}
		flag=hs.size();
		return flag;
	}
}
