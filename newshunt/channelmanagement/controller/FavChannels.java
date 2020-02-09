package com.newshunt.channelmanagement.controller;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
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
import com.newshunt.dto.ChannelNews;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

@Controller
public class FavChannels 
{
	@RequestMapping("/favChannel")
	public String mychannel() 
	{
		return "favChannel";
	}
	
	@RequestMapping(value="/fav", method = RequestMethod.POST)
	@ResponseBody
	public byte fav(@RequestBody signup r , HttpSession session)
	{
		byte a=0;
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
    		rec.setFavchannel(r.getFavchannel());
    		ss.update(rec);
    		ss.beginTransaction().commit();
    		a=1;
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
		return a;
	}
	
	@RequestMapping(value = "/myfav" , method = RequestMethod.POST)
	@ResponseBody
	public List<ChannelNews> my(HttpSession session) 
	{	  
		try
		{
			String email=session.getAttribute("un").toString();
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			SessionFactory ss = cfg.buildSessionFactory();
	        Session s = ss.openSession();
	        Criteria cr=s.createCriteria(signup.class);
	        cr.add(Restrictions.eq("email", email));
	        signup rec = (signup)cr.uniqueResult();	        
			String channel=rec.getFavchannel();
			String m[]=channel.split(",");
			ArrayList <String> al=new ArrayList<String>();
			for(String z:m)
			{
				al.add(z);
			}
			cr=s.createCriteria(ChannelListDao.class);
			cr.add(Restrictions.in("id", al));
			List<ChannelListDao> ls=cr.list();			
			LinkedList<ChannelNews> cn = new LinkedList<ChannelNews>(); 
			for(ChannelListDao ch : ls)
			{
				URL url = new URL(ch.getUrl());
				SyndFeedInput sf = new SyndFeedInput();
				SyndFeed ff = sf.build(new XmlReader(url));
				List list = ff.getEntries();
				for(int j=0;j<2;j++)
				{
					ChannelNews sc = new ChannelNews(); 
					String title = ((SyndEntry) list.get(j)).getTitle();
					sc.setTitle(title);
					String link = ((SyndEntry) list.get(j)).getLink();
					sc.setLink(link);
					Date D = ((SyndEntry) list.get(j)).getPublishedDate();
					Date date = Calendar.getInstance().getTime();  
					DateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");  
					String strDate = dateFormat.format(date);  
					sc.setDate(strDate);
					sc.setChannelName(ch.getTitle());
					cn.add(sc);
				}				  
			}
			return cn;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null; 
	}	
}
