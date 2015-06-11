package com.java.guest.service;



import org.springframework.web.servlet.ModelAndView;

public interface GuestService {
	public void testing(ModelAndView mav);
	
	public void guestWrite(ModelAndView mav);
	
	public void guestWriteOk(ModelAndView mav);
	
	public void guestDelete(ModelAndView mav);
	
	public void guestSelect(ModelAndView mav);
	
	public void guestUpdate(ModelAndView mav);
}
