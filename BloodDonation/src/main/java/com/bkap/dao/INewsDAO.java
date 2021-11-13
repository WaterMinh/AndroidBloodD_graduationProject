package com.bkap.dao;
import java.util.List;
import com.bkap.entity.News;

public interface INewsDAO {
	public List<News> selectAll();
	public News selectById(int idNews);
	public Boolean insert(News news);
	public Boolean update(News news);
	public Boolean delete(int idNews);
}
