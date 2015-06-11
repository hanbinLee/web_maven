package com.java.homepage.fileboard.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.java.homepage.fileboard.dao.FileBoardDao;
import com.java.homepage.fileboard.dto.FileBoardDto;


@Component
public class FileBoardServiceImpl implements FileBoardService {
	private final Logger logger=Logger.getLogger(this.getClass().getName());
	
	@Autowired
	private FileBoardDao fileBoardDao;

	@Override
	public void write(ModelAndView mav) {
		Map <String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		int boardNumber=0;
		int groupNumber=1;
		int sequenceNumber=0;
		int sequenceLevel=0;
		
		if(request.getParameter("boardNumber")!=null){
			boardNumber=Integer.parseInt(request.getParameter("boardNumber"));
			groupNumber=Integer.parseInt(request.getParameter("groupNumber"));
			sequenceNumber=Integer.parseInt(request.getParameter("sequenceNumber"));
			sequenceLevel=Integer.parseInt(request.getParameter("sequenceLevel"));			
		}
		
		logger.info("boardNumber:" + boardNumber);
		logger.info("groupNumber:" + groupNumber);
		logger.info("sequenceNumber:" + sequenceNumber);
		logger.info("sequenceLevel:" + sequenceLevel);		
		
		mav.addObject("boardNumber", boardNumber);
		mav.addObject("groupNumber", groupNumber);
		mav.addObject("sequenceNumber", sequenceNumber );
		mav.addObject("sequenceLevel" , sequenceLevel);
		
		mav.setViewName("fileBoard/write");
	}

	@Override
	public void writeOk(ModelAndView mav) {
		Map <String, Object> map=mav.getModelMap();
		MultipartHttpServletRequest request=(MultipartHttpServletRequest)map.get("request");
				
		FileBoardDto fileBoardDto=(FileBoardDto) map.get("fileBoardDto");
		
		boardWriteNumber(fileBoardDto);
		
		fileBoardDto.setWriteDate(new Date());
		fileBoardDto.setReadCount(0);
		fileBoardDto.setIp(request.getRemoteAddr());
		
		MultipartFile upFile=request.getFile("file");//request로부터 넘어오는 값들중 file(binary)값만 자동으로 추출한다.
		String fileName=upFile.getOriginalFilename();
		String timeName=Long.toString(System.currentTimeMillis()) + "_" +  fileName ;
		long fileSize=upFile.getSize();
		
		logger.info("fileName" + fileName);
		logger.info("fileSize" + fileSize);
		logger.info("timeName" + timeName);
		
		if(fileSize!=0){
			try{
				//파일 절대경로
				String dir="C:\\Users\\KOSTA_07_002\\Documents\\mavenworkspace\\mavenFileBoard\\src\\main\\webapp\\resources";
				
				//상대경로 
				//이클립스의 fileUp폴더에서는 업로드 된 파일이 보이지 않고 
				//실제 경로 폴더에 가면 존재한다.
				//String dir=request.getSession().getServletContext().getRealPath("/resources");
				logger.info("dir:" + dir);
				
				File file=new File(dir, timeName);				
				//입출력(서버에 파일 저장) input output 자동지원
				upFile.transferTo(file); //upFile의 파일을 file에서 지정된 파일이름과 경로에 저장된다.
				
				fileBoardDto.setPath(file.getAbsolutePath());
				fileBoardDto.setFileName(fileName);
				fileBoardDto.setFileSize(fileSize);
				
			}catch(Exception e){
				logger.info("파일 입출력 에러:" + e);
			}
		}
		
		int check=fileBoardDao.insert(fileBoardDto);
		logger.info("check:" + check);
		
		mav.addObject("check", check);
		mav.setViewName("fileBoard/writeOk");
	}
	
	//writeOk에 
	public void boardWriteNumber(FileBoardDto fileBoardDto){
		int boardNumber=fileBoardDto.getBoardNumber();
		int groupNumber=fileBoardDto.getGroupNumber();
		int sequenceNumber=fileBoardDto.getSequenceNumber();
		int sequenceLevel=fileBoardDto.getSequenceLevel();
		
		logger.info("boardNumber:" + boardNumber);
		logger.info("groupNumber:" + groupNumber);
		logger.info("sequenceNumber:" + sequenceNumber);
		logger.info("sequenceLevel:" + sequenceLevel);
		
		int max=0;
		if(boardNumber==0){
			//ROOT
			max=fileBoardDao.boardGroupNumberMax();//14			
			if(max!=0){
			//	2번째 이상 글일때
				max=max+1;
			}else{
			//	첫번째 작성글일때
				max=fileBoardDto.getGroupNumber();//1
			}
			logger.info("max:" + max);
			
			groupNumber=max;
			sequenceNumber=fileBoardDto.getSequenceNumber();
			sequenceLevel=fileBoardDto.getSequenceLevel();
		}
		else{
			//답글
			HashMap<String,Integer> hMap=new HashMap<String, Integer>();
			hMap.put("groupNumber", groupNumber);
			hMap.put("sequenceNumber", sequenceNumber);
			
			fileBoardDao.boardGroupNumberUpdate(hMap);
			sequenceNumber=sequenceNumber+1;
			sequenceLevel=sequenceLevel+1;
		}
		
		fileBoardDto.setGroupNumber(groupNumber);
		fileBoardDto.setSequenceNumber(sequenceNumber);
		fileBoardDto.setSequenceLevel(sequenceLevel);
	}

	@Override
	public void list(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");

		int boardSize=10;
		String pageNumber=request.getParameter("pageNumber");
		
		if(pageNumber==null){ pageNumber="1";}
		
		int currentPage=Integer.parseInt(pageNumber);
		int startRow=(currentPage-1)*boardSize+1;
		int endRow=currentPage*boardSize;
		
		int count=fileBoardDao.getBoardCount();
		logger.info("count:" + count);
		
		List<FileBoardDto> fileBoardList=fileBoardDao.getBoardList(startRow, endRow);
		logger.info("fileBoardList:"  + fileBoardList.size());
		
		mav.addObject("boardList", fileBoardList);
		mav.addObject("count", count);
		mav.addObject("boardSize", boardSize);
		mav.addObject("currentPage", currentPage);
		mav.setViewName("fileBoard/list");
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT ,rollbackFor = {Exception.class,SQLException.class} ,readOnly = false)
	public void read(ModelAndView mav) {
		//boardNumber&pageNumber
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		int boardNumber= Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber= Integer.parseInt(request.getParameter("pageNumber"));
		
		FileBoardDto fileBoardDto=fileBoardDao.read(boardNumber);
		logger.info("fileBoardDto:" + fileBoardDto);
		
		mav.addObject("board", fileBoardDto);
		mav.addObject("pageNumber", pageNumber);
		mav.setViewName("fileBoard/read");
	}

	@Override
	public void fileDown(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		HttpServletResponse response=(HttpServletResponse) map.get("response");
	
		int boardNumber= Integer.parseInt(request.getParameter("boardNumber"));
		logger.info("boardNumber:" + boardNumber);
		
		FileBoardDto fileBoardDto=fileBoardDao.read(boardNumber);//filename, path, filesize
		long fileSize=fileBoardDto.getFileSize();
			
	
		String fileName="";
		try {
			fileName = new String(fileBoardDto.getFileName().getBytes("UTF-8"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		response.setHeader("Content=Transfer-Encoding", "binary");			
		response.setContentType("application/octet-stream");
		response.setContentLength((int)fileSize);
		
		BufferedInputStream fis=null;
		BufferedOutputStream fos=null;
		
		try{
			fis=new BufferedInputStream(new FileInputStream(fileBoardDto.getPath()));//서버
			fos=new BufferedOutputStream(response.getOutputStream());//클라이언트(다운)
			
			byte[] by=new byte[(int)fileSize];
			int count=fis.read(by);
			
			for(int i=0;i<count;i++){
				fos.write(by[i]);
			}
			
			fos.flush();
		}catch(Exception e){
			System.out.println("DownLoad Error:" + e);
			e.printStackTrace();
		}finally{
			if(fis!=null)
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if(fos!=null)
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}		
		
		mav.addObject("fileBoardDto", fileBoardDto);
		mav.addObject("response", response);
		mav.setViewName("fileBoard/read");
	}

	@Override
	public void delete(ModelAndView mav) {
		logger.info("delete-------");
		Map<String, Object>hMap=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest)hMap.get("request");
	
		int boardNumber=Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
		String password=request.getParameter("password");
		
		logger.info("password:" + password);
		int check=fileBoardDao.delete(boardNumber, password);
		logger.info("check:" + check);
		
		mav.addObject("check", check);
		mav.addObject("pageNumber", pageNumber);
		mav.setViewName("fileBoard/deleteOk");	
	}

	@Override
	public void update(ModelAndView mav) {
		logger.info("update------------");
		Map<String, Object> hMap=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest)hMap.get("request");
		
		int boardNumber=Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
		
		FileBoardDto fileBoardDto=fileBoardDao.getBoardUpdate(boardNumber);
		logger.info("fileBoardDto" + fileBoardDto);
		
		mav.addObject("boardNumber", boardNumber);
		mav.addObject("pageNumber", pageNumber);
		mav.addObject("board", fileBoardDto);
		mav.setViewName("fileBoard/update");	
	}

	@Override
	public void updateOk(ModelAndView mav) {
		logger.info("updateOk------------");
		Map<String, Object> hMap=mav.getModelMap();
		MultipartHttpServletRequest request=(MultipartHttpServletRequest)hMap.get("request");
		
		int boardNumber=Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));		
		FileBoardDto fileBoardDto=(FileBoardDto) hMap.get("fileBoardDto");
		
		MultipartFile upFile=request.getFile("file");
		
		
		String fileName=upFile.getOriginalFilename();
		String timeName=Long.toString(System.currentTimeMillis()) + "_" +  fileName ;
		long fileSize=upFile.getSize();
	
		if(fileSize!=0){//fileSize가 0이면 dao에 null값으로 넘어간다.
			try{
				//절대경로
				//String dir="C:\\spring\\workspace\\annoSpringFileBoard\\WebContent\\pds";
				
				//상대경로 
				//이클립스의 fileUp폴더에서는 업로드 된 파일이 보이지 않고 
				//실제 경로 폴더에 가면 존재한다.
				String dir=request.getSession().getServletContext().getRealPath("/fileUp");
				
				File file=new File(dir, timeName);			
				
				//입출력(서버에 파일 저장) input output 자동지원
				upFile.transferTo(file); //upFile의 파일을 file에서 지정된 파일이름과 경로에 저장된다.
				
				fileBoardDto.setPath(file.getAbsolutePath());
				fileBoardDto.setFileName(fileName);
				fileBoardDto.setFileSize(fileSize);
				
			}catch(Exception e){
				logger.info("파일 입출력 에러:" + e);
			}

			//DB에 아직 수정되기 전인 파일의 정보를 가져온다.
			FileBoardDto fileReadBoard=fileBoardDao.getBoardUpdate(boardNumber);
			if(fileReadBoard.getFileName()!=null){//수정(새로운 파일 업로드) 후 전파일 삭제
				File file=new File(fileReadBoard.getPath());
				if(file.exists() && file.isFile()) file.delete();
			}					
		}
		
		int check=fileBoardDao.update(boardNumber, fileBoardDto, fileName);
		logger.info("check:" + check);
		
		mav.addObject("pageNumber", pageNumber);
		mav.addObject("check", check);
		mav.setViewName("fileBoard/updateOk");	
	}
	
}
