package com.java.addr.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.java.addr.dto.Address;
import com.java.addr.service.Service;

@org.springframework.stereotype.Controller
public class Controller{
	private static final Logger logger = LoggerFactory.getLogger(Controller.class);
	@Autowired
	private Service service;
	
	@RequestMapping(value="/write.do" , method=RequestMethod.GET)
	public String write(HttpServletRequest request , HttpServletResponse response){
		return "addr/write";
	}
	
	@RequestMapping(value="/writeOk.do" , method=RequestMethod.POST)
	public ModelAndView writeOk(HttpServletRequest request , HttpServletResponse response , Address address){
		logger.info("writeOk");
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("address" , address);
		service.write(mav);
		
		return mav;
	}
	
	@RequestMapping(value="/select.do" , method=RequestMethod.GET)
	public String select(HttpServletRequest request , HttpServletResponse response){
		return "addr/select";
	}
	
	@RequestMapping(value="/selectOk.do" , method=RequestMethod.POST)
	public ModelAndView selectOk(HttpServletRequest request , HttpServletResponse response){
		logger.info("selectOk");
		
		ModelAndView mav = new ModelAndView("addr/selectOk");
		mav.addObject("request" , request);
		service.selectOk(mav);
		
		return mav;
	}
	
	@RequestMapping(value="/delete.do" , method=RequestMethod.GET)
	public String delete(HttpServletRequest request , HttpServletResponse response){
		return "addr/delete";
	}
	
	@RequestMapping(value="/deleteOk.do" , method=RequestMethod.GET)
	public ModelAndView deleteOk(HttpServletRequest request , HttpServletResponse response){
		ModelAndView mav = new ModelAndView("addr/deleteOk");
		mav.addObject("request" ,request);
		service.delete(mav);
		
		return mav;
	}
	
	@RequestMapping(value="/update.do" , method=RequestMethod.GET)
	public String update(){
		return "addr/update";
	}
	
	@RequestMapping(value="/update.do" , method=RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("addr/update");
		String name = request.getParameter("name"); 
		mav.addObject("name" , name);
		logger.info("name : " + name);
		service.findUser(mav);
		
		return mav;
	}
	
	@RequestMapping(value="/updateOk.do" , method=RequestMethod.POST)
	public ModelAndView updateOk(HttpServletRequest request , HttpServletResponse response , Address address){
		ModelAndView mav = new ModelAndView("addr/updateOk");
		mav.addObject("address" , address);
		service.updateOk(mav);
		
		return mav;
	}
}
