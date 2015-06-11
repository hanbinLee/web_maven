package com.java.board.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.java.board.dto.BoardDto;
import com.java.board.service.BoardService;

@Controller
public class BoardController{
	final Logger logger=Logger.getLogger(this.getClass().getName());
	
	@Autowired
	private BoardService boardService;

	@RequestMapping(value="/board/test.do", method=RequestMethod.GET)
	public ModelAndView test(HttpServletRequest request, HttpServletResponse response){
		logger.info("test-------------------------");
		ModelAndView mav=new ModelAndView();
		boardService.test(mav);
		return mav;
	}
	
	@RequestMapping(value="/board/write.do", method=RequestMethod.GET)
	public ModelAndView boardWrite(HttpServletRequest request, HttpServletResponse response){
		logger.info("boardWrite-------------------------");
		ModelAndView mav=new ModelAndView();
		mav.addObject("request",request);
		boardService.boardWrite(mav);
		
		return mav;
	}
	
	
	// �Լ� �̸��� ���� �ص� �������. ( ��, �Ű������� ���� �ÿ��� �̸��� Ʋ���� �ۼ�)
	// ���� �̸��̶� GET ������� POST ������� ���� �˾Ƽ� ã�Ƽ� ���ư�
	@RequestMapping(value="/board/write.do", method=RequestMethod.POST)
	public ModelAndView boardWrite(HttpServletRequest request, HttpServletResponse response,BoardDto boardDto){
		logger.info("boardWriteOk-------------------------");
		ModelAndView mav=new ModelAndView();
		mav.addObject("request",request);
		mav.addObject("boardDto",boardDto);
		
		boardService.boardWriteOk(mav);
		
		return mav;
	}
	
	@RequestMapping(value="/board/list.do", method=RequestMethod.GET)
	public ModelAndView boardList(HttpServletRequest request, HttpServletResponse response){
		logger.info("boardList-------------------");
		ModelAndView mav=new ModelAndView();
		mav.addObject("request",request);
	
		
		boardService.boardList(mav);		
		
		return mav;
	}
	
	@RequestMapping(value="/board/read.do", method=RequestMethod.GET)
	public ModelAndView boardRead(HttpServletRequest request, HttpServletResponse response){
		logger.info("boardRead--------------");		
		ModelAndView mav=new ModelAndView();
		mav.addObject("request",request);
		
		boardService.boardRead(mav);
		return mav;
	}
	
	@RequestMapping(value="/board/update.do", method=RequestMethod.GET)
	public ModelAndView boardUpdate(HttpServletRequest request, HttpServletResponse response){
		logger.info("boardUpdate-----------");
		ModelAndView mav=new ModelAndView();
		mav.addObject("request",request);
		
		boardService.boardSelect(mav);
		return mav;
	}
	
	@RequestMapping(value="/board/update.do", method=RequestMethod.POST)
	public ModelAndView boardUpdate(HttpServletRequest request, HttpServletResponse response, BoardDto boardDto){
		logger.info("boardUpdateOk-------");
		ModelAndView mav=new ModelAndView();
		mav.addObject("request",request);
		mav.addObject("boardDto",boardDto);
		
		boardService.boardUpdate(mav);		
		
		return mav;
	}
	
	@RequestMapping(value="/board/delete.do", method=RequestMethod.GET)
	public ModelAndView boardDelete(HttpServletRequest request, HttpServletResponse response){
		logger.info("delete-------");
		ModelAndView mav=new ModelAndView();
		mav.addObject("request",request);
		
		boardService.boardDelete(mav);
		return mav;
	}
	
	@RequestMapping(value="/board/delete.do", method=RequestMethod.POST)
	public ModelAndView boardDelete(HttpServletRequest request, HttpServletResponse response, BoardDto boardDto){
		logger.info("deleteOk---------");
		ModelAndView mav=new ModelAndView();
		mav.addObject("request",request);
		mav.addObject("boardDto",boardDto);		
		boardService.boardDeleteOk(mav);
		
		return mav;
		
	}
}
