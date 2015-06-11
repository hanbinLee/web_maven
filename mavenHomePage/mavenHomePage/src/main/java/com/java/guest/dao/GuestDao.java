package com.java.guest.dao;

import java.util.List;

import com.java.guest.dto.GuestDto;

public interface GuestDao {
	public int getGuestCount();
	public List<GuestDto> getGuestList(int startRow,int endRow);
	public int insert(GuestDto guestDto);
	public int delete(int num);
	public GuestDto select(int num);
	public int update(GuestDto guestDto);
}
