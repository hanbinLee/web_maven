package com.java.addr.service;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.java.addr.dao.Dao;
import com.java.addr.dto.Address;

@Component
public class ServiceImp implements Service{
	@Autowired
	private Dao dao;
	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Override
	public void write(ModelAndView mav) {
		logger.info("Service Write");
		
		Map<String , Object> map = mav.getModelMap();
		Address address = (Address) map.get("address");
		
		int check = dao.write(address);
		mav.setViewName("addr/writeOk");
		mav.addObject("check" , check);
	}
	@Override
	public void selectOk(ModelAndView mav) {
		logger.info("Service SelectOk");
		
		Map<String , Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		List<Address> list = dao.select(request.getParameter("name"));
		mav.addObject("addr" , list);
	}
	@Override
	public void delete(ModelAndView mav) {
		logger.info("Service Delete");
		
		Map<String , Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int check = dao.delete(request.getParameter("name"));
		
		mav.addObject("check" , check);
	}
	@Override
	public void update(ModelAndView mav) {
		logger.info("Service Update");	
		Address address = null;	
		Map<String , Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		if(request.getParameter("name") !=null) address = dao.search(request.getParameter("name"));
		
		mav.addObject("address" , address);
	}
	@Override
	public void updateOk(ModelAndView mav) {
		logger.info("Service UpdateOk");
		Map<String , Object> map = mav.getModelMap();
		
		Address address = (Address) map.get("address");
		int check = dao.update(address);
		mav.addObject("check" , check);
	}
	@Override
	public void findUser(ModelAndView mav) {
		logger.info("Service FindUser");
		Map<String , Object> map = mav.getModelMap();
		
		String name = (String) map.get("name");
		Address address = dao.selectName(name);
		mav.addObject("address" , address);
	}
	
	
	
}
