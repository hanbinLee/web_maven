package com.java.fileboard.service;

import org.springframework.web.servlet.ModelAndView;

public interface FileBoardService {
	public void write(ModelAndView mav);
	public void writeOk(ModelAndView mav);
	public void list(ModelAndView mav);
	public void read(ModelAndView mav);
	public void fileDown(ModelAndView mav);
	public void delete(ModelAndView mav);
	public void update(ModelAndView mav);
	public void updateOk(ModelAndView mav);
}
