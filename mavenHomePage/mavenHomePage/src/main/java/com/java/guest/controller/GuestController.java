package com.java.guest.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.java.guest.dto.GuestDto;
import com.java.guest.service.GuestService;

@Controller
public class GuestController{
	final Logger logger=Logger.getLogger(this.getClass().getName());
	
	@Autowired	//setMethod	
	private GuestService guestService;

	@RequestMapping(value="/guest/test.do", method=RequestMethod.GET)
	public ModelAndView test(HttpServletRequest request,HttpServletResponse response){
		logger.info("test----------");		
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("request",request);
		guestService.testing(mav);	
		
		return mav;
	}
	
	@RequestMapping(value="/guest/write.do", method=RequestMethod.GET)
	public ModelAndView guestWrite(HttpServletRequest request,HttpServletResponse response){
		logger.info("guestWrite----------");
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("request",request);		
		
		guestService.guestWrite(mav);
		return mav;
	}
	
	@RequestMapping(value="/guest/writeOk.do", method=RequestMethod.POST)
	public ModelAndView guestWriteOk(HttpServletRequest request,HttpServletResponse response,GuestDto guestDto){
		
		logger.info("guestWriteOk-----------");
		logger.info("guestDto:"+guestDto+","+guestDto.getPassword());
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("guestDto",guestDto);
		guestService.guestWriteOk(mav);
		return mav;
	}
	
	@RequestMapping(value="/guest/delete.do", method=RequestMethod.GET)
	public ModelAndView guestDelete(HttpServletRequest request,HttpServletResponse response){
		logger.info("guestDelete------------");
		ModelAndView mav=new ModelAndView();
		mav.addObject("request",request);
		
		guestService.guestDelete(mav);
		return mav;
	}
	
	@RequestMapping(value="/guest/update.do", method=RequestMethod.GET)
	public ModelAndView guestUpdate(HttpServletRequest request,HttpServletResponse response){
		logger.info("guestUpdate------");
		ModelAndView mav=new ModelAndView();
		mav.addObject("request",request);
		guestService.guestSelect(mav);
		return mav;
	}
	
	@RequestMapping(value="/guest/updateOk.do", method=RequestMethod.POST)
	public ModelAndView guestUpdateOk(HttpServletRequest request,HttpServletResponse response,GuestDto guestDto){
		logger.info("guestUpdateOk-----");
		ModelAndView mav=new ModelAndView();
		mav.addObject("guestDto",guestDto);
		mav.addObject("request",request);		//�럹�씠吏�踰덊샇�룄 �엳湲곕븣臾몄뿉
		guestService.guestUpdate(mav);
		String pageNumber=request.getParameter("pageNumber");
		logger.info("pageNumber:"+pageNumber);
		return mav;
	}
}

	
