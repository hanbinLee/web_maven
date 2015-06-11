package com.java.board.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.java.board.dao.BoardDao;
import com.java.board.dto.BoardDto;

@Component
public class BoardServiceImpl implements BoardService {
	final Logger logger=Logger.getLogger(this.getClass().getName());
	
	@Autowired
	private BoardDao boardDao;
	
	@Override
	public void test(ModelAndView mav) {
		mav.addObject("msg","하이");
		
		mav.setViewName("test/test");
		
	}


	@Override
	public void boardWrite(ModelAndView mav) {
		Map<String,Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		//�떟湲��씪�븣,�븘�땺�븣
		//Root 0 1 0 0
		int boardNumber=0;
		int groupNumber=1;
		int sequenceNumber=0;
		int sequenceLevel=0;
		
		//�떟湲�
		if(request.getParameter("boardNumber")!=null){
			boardNumber=Integer.parseInt(request.getParameter("boardNumber"));
			groupNumber=Integer.parseInt(request.getParameter("groupNumber"));
			sequenceNumber=Integer.parseInt(request.getParameter("sequenceNumber"));
			sequenceLevel=Integer.parseInt(request.getParameter("sequenceLevel"));
			
		}
		
		logger.info("boardNumber:"+boardNumber);
		logger.info("groupNumber:"+groupNumber);
		logger.info("sequenceNumber:"+sequenceNumber);
		logger.info("sequenceLevel:"+sequenceLevel);
		
		mav.addObject("boardNumber",boardNumber);
		mav.addObject("groupNumber",groupNumber);
		mav.addObject("sequenceNumber",sequenceNumber);
		mav.addObject("sequenceLevel",sequenceLevel);
		
		mav.setViewName("board/write");
		
	}
	
	public void boardWriteOk(ModelAndView mav){
		Map<String,Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		BoardDto boardDto=(BoardDto)map.get("boardDto");
		
		boardWriteNumber(boardDto);
		boardDto.setWriteDate(new Date());
		boardDto.setReadCount(0);
		boardDto.setIp(request.getRemoteAddr());	
		
		int check=boardDao.insert(boardDto);
		logger.info("check:"+check);
		
//		mav.addObject("boardNumber",boardDto.getBoardNumber());
//		mav.addObject("groupNumber",boardDto.getGroupNumber());
//		mav.addObject("sequenceNumber",boardDto.getSequenceNumber());
//		mav.addObject("sequenceLevel",boardDto.getSequenceLevel());
		mav.addObject("check",check);
		
		mav.setViewName("board/writeOk");
		
	}
	
	public void boardWriteNumber(BoardDto boardDto){
		int boardNumber=boardDto.getBoardNumber();
		int groupNumber=boardDto.getGroupNumber();
		int sequenceNumber=boardDto.getSequenceNumber();
		int sequenceLevel=boardDto.getSequenceLevel();
		
		logger.info("boardNumber:"+boardNumber);
		logger.info("groupNumber:"+groupNumber);
		logger.info("sequenceNumber:"+sequenceNumber);
		logger.info("sequenceLevel:"+sequenceLevel);
		
		int max=0;
		if(boardNumber==0){
			//ROOT
			max=boardDao.boardGroupNumberMax();
			if(max!=0){
				max=max+1;
			}else{
				max=boardDto.getGroupNumber();    //1
			}
			
			logger.info("max:"+max);
			
			groupNumber=max;
			sequenceNumber=boardDto.getSequenceNumber();
			sequenceLevel=boardDto.getSequenceLevel();
		}else{
			//�떟湲�
			HashMap<String,Integer>hMap=new HashMap<String,Integer>();
			hMap.put("groupNumber", groupNumber);
			hMap.put("sequenceNumber", sequenceNumber);
			
			boardDao.boardGroupNumberUpdate(hMap);
			sequenceNumber=sequenceNumber+1;
			sequenceLevel=sequenceLevel+1;
		}
		
		boardDto.setGroupNumber(groupNumber);
		boardDto.setSequenceLevel(sequenceLevel);
		boardDto.setSequenceNumber(sequenceNumber);
		
	}


	@Override
	public void boardList(ModelAndView mav) {
		Map<String,Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		String pageNumber=request.getParameter("pageNumber");
		if(pageNumber==null) pageNumber="1";
		
		int boardSize=10;	//�븳�럹�씠吏��뿉 10媛�
		
		int currentPage=Integer.parseInt(pageNumber);
		int startRow=(currentPage-1)*boardSize+1;
		int endRow=currentPage*boardSize;
		
		int count=boardDao.getBoardCount();
		logger.info("count:"+count);
		logger.info("currentPage"+currentPage);
		logger.info("startRow"+startRow);
		logger.info("endRow"+endRow);
		
		List<BoardDto> boardList=null;
		
		if(count>0){
			boardList=boardDao.getBoardList(startRow,endRow);
		}
		logger.info("boardListSize:"+boardList.size());
		
		mav.addObject("boardList",boardList);
		mav.addObject("count",count);
		mav.addObject("boardList",boardList);
		mav.addObject("boardSize",boardSize);
		mav.addObject("currentPage",currentPage);
		
		mav.setViewName("board/list");
		
	}


	@Override
	public void boardRead(ModelAndView mav) {
		Map<String,Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest)map.get("request");
		
		int boardNumber=Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
		
		
		logger.info("boardnumber:"+boardNumber);
		logger.info("pageNumber:"+pageNumber);
		
		BoardDto boardDto=boardDao.boardRead(boardNumber);
		mav.addObject("board",boardDto);
		mav.addObject("pageNumber",pageNumber);
		mav.setViewName("board/read");
		logger.info("boardDto:"+boardDto);		
	}

	@Override
	public void boardSelect(ModelAndView mav) {
		Map<String,Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		int boardNumber=Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
		
		logger.info("boardnumber:"+boardNumber);
		logger.info("pageNumber:"+pageNumber);
		
		BoardDto boardDto=boardDao.boardSelect(boardNumber);
		mav.addObject("board",boardDto);
		mav.addObject("pageNumber",pageNumber);
		mav.setViewName("board/update");
		logger.info("boardDto:"+boardDto);
		
	}


	@Override
	public void boardUpdate(ModelAndView mav) {
		Map<String,Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		BoardDto boardDto=(BoardDto) map.get("boardDto");
		
		int boardNumber=Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
		
		int check=boardDao.boardUpdate(boardDto);
		logger.info("check:"+check);
		mav.addObject("boardNumber",boardNumber);
		mav.addObject("pageNumber",pageNumber);	
		mav.addObject("board",boardDto);
		mav.addObject("check",check);
		mav.setViewName("board/updateOk");
		
		
	}


	@Override
	public void boardDelete(ModelAndView mav) {
		Map<String,Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		int boardNumber=Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
		
		logger.info("boardNumber"+boardNumber);
		logger.info("pageNumber"+pageNumber);
		
		mav.addObject("boardNumber",boardNumber);
		mav.addObject("pageNumber",pageNumber);
		
		mav.setViewName("board/delete");
		
		
	}


	@Override
	public void boardDeleteOk(ModelAndView mav) {
		Map<String,Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		BoardDto boardDto=(BoardDto)map.get("boardDto");
		int boardNumber=Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));		
		int check=boardDao.boardDeleteOk(boardDto);
		
		mav.addObject("boardNumber",boardNumber);
		mav.addObject("pageNumber",pageNumber);
		mav.addObject("check",check);
		mav.addObject("board",boardDto);
		mav.setViewName("board/deleteOk");
		
	}
	
	

}
