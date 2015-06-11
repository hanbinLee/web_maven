package com.java.addr.service;

import org.springframework.web.servlet.ModelAndView;

public interface Service {

	public void write(ModelAndView mav);

	public void selectOk(ModelAndView mav);

	public void delete(ModelAndView mav);

	public void update(ModelAndView mav);

	public void updateOk(ModelAndView mav);

	public void findUser(ModelAndView name);

}
