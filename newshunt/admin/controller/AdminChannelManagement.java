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

import com.newshunt.daomodel.ChannelListDao;
import com.newshunt.daomodel.MenuDao;
import com.newshunt.daomodel.signup;

@Controller
public class AdminChannelManagement 
{
	@RequestMapping("/AddUserChannel")
	public String AddUserChannel()
	{
		return "admin/addUserChannel";
	}
	
	@RequestMapping(value="/ChannelAddOn", method = RequestMethod.POST)
	@ResponseBody
	public byte ChannelAddOn(@RequestBody ChannelListDao r)
	{
		byte a=0;
    	try
    	{
    		ChannelListDao p = new ChannelListDao();
    		p.setId(r.getId());
    		p.setTitle(r.getTitle());
    		p.setDescription(r.getDescription());
    		p.setImage(r.getImage());
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
	
	@RequestMapping("/ChannelList")
	public String ChannelList()
	{
		return "admin/channelList";
	}
	
	@RequestMapping(value = "/ShowChannelList" , method = RequestMethod.POST)
    @ResponseBody
	public List<ChannelListDao>ShowChannelList()
	{
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		Criteria cr = ss.createCriteria(ChannelListDao.class);
		List<ChannelListDao>p=cr.list();
		if(p.isEmpty()!=true)
			return p;  
		
		return null;  
   }
	
	@RequestMapping(value="/updateChannelInfo", method=RequestMethod.POST)
	@ResponseBody
	public byte updateChannelInfo(@RequestBody ChannelListDao rec, HttpSession hs)
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
	
	@RequestMapping(value="/deleteChannelInfo", method=RequestMethod.POST)
	@ResponseBody
	public int del1(@RequestBody ChannelListDao rec)
	{
		int flag=0;
	Configuration cfg= new Configuration();
	cfg.configure("hibernate.cfg.xml");
	SessionFactory sf = cfg.buildSessionFactory();
	Session ss = sf.openSession();	    
	Criteria criteria = ss.createCriteria(signup.class);
	List<signup>info =criteria.list();
	for(signup w : info) {
		String updateChannel="";
		if(w.getMychannel().contains(rec.getId()+""));
		{
	       String m[] = w.getMychannel().split(",");		
	       for(String v: m) {
	    	   if(v.equals(rec.getId()+"")){
	    		    continue;
	    	   }
	    	   else {
	    		   updateChannel = updateChannel + v +",";    
	    	   }
	       }
	       w.setMychannel(updateChannel);
	       ss.update(w);     
	       flag=1;
		}
	}
	ss.delete(rec);
	ss.beginTransaction().commit();
	return flag;
	}
	
}
