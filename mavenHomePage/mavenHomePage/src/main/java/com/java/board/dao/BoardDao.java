package com.java.board.dao;

import java.util.HashMap;
import java.util.List;

import com.java.board.dto.BoardDto;

public interface BoardDao {
	public int boardGroupNumberMax();
	public int boardGroupNumberUpdate(HashMap<String,Integer> hMap);
	public int insert(BoardDto boardDto);
	public int getBoardCount();
	public List<BoardDto> getBoardList(int startRow,int endRow);
	public BoardDto boardRead(int boardNumber);
	public BoardDto boardSelect(int boardNumber);
	public int boardUpdate(BoardDto boardDto);
	public int boardDeleteOk(BoardDto password);
}
