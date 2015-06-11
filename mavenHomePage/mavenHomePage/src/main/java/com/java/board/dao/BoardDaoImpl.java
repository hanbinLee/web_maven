package com.java.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.board.dto.BoardDto;

@Component
public class BoardDaoImpl implements BoardDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int boardGroupNumberMax() {
		
		return sqlSession.selectOne("dao.BoardMapper.boardGroupNumberMax");
	}

	@Override
	public int boardGroupNumberUpdate(HashMap<String, Integer> hMap) {
		
		return sqlSession.update("dao.BoardMapper.boardGroupNumberUpdate",hMap);
	}

	@Override
	public int insert(BoardDto boardDto) {		
		
		return sqlSession.insert("dao.BoardMapper.boardInsert" , boardDto);
	}

	@Override
	public int getBoardCount() {
		
		return sqlSession.selectOne("dao.BoardMapper.boardCount");
	}

	@Override
	public List<BoardDto> getBoardList(int startRow, int endRow) {
		Map<String,Integer> hMap=new HashMap<String,Integer>();
		hMap.put("startRow",startRow);
		hMap.put("endRow", endRow);
		return sqlSession.selectList("dao.BoardMapper.boardList",hMap);
	}

	@Override
	public BoardDto boardRead(int boardNumber) {
		BoardDto board=null;
		try{
		sqlSession.update("dao.BoardMapper.readCount",boardNumber);
		board=sqlSession.selectOne("dao.BoardMapper.read",boardNumber);
		}catch(Exception e){
			sqlSession.rollback();
		}
		return board;
	}

	@Override
	public BoardDto boardSelect(int boardNumber) {				
		
		return sqlSession.selectOne("dao.BoardMapper.boardSelect",boardNumber);
	}

	@Override
	public int boardUpdate(BoardDto boardDto) {
		
		return sqlSession.update("dao.BoardMapper.boardUpdate",boardDto);
	}

	@Override
	public int boardDeleteOk(BoardDto boardDto) {
		
		return sqlSession.delete("dao.BoardMapper.boardDelete",boardDto);
	}
	

	
	
}
