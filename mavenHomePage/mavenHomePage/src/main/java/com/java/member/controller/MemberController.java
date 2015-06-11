package com.java.member.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.java.member.dto.MemberDto;
import com.java.member.service.MemberService;



@Controller
public class MemberController{
	private final Logger logger=Logger.getLogger(this.getClass().getName());//import java.util
	
	@Autowired
	private MemberService memberService;
	
	
	@RequestMapping(value="/member/test.do", method=RequestMethod.GET)
	public ModelAndView test(HttpServletRequest request, HttpServletResponse response){
		logger.info("testing-----------");
		
		ModelAndView mav=new ModelAndView();
		memberService.test(mav);
		
		return mav;
	}
	
	@RequestMapping(value="/member/register.do", method=RequestMethod.GET)
	public String register(HttpServletRequest request, HttpServletResponse response){
		logger.info("register-----------");
		
		//db에 넘어갈 값이 없으므로 바로 register.jsp 로 이동
		return "member/register";
	}
	
	@RequestMapping(value="/member/register.do", method=RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response, MemberDto memberDto){
		logger.info("registerOk-----------");
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("memberDto", memberDto);//jstl에 의해 jsp파일에서 ${} 값이 dto에 저장되어 넘어온다.
															 	 //dto로 안받아오면 request.getParameter로 일일히 dto에 집어넣어야 한다.
		
		memberService.registerOk(mav);
		
		return mav;
	}
	
	@RequestMapping(value="/member/idCheck.do", method=RequestMethod.GET)
	public ModelAndView idCheck(HttpServletRequest request, HttpServletResponse response){
		logger.info("idCheck-----------");
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		
		memberService.idCheck(mav);
		
		return mav;
	}
	
	@RequestMapping(value="/member/zipcode.do", method=RequestMethod.GET)
	public ModelAndView zipcode(HttpServletRequest request, HttpServletResponse response){
		logger.info("zipcode-----------");
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		
		memberService.zipcode(mav);
		
		return mav;
	}	
	
	@RequestMapping(value="/member/login.do", method=RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response){
		logger.info("login-----------");
	
		return "member/login";
	}	
	
	@RequestMapping(value="/member/login.do", method=RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, MemberDto memberDto){
		logger.info("loginOk-----------");
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		
		memberService.loginOk(mav);
		return mav;
	}
	
	@RequestMapping(value="/member/main.do", method=RequestMethod.GET)
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response){
		logger.info("main-----------");
		
		ModelAndView mav=new ModelAndView();
		String id=request.getParameter("id");
		
		mav.addObject("id", id);
		mav.setViewName("member/main");
		//memberService.main(mav);
		return mav;
	}
	
	@RequestMapping(value="/member/logout.do", method=RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response){
		return "member/logout";
	}
	
	@RequestMapping(value="/member/delete.do", method=RequestMethod.GET)
	public String delete(HttpServletRequest request, HttpServletResponse response){		
		return "member/delete";
	}
	
	@RequestMapping(value="/member/delete.do", method=RequestMethod.POST)
	public ModelAndView deleteOk(HttpServletRequest request, HttpServletResponse response){	
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		
		memberService.delete(mav);
		return mav;
	}
	
	@RequestMapping(value="/member/update.do", method=RequestMethod.GET)
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav=new ModelAndView();
		logger.info("update--------------");
		mav.addObject("request", request);
		
		memberService.update(mav);
		
		return mav;
	}
	
	@RequestMapping(value="/member/update.do", method=RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response, MemberDto memberDto){
		ModelAndView mav=new ModelAndView();
		logger.info("updateOk--------------");
		mav.addObject("request", request);
		mav.addObject("memberDto", memberDto);
		
		memberService.updateOk(mav);
		
		return mav;
	}
}
