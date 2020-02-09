package com.newshunt.menu.controller;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newshunt.daomodel.MenuDao;
import com.newshunt.daomodel.signup;

@Controller
public class MenuManagement
{	
	@RequestMapping("/home")
	public String home()
	{
		return "home";
	}
	
	@RequestMapping(value = "/userMenuList" , method = RequestMethod.POST)
    @ResponseBody
	public List<MenuDao>userMenuList()
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

}