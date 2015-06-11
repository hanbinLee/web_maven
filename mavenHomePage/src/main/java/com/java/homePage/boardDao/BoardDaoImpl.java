package com.java.homePage.boardDao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.homePage.boardDto.BoardDto;

@Component
public class BoardDaoImpl implements BoardDao{
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int boardGroupNumberMax() {
		return sqlSession.selectOne("dao.BoardMapper.groupNumberMax");
	}

	@Override
	public int boardGroupNumberUpdate(HashMap<String, Integer> hMap) {
		return sqlSession.update("dao.BoardMapper.groupNumberUpdate" , hMap);
	}

	@Override
	public int boardInsert(BoardDto boardDto) {
		return sqlSession.insert("dao.BoardMapper.boardInsert" , boardDto);
	}

	@Override
	public int getBoardConut() {
		return sqlSession.selectOne("dao.BoardMapper.boardCount");
	}

	@Override
	public List<BoardDto> getBoardList(int startRow, int endRow) {
		HashMap<String , Integer> hMap = new HashMap<String , Integer>();
		hMap.put("startRow", startRow);
		hMap.put("endRow", endRow);
		return sqlSession.selectList("dao.BoardMapper.getBoardList" , hMap);
	}

	@Override
	public BoardDto getInfo(int boardNumber){
		BoardDto board = null;
		
		try{
			int check = sqlSession.update("dao.BoardMapper.readCount" , boardNumber);
			System.out.println(check);
			board = sqlSession.selectOne("dao.BoardMapper.getInfo" , boardNumber);
			System.out.println(board.getEmail());	
		}catch(Exception e){
			System.out.println("����");
			//sqlSession.rollback();
		}
		return board;
	}

	@Override
	public int boardDelete(HashMap<String, Object> hMap) {
		return sqlSession.delete("dao.BoardMapper.delete" , hMap);	
	}

	@Override
	public int boardUpdate(BoardDto boardDto) {
		return sqlSession.update("dao.BoardMapper.update" , boardDto);
	}
	
	
}
