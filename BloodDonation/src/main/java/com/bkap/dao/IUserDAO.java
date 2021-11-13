package com.bkap.dao;

import java.util.List;

import com.bkap.entity.Users;

public interface IUserDAO {
	public Users login (String email, String password);
	public List<Users> selectAll();
	public Users selectById(int userId);
	public boolean insert(Users user);
	public boolean update(Users user);
	public boolean remove(int userId);

}
