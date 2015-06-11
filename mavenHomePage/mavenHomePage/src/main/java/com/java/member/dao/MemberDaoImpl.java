package com.java.member.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.member.dto.MemberDto;
import com.java.member.dto.MemberZipcodeDto;

@Component
public class MemberDaoImpl implements MemberDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;	

	@Override
	public int insert(MemberDto memberDto) {
		return sqlSession.insert("dao.memberMapper.insert", memberDto);
	}

	@Override
	public int idCheck(String id) {
		//id값이 null로 넘어올수 있다. null로 넘어오면 nullpointException 에러발생
		
		//같은 아이디가 있는지 확인후 그 아이디를 String으로 반환
		String check=sqlSession.selectOne("dao.memberMapper.idCheck", id);
		
		int value=0;
		if(check!=null) value=1;
		
		return value;
	}

	@Override
	public List<MemberZipcodeDto> zipcode(String dong) {
		return sqlSession.selectList("dao.memberMapper.zipcode", dong);
	}

	@Override
	public String login(String id, String password) {
		Map<String, String> map=new HashMap<String, String>();
		map.put("id", id);
		map.put("password", password);
		
		return sqlSession.selectOne("dao.memberMapper.login", map);
	}

	@Override
	public int delete(String id, String password) {
		Map<String, String> map=new HashMap<String, String>();
		map.put("id", id);
		map.put("password", password);
		
		return sqlSession.delete("dao.memberMapper.memberDelete", map);
	}

	@Override
	public MemberDto updateSelect(String id) {
		return sqlSession.selectOne("dao.memberMapper.memberUpdateSelect", id);
	}

	@Override
	public int update(MemberDto memberDto) {
		return sqlSession.update("dao.memberMapper.memberUpdate", memberDto);
	}
	
	
}
