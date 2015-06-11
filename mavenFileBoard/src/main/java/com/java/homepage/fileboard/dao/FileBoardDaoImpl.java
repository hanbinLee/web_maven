package com.java.homepage.fileboard.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.homepage.fileboard.dto.FileBoardDto;



@Component
public class FileBoardDaoImpl implements FileBoardDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int boardGroupNumberMax() {
		return sqlSession.selectOne("dao.fileBoardMapper.boardGroupNumberMax");
	}

	@Override
	public int boardGroupNumberUpdate(HashMap<String, Integer> hMap) {
		return sqlSession.update("dao.fileBoardMapper.boardGroupNumberUpdate", hMap);
	}

	@Override
	public int insert(FileBoardDto fileBoardDto) {
		int value=0;
		
		if(fileBoardDto.getFileName()!=null){//파일을 업로드 안했을때
			value=sqlSession.insert("dao.fileBoardMapper.boardInsertFile", fileBoardDto);
		}else{
			value=sqlSession.insert("dao.fileBoardMapper.boardInsert", fileBoardDto);
		}
		return value;
	}

	@Override
	public int getBoardCount() {
		return sqlSession.selectOne("dao.fileBoardMapper.boardCount");
	}

	@Override
	public List<FileBoardDto> getBoardList(int startRow, int endRow) {
		Map <String, Integer> hMap=new HashMap<String, Integer>();
		hMap.put("startRow", startRow);
		hMap.put("endRow", endRow);
		return sqlSession.selectList("dao.fileBoardMapper.boardList", hMap);
	}

	@Override
	public FileBoardDto read(int boardNumber) {	
		FileBoardDto fileBoardDto=null;		
		try{
			sqlSession.update("dao.fileBoardMapper.readCount", boardNumber);
			fileBoardDto=sqlSession.selectOne("dao.fileBoardMapper.boardRead", boardNumber);
			//throw new RuntimeException();
			//throw new SQLException();
			
		}catch(Exception e){
			sqlSession.rollback();
		}		
		return fileBoardDto;
	}

	@Override
	public int delete(int boardNumber, String password) {
		Map<String, Object> hMap=new HashMap<String, Object>();
		hMap.put("boardNumber", boardNumber);
		hMap.put("password", password);
		
		return sqlSession.delete("dao.fileBoardMapper.delete", hMap);
	}

	@Override
	public FileBoardDto getBoardUpdate(int boardNumber) {
		return sqlSession.selectOne("dao.fileBoardMapper.getBoardUpdate", boardNumber );
	}

	@Override
	public int update(int boardNumber, FileBoardDto fileBoardDto, String fileName) {
		int value=0;
		Map <String, Object> hMap=new HashMap<String, Object>();
		hMap.put("boardNumber", boardNumber);
		hMap.put("fileBoardDto", fileBoardDto);
		
		//fileName은 update.jsp의 form으로부터 넘어온 binary 업데이트 될 파일의 이름
		if(fileName==null || fileName.equals("")){			
			value=sqlSession.update("update", fileBoardDto);
		}else{
			value=sqlSession.update("updateFile", fileBoardDto);			
		}
		return value;
	}
	
}
