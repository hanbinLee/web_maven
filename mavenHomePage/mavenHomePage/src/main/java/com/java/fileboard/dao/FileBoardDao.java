package com.java.fileboard.dao;

import java.util.HashMap;
import java.util.List;

import com.java.fileboard.dto.FileBoardDto;


public interface FileBoardDao {
	public int boardGroupNumberMax();
	public int boardGroupNumberUpdate(HashMap<String, Integer> hMap);
	public int insert(FileBoardDto fileBoardDto);
	public int getBoardCount();
	public List<FileBoardDto> getBoardList(int startRow, int endRow);
	public FileBoardDto read(int boardNumber);
	public int delete(int boardNumber, String password);
	public FileBoardDto getBoardUpdate(int boardNumber);
	public int update(int boardNumber, FileBoardDto fileBoardDto, String fileName);
}
