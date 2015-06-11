package com.java.homePage.boardService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.java.homePage.boardDao.BoardDao;
import com.java.homePage.boardDto.BoardDto;

@Component
public class BoardServiceImpl implements BoardService {
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	@Autowired
	private BoardDao boardDao;
	
	@Override
	public void test(ModelAndView mav) {
		logger.info("--------------------Service Test------------------");
		
		mav.addObject("test" , "test");
		//InternalResourceViewResolver�����ϸ� �̷���
		//mav.setViewName("/WEB-INF/test/test.jsp");
		mav.setViewName("test/test");
	}
	
	@Override
	public void boardWrite(ModelAndView mav) {
		logger.info("--------------------Service Write------------------");
		Map<String , Object> map = mav.getModel();
		
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		//Root ���϶� 0 1 0 0
		int boardNumber = 0;
		int groupNumber = 1;
		int sequenceNumber = 0;
		int sequenceLevel = 0;
		
		//���
		if(request.getParameter("boardNumber") != null){
			boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
			groupNumber = Integer.parseInt(request.getParameter("groupNumber"));
			sequenceNumber = Integer.parseInt(request.getParameter("sequenceNumber"));
			sequenceLevel = Integer.parseInt(request.getParameter("sequenceLevel"));
		}
		
		logger.warning("boardNumber : " + boardNumber);
		logger.warning("groupNumber : " + groupNumber);
		logger.warning("sequenceNumber : " + sequenceNumber);
		logger.warning("sequenceLevel : " + sequenceLevel);
		
		mav.addObject("boardNumber" , boardNumber);
		mav.addObject("groupNumber" , groupNumber);
		mav.addObject("sequenceNumber" , sequenceNumber);
		mav.addObject("sequenceLevel" , sequenceLevel);
		mav.setViewName("board/write");
	}
	
	@Override
	public void boardWriteOk(ModelAndView mav) {
		Map<String ,Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		BoardDto boardDto = (BoardDto) map.get("boardDto");
		
		this.boardWriteNumber(boardDto);
		
		boardDto.setWriteDate(new Date());
		boardDto.setReadCount(0);
		boardDto.setIp(request.getRemoteAddr());
		
		int check = boardDao.boardInsert(boardDto);
		logger.info("check" + check);
		mav.addObject("check" , check);
		
//		logger.severe(mav.getViewName());
//		mav.addObject("boardNumber" , boardDto.getBoardNumber());
//		mav.addObject("groupNumber" , boardDto.getGroupNumber());
//		mav.addObject("sequenceNumber" , boardDto.getSequenceNumber());
//		mav.addObject("sequenceLevel" , boardDto.getSequenceLevel());
	}
	
	public void boardWriteNumber(BoardDto boardDto){
		int boardNumber = boardDto.getBoardNumber();
		int groupNumber = boardDto.getGroupNumber();
		int sequenceNumber = boardDto.getSequenceNumber();
		int sequenceLevel = boardDto.getSequenceLevel();
		
		logger.warning("boardNumber : " + boardNumber);
		logger.warning("groupNumber : " + groupNumber);
		logger.warning("sequenceNumber : " + sequenceNumber);
		logger.warning("sequenceLevel : " + sequenceLevel);
		
		int max = 0;
		if(boardNumber==0){
			//root
			max = boardDao.boardGroupNumberMax();
			if(max != 0){
				max+=1;
			}else{
				max = boardDto.getGroupNumber(); // 1
			}
			
			logger.info("max : " + max);
			
			groupNumber = max;
			sequenceNumber = boardDto.getSequenceNumber();
			sequenceLevel = boardDto.getSequenceLevel();
		}else{
			//���
			HashMap<String , Integer> hMap = new HashMap<String ,Integer>();
			hMap.put("groupNumber", groupNumber);
			hMap.put("sequenceNumber", sequenceNumber);
			
			boardDao.boardGroupNumberUpdate(hMap);
			sequenceNumber+=1;
			sequenceLevel+=1;
		}
		
		boardDto.setGroupNumber(groupNumber);
		boardDto.setSequenceLevel(sequenceLevel);
		boardDto.setSequenceNumber(sequenceNumber);
	}
	
	@Override
	public void boardList(ModelAndView mav) {
		Map<String , Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String pageNumber = request.getParameter("pageNumber");
		if(pageNumber == null) pageNumber = "1";
		
		int boardSize = 5; //���������� ������ �Խù� ����
		int currentPage = Integer.parseInt(pageNumber);
		int startRow = (currentPage-1)*boardSize +1;
		int endRow = currentPage*boardSize;
		
		int count=boardDao.getBoardConut();
		logger.info("count : " + count);
		
		List<BoardDto> boardList=null;
		if(count > 0){
			boardList = boardDao.getBoardList(startRow , endRow);
		}
		
		mav.addObject("boardSize" , boardSize);
		mav.addObject("boardList" , boardList);
		mav.addObject("count" ,count);
		mav.addObject("currentPage" , currentPage);
		
		//�̰ž��ص� �Ѿ�� ������ ������;
		mav.setViewName("board/list");
	}
	@Override
	public void boardRead(ModelAndView mav) {
		Map<String , Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		
		BoardDto boardDto=null;
		try {
			boardDto = boardDao.getInfo(boardNumber);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mav.addObject("board" , boardDto);
		mav.addObject("pageNumber" , pageNumber);
	}
	@Override
	public void boardDelete(ModelAndView mav) {
		logger.severe("delete start");
		Map<String , Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
		mav.addObject("pageNumber", pageNumber);
		mav.addObject("boardNumber" , boardNumber);
		mav.setViewName("board/delete");
	}
	@Override
	public void boardDeleteOk(ModelAndView mav) {
		Map<String , Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
		String password = request.getParameter("password");
		
		HashMap<String , Object> hMap = new HashMap<String , Object>();
		hMap.put("boardNumber", boardNumber);
		hMap.put("password", password);
		
		int check = boardDao.boardDelete(hMap);
		mav.addObject("pageNumber", pageNumber);
		mav.addObject("boardNumber" , boardNumber);
		mav.addObject("check" , check);
	}
	@Override
	public void boardUpdate(ModelAndView mav) {
		Map<String , Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
		
		BoardDto board = boardDao.getInfo(boardNumber);
		
		mav.addObject("board" , board);
		mav.addObject("pageNumber", pageNumber);
		mav.addObject("boardNumber" , boardNumber);
		mav.setViewName("board/update");
	}
	@Override
	public void boardUpdateOk(ModelAndView mav) {
		Map<String , Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		BoardDto boardDto = (BoardDto) map.get("boardDto");
		
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
		mav.addObject("pageNumber", pageNumber);
		mav.addObject("boardNumber" , boardNumber);
		
		int check = boardDao.boardUpdate(boardDto);
		logger.severe("check :" + check);
		mav.addObject("check" , check);
		mav.setViewName("board/updateOk");
	}
}
