package com.java.member.dao;

import java.util.List;

import com.java.member.dto.MemberDto;
import com.java.member.dto.MemberZipcodeDto;

public interface MemberDao {
	public int insert(MemberDto memberDto);
	public int idCheck(String id);
	public List<MemberZipcodeDto> zipcode(String dong);
	public String login(String id, String password);
	public int delete(String id, String password);
	public MemberDto updateSelect(String id);
	public int update(MemberDto memberDto);
}
