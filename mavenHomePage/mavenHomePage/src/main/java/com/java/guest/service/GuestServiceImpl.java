package com.java.guest.service;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.java.guest.dao.GuestDao;
import com.java.guest.dto.GuestDto;

@Component
public class GuestServiceImpl implements GuestService{
	final Logger logger=Logger.getLogger(this.getClass().getName());
	
	@Autowired
	private GuestDao guestDao;
	
	@Override
	public void testing(ModelAndView mav) {
		
		
		mav.addObject("msg","하하하");
		mav.addObject("a","허허허");
		mav.setViewName("test/testOk");
	}

	@Override
	public void guestWrite(ModelAndView mav) {
		Map<String,Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		String pageNumber=request.getParameter("pageNumber");
		if(pageNumber==null) pageNumber="1";
		int currentPage=Integer.parseInt(pageNumber);
		logger.info("currentPage:"+currentPage);
		
//		//�쟾泥대젅肄붾뱶�닔, �쁽�옱踰덊샇�쓽 �떆�옉踰덊샇, �걹踰덊샇 -->DB
		
		int count=guestDao.getGuestCount();
		logger.info("count:"+count);
		
		int boardSize=3;
		int startRow=(currentPage-1)*boardSize+1;
		int endRow=currentPage*boardSize;
		logger.info("startRow:"+startRow+",endRow:"+endRow);
		
		List<GuestDto> guestList=null;
		
		
		if(count>0) guestList=guestDao.getGuestList(startRow,endRow);
		logger.info("guestListSize:"+guestList.size());
		logger.info("count:"+count);
		logger.info("boardSize:"+boardSize);
		logger.info("currentPage:"+currentPage);
		
		mav.addObject("guestList",guestList);
		mav.addObject("count",count);
		mav.addObject("boardSize",boardSize);
		mav.addObject("currentPage",currentPage);
		mav.setViewName("guest/write");		
		
		}
		
	

	@Override
	public void guestWriteOk(ModelAndView mav) {
		Map<String,Object> map=mav.getModelMap();
		GuestDto guestDto=(GuestDto)map.get("guestDto");
		guestDto.setWriteDate(new Date());
		
		int check=guestDao.insert(guestDto);
		logger.info("check:"+check);
		
		mav.addObject("check",check);
		mav.setViewName("guest/writeOk");	
		
	}
	
	public void guestDelete(ModelAndView mav) {
		Map<String,Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		int num=Integer.parseInt(request.getParameter("num"));
		int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
		
		logger.info("num:"+num+"\t pageNumber:"+pageNumber);
		
		int check=guestDao.delete(num);
		logger.info("check"+check);
		
		mav.addObject("check",check);
		mav.addObject("pageNumber",pageNumber);
		mav.setViewName("guest/delete");
	}

	@Override
	public void guestSelect(ModelAndView mav) {
		Map<String,Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		int num=Integer.parseInt(request.getParameter("num"));
		int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
		
		GuestDto guestDto=guestDao.select(num);
		logger.info("guestDto:"+guestDto);
		
		mav.addObject("guest",guestDto);	//write.jsp
		mav.addObject("pageNumber",pageNumber);
		mav.setViewName("guest/update");	
		
	}

	@Override
	public void guestUpdate(ModelAndView mav) {
		Map<String,Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		GuestDto guestDto=(GuestDto)map.get("guestDto");		
		
		int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
		
		int check=guestDao.update(guestDto);
		logger.info("check:"+check);
		mav.addObject("check",check);
		mav.addObject("guest",guestDto);
		mav.addObject("pageNumber",pageNumber);		
		mav.setViewName("guest/updateOk");
	}
	
	
		
	
	
	
}
