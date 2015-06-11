package com.java.homepage.fileboard.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.java.homepage.fileboard.dto.FileBoardDto;
import com.java.homepage.fileboard.service.FileBoardService;



@Controller
public class FileBoardController {
	private final Logger logger=Logger.getLogger(this.getClass().getName());
	
	@Autowired
	private FileBoardService boardService;
	
	@RequestMapping(value="/fileBoard/test.do", method=RequestMethod.GET)
	public String test(){
		logger.info("testing----------------------------");
		return "test/testing";
	}
	
	@RequestMapping(value="/fileBoard/write.do", method=RequestMethod.GET)
	public ModelAndView write(HttpServletRequest request){
		logger.info("write----------------------------");
		ModelAndView mav=new ModelAndView();
		
		mav.addObject("request", request);
		boardService.write(mav);
		return mav;
	}
	
	@RequestMapping(value="/fileBoard/write.do", method=RequestMethod.POST)
	public ModelAndView write(MultipartHttpServletRequest request, FileBoardDto fileBoardDto){
		logger.info("writeOk post----------------------------");
		ModelAndView mav=new ModelAndView();
		
		mav.addObject("request", request);
		mav.addObject("fileBoardDto", fileBoardDto);
		boardService.writeOk(mav);
		
		return mav;
	}
	
	@RequestMapping(value="/fileBoard/list.do", method=RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request){
		logger.info("list-----------");
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		boardService.list(mav);
		
		return mav;
	}
	
	@RequestMapping(value="/fileBoard/read.do", method=RequestMethod.GET)
	public ModelAndView read(HttpServletRequest request){
		logger.info("read-----------");
		
		//boardNumber&pageNumber
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		boardService.read(mav);
		
		return mav;
	}
	
	@RequestMapping(value="/fileBoard/downLoad.do", method=RequestMethod.GET)
	public ModelAndView fileDown(HttpServletRequest request, HttpServletResponse response){
		logger.info("fileDown-----------");
		//JspWriter w = (JspWriter) request.getAttribute(request.getParameter("out"));
		//System.out.println(w);
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("response", response);
		boardService.fileDown(mav);

		return null;
	}
	
	@RequestMapping(value="/fileBoard/delete.do", method=RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request){
		logger.info("delete-----------");
		
		ModelAndView mav=new ModelAndView();
		
		int boardNumber=Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
		
		mav.addObject("boardNumber", boardNumber);
		mav.addObject("pageNumber", pageNumber);
		mav.setViewName("fileBoard/delete");
		
		return mav;
	}
	
	@RequestMapping(value="/fileBoard/delete.do", method=RequestMethod.POST)
	public ModelAndView deleteOk(HttpServletRequest request){
		logger.info("deleteOk-----------");
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		boardService.delete(mav);
		
		return mav;
	}
	
	@RequestMapping(value="fileBoard/update.do", method=RequestMethod.GET)
	public ModelAndView update(HttpServletRequest request){
		logger.info("update-----------");
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		boardService.update(mav);
		
		return mav;
	}
	
	@RequestMapping(value="fileBoard/update.do", method=RequestMethod.POST)
	public ModelAndView updateOk(MultipartHttpServletRequest request, FileBoardDto fileBoardDto){
		logger.info("updateOk-----------");
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("fileBoardDto", fileBoardDto);
		boardService.updateOk(mav);
		
		return mav;
	}
}
