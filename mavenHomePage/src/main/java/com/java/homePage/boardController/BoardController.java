package com.java.homePage.boardController;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.java.homePage.boardDto.BoardDto;
import com.java.homePage.boardService.BoardService;

@Controller
public class BoardController{
	@Autowired
	private BoardService boardService;
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	@RequestMapping(value="/board/test.do" , method=RequestMethod.GET)
	public ModelAndView test(HttpServletRequest request , HttpServletResponse response){
		logger.info("-----------------------Controller test--------------------------");
		
		ModelAndView mav = new ModelAndView();
		boardService.test(mav);
		
		return mav;
	}
	
	@RequestMapping(value="/board/write.do" , method=RequestMethod.GET)
	public ModelAndView boardWrite(HttpServletRequest request , HttpServletResponse response){
		logger.info("-----------------------Controller Write--------------------------");
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("request" , request);
		boardService.boardWrite(mav);
		
		return mav;
	}
	
	@RequestMapping(value="/board/writeOk.do" , method=RequestMethod.POST)
	public ModelAndView boardWriteOk(HttpServletRequest request , BoardDto boardDto){
		//BoardDto �� Jsp:useBean���·� �Ѿ���⶧���� ��밡��
		logger.info("-----------------------Controller WriteOk--------------------------");
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("request" , request);
		mav.addObject("boardDto" , boardDto);
		boardService.boardWriteOk(mav);
		
		logger.info(mav.getViewName());
		return mav;
	}
	
	@RequestMapping(value="/board/list.do" , method=RequestMethod.GET)
	public ModelAndView boardList(HttpServletRequest request , HttpServletResponse response){
		logger.info("-----------------------Controller boardList--------------------------");
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("request" , request);
		boardService.boardList(mav);
		return mav;
	}
	
	@RequestMapping(value="/board/read.do" , method=RequestMethod.GET)
	public ModelAndView boardRead(HttpServletRequest request , HttpServletResponse response){
		logger.info("-----------------------Controller boardRead--------------------------");
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("request" , request);
		boardService.boardRead(mav);
		return mav;
	}
	
	@RequestMapping(value="/board/delete.do" , method=RequestMethod.GET)
	public ModelAndView boardDelete(HttpServletRequest request , HttpServletResponse response){
		logger.info("-----------------------Controller delete--------------------------");
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("request" , request);
		boardService.boardDelete(mav);
		return mav;
	}
	
	@RequestMapping(value="/board/deleteOk.do" ,method=RequestMethod.POST)
	public ModelAndView boardDeleteOk(HttpServletRequest request , HttpServletResponse response){
		logger.info("-----------------------Controller deleteOk--------------------------");
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("request" , request);
		boardService.boardDeleteOk(mav);
		return mav;
	}
	
	@RequestMapping(value="/board/update.do" , method=RequestMethod.GET)
	public ModelAndView boardUpdate(HttpServletRequest request , HttpServletResponse response){
		logger.info("-----------------------Controller update--------------------------");
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("request" , request);
		boardService.boardUpdate(mav);
		
		return mav;
	}
	
	@RequestMapping(value="/board/updateOk.do" , method=RequestMethod.POST)
	public ModelAndView boardUpdateOk(HttpServletRequest request , HttpServletResponse response , BoardDto boardDto){
		logger.info("-----------------------Controller UpdateOk--------------------------");
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("request" , request);
		mav.addObject("boardDto" , boardDto);
		boardService.boardUpdateOk(mav);
		
		return mav;
	}
}
