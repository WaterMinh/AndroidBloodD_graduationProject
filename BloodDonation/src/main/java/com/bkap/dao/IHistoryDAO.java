package com.bkap.dao;

import java.util.List;

import com.bkap.entity.History;

public interface IHistoryDAO {
	public List<History> selectAll();
	public History selectById(int hisId);
	public boolean insert(History his);
	public boolean update(History his);
	public boolean delete(int hisId);
	public List<History> selectByUserId(int userId);
}