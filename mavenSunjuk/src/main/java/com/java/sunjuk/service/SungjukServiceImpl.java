package com.java.sunjuk.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.java.sunjuk.dao.SungjukDao;
import com.java.sunjuk.dto.Sungjuk;

@Component
public class SungjukServiceImpl implements SungjukService{
	@Autowired
	private SungjukDao dao;
	@Override
	public void write(ModelAndView mav) {
		Map<String , Object> map = mav.getModelMap();
		Sungjuk sung = (Sungjuk) map.get("sungjuk");
		sung.setTotal(sung.getEng()+sung.getKor()+sung.getMath());
		sung.setAverage(sung.getTotal()/3.0f);
		int check = dao.write(sung);
		
		mav.addObject("check" , check);
	}
	
}
