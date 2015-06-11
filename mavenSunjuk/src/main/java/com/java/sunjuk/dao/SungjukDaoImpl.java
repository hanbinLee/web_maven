package com.java.sunjuk.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.sunjuk.dto.Sungjuk;

@Component
public class SungjukDaoImpl implements SungjukDao{
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int write(Sungjuk sung) {	
		return sqlSession.insert("write" , sung);
	}
	
	
}
