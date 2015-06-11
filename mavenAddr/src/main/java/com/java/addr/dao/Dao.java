package com.java.addr.dao;

import java.util.List;

import com.java.addr.dto.Address;

public interface Dao {

	public int write(Address address);

	public List<Address> select(String name);

	public int delete(String parameter);

	public Address search(String parameter);

	public int update(Address address);

	public Address selectName(String name);

}
