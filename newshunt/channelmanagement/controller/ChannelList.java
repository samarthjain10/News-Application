package com.newshunt.channelmanagement.controller;

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

import com.newshunt.daomodel.ChannelListDao;
import com.newshunt.daomodel.signup;
@Controller
public class ChannelList
{
	
	@RequestMapping("/addChannel")
	public String addChannel() 
	{
		return "addChannels";
	}
	
	@ResponseBody
	@RequestMapping(value= "/channellist", method=RequestMethod.POST)
	public List<ChannelListDao> channel(HttpSession hs)
	{
		String un = hs.getAttribute("un").toString();
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		Criteria cr = ss.createCriteria(signup.class);
		cr.add(Restrictions.eq("email", un));
		signup rec = (signup)cr.uniqueResult();
		String z = rec.getMychannel();
		if(z!=null)
		{
			String m[] = z.split(",");
			ArrayList<String> al = new ArrayList<String>();
			for(String p:m)
			{
				al.add(p);
			}
			Criteria criteria = ss.createCriteria(ChannelListDao.class);
			criteria.add(Restrictions.not(Restrictions.in("id", al)));
			List<ChannelListDao>p=criteria.list();
  		   	if(p.isEmpty()!=true)
  		   		return p;  
  		   	else
  		   		return null;
		}
		else
		{
			cr = ss.createCriteria(ChannelListDao.class);
			List<ChannelListDao>p=cr.list();
			if(p.isEmpty()!=true) 
				return p;  
    		else
    		    return null;   
	    }	      
	}
		
	@RequestMapping(value="/subscribe", method = RequestMethod.POST)
	@ResponseBody
	public byte suscribe(@RequestBody signup r , HttpSession session)
	{
		try
    	{
			String un = session.getAttribute("un").toString();
	    	Configuration cfg= new Configuration();
	    	cfg.configure("hibernate.cfg.xml");
	    	SessionFactory sf = cfg.buildSessionFactory();
	    	Session ss = sf.openSession();
	    	Criteria criteria = ss.createCriteria(signup.class);
	    	criteria.add(Restrictions.eq("email", un));
	    	signup rec = (signup)criteria.uniqueResult();	 
	    	String z = rec.getMychannel();
	    	if(z!=null)
	    	{
	    		String x = r.getMychannel();
	    		String n = z.concat(x);
	    		rec.setMychannel(n);
	    	}
	    	else
	    	{
	    		rec.setMychannel(r.getMychannel());
	    	}
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