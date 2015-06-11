package com.java.homePage.boardDao;

import java.util.HashMap;
import java.util.List;

import com.java.homePage.boardDto.BoardDto;

public interface BoardDao {
	public int boardGroupNumberMax();
	public int boardGroupNumberUpdate(HashMap<String,Integer> hMap);
	public int boardInsert(BoardDto boardDto);
	public int getBoardConut();
	public List<BoardDto> getBoardList(int startRow, int endRow);
	public BoardDto getInfo(int boardNumber);
	public int boardDelete(HashMap<String, Object> hMap);
	public int boardUpdate(BoardDto boardDto);
}
