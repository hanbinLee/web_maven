package com.java.member.service;

import org.springframework.web.servlet.ModelAndView;

public interface MemberService {
	public void test(ModelAndView mav);
	
	public void registerOk(ModelAndView mav);
	
	public void idCheck(ModelAndView mav);
	
	public void zipcode(ModelAndView mav);
	
	public void loginOk(ModelAndView mav);
	
	public void main(ModelAndView mav);
	
	public void delete(ModelAndView mav);
	
	public void update(ModelAndView mav);
	
	public void updateOk(ModelAndView mav);
}
