package com.java.addr.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.addr.dto.Address;

@Component
public class DaoImp implements Dao{
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int write(Address address) {
		return sqlSession.insert("write" , address);
	}
	@Override
	public List<Address> select(String name) {
		return sqlSession.selectList("select" , name);
	}
	@Override
	public int delete(String name) {
		return sqlSession.delete("delete" , name );
	}
	@Override
	public Address search(String name) {
		return sqlSession.selectOne("search" , name);
	}
	@Override
	public int update(Address address) {
		return sqlSession.update("update" , address);
	}
	@Override
	public Address selectName(String name) {
		return sqlSession.selectOne("selectUser" , name);
	}
	
	
	
}
