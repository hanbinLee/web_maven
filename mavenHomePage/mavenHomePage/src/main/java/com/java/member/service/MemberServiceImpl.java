package com.java.member.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.java.member.dao.MemberDao;
import com.java.member.dto.MemberDto;
import com.java.member.dto.MemberZipcodeDto;


@Component
public class MemberServiceImpl implements MemberService{
	private final Logger logger=Logger.getLogger(this.getClass().getName());//import java.util
	
	@Autowired
	private MemberDao memberDao;
	
	@Override
	public void test(ModelAndView mav) {
		mav.addObject("msg", "member 시작입니다.");
		mav.setViewName("test/testing");		
	}

	@Override
	public void registerOk(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		
		MemberDto memberDto=(MemberDto) map.get("memberDto");
		memberDto.setMemberLevel("BA");
		memberDto.setRegisterDate(new Date());
		
		int check=memberDao.insert(memberDto);
		logger.info("registerOk check:" + check);
		
		mav.addObject("check", check);
		mav.setViewName("member/registerOk");
	}

	@Override
	public void idCheck(ModelAndView mav) {
		Map<String, Object> map=mav.getModel();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		String id=request.getParameter("id");
		logger.info("idCheck id:" + id);
		
		int check=memberDao.idCheck(id);
		logger.info("idCheck check:" + check);
		
		mav.addObject("id", id);
		mav.addObject("check", check);
		mav.setViewName("member/idCheck");
	}

	@Override
	public void zipcode(ModelAndView mav) {
		Map<String, Object> map =mav.getModel();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		String dong=request.getParameter("dong");
		logger.info("dong:" + dong);
		
		if(dong!=null){
			List<MemberZipcodeDto> zipcodeDtoList=memberDao.zipcode(dong);
			logger.info("zipcodeDto:"  +zipcodeDtoList.size());
			mav.addObject("zipcodeDtoList", zipcodeDtoList);
		}
		mav.setViewName("member/zipcode");
	}

	@Override
	public void loginOk(ModelAndView mav) {
		Map<String, Object> map=mav.getModel();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		logger.info("id:" + id + "password:" + password);
		
		String memberLevel=memberDao.login(id, password);
		logger.info("memberLevel:" + memberLevel);
		
		mav.addObject("id", id);
		mav.addObject("memberLevel", memberLevel);		
		mav.setViewName("member/loginOk");
	}

	@Override
	public void main(ModelAndView mav) {
		mav.setViewName("member/main");
		
	}

	@Override
	public void delete(ModelAndView mav) {
		Map<String, Object> map=mav.getModel();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		logger.info(id+"\t" + password);
		
		int check=memberDao.delete(id, password);
		logger.info("check:" + check);
		
		mav.addObject("check", check);
		mav.setViewName("member/deleteOk");
	}

	@Override
	public void update(ModelAndView mav) {
		Map<String, Object> map=mav.getModel();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		String id=request.getParameter("id");
		logger.info("id:" + id);
		
		MemberDto memberDto=memberDao.updateSelect(id);
		logger.info("memberDto:" + memberDto);
		
		mav.addObject("member", memberDto);
		mav.setViewName("member/update");
	}

	@Override
	public void updateOk(ModelAndView mav) {
		Map<String, Object> map=mav.getModel();
		
		MemberDto memberDto=(MemberDto)map.get("memberDto");
		
		int check=memberDao.update(memberDto);
		logger.info("check:" + check);
		
		mav.addObject("check", check);
		mav.setViewName("member/updateOk");
	}
}
