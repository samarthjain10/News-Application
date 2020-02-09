package com.newshunt.channelmanagement.controller;

import com.newshunt.daomodel.ChannelListDao;
import com.newshunt.daomodel.signup;

import java.util.ArrayList;
import java.util.List;

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
@Controller
public class Mychannels
{

	@RequestMapping("/myChannel")
	public String mychannel() 
	{
		return "myChannel";
	}
	
	@ResponseBody
	@RequestMapping(value= "/myChannelList", method=RequestMethod.POST)
	public List<ChannelListDao>myChannelList(HttpSession hs)
	{
		String un = hs.getAttribute("un").toString();
		Configuration cfg= new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		Criteria cc = ss.createCriteria(signup.class);
		cc.add(Restrictions.eq("email", un));
		signup rec = (signup)cc.uniqueResult();	
		String ch = rec.getMychannel();
		String m[] = ch.split(",");
		ArrayList<String>al = new ArrayList();   
		for(String z:m)
		{
			al.add(z);
		}   
		Criteria c = ss.createCriteria(ChannelListDao.class);
		c.add(Restrictions.in("id", al));
		List<ChannelListDao>subscribedlist = c.list();
		if(subscribedlist.isEmpty()!=true) 
			return subscribedlist;  
		else
			return null;		
	}	
	

	@RequestMapping(value="/unsubscribe", method = RequestMethod.POST)
	@ResponseBody
	public byte subscribe(@RequestBody signup r , HttpSession hs)
	{
    	try
    	{
    		String un = hs.getAttribute("un").toString();
    		Configuration cfg= new Configuration();
    		cfg.configure("hibernate.cfg.xml");
    		SessionFactory sf = cfg.buildSessionFactory();
    		Session ss = sf.openSession();
    		Criteria criteria = ss.createCriteria(signup.class);
    		criteria.add(Restrictions.eq("email", un));
    		signup rec = (signup)criteria.uniqueResult();	       
    		rec.setMychannel(r.getMychannel());
    		ss.update(rec);
    		ss.beginTransaction().commit();
    		return 1; 
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return 0;
	}
		
}
