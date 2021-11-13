package com.bkap.dao;

import java.util.List;

import org.hibernate.Session;

import com.bkap.entity.DonationPlaces;
import com.bkap.entity.News;
import com.bkap.service.HibernateUtils;

public class ImplNewsDAO implements INewsDAO {

	@Override
	public List<News> selectAll() {
		Session ss = HibernateUtils.getSessionFactory().openSession();
		ss.beginTransaction();
		
		List<News> lst = ss.createQuery("from News").list();
		
		ss.getTransaction().commit();
		ss.close();
		// TODO Auto-generated method stub
		return lst;
	}

	@Override
	public News selectById(int idNews) {
		Session ss = HibernateUtils.getSessionFactory().openSession();
		ss.beginTransaction();
		
		News news = ss.get(News.class,idNews);
		
		ss.getTransaction().commit();
		ss.close();
		// TODO Auto-generated method stub
		return news;
	}

	@Override
	public Boolean insert(News news) {
		Session ss = HibernateUtils.getSessionFactory().openSession();
		ss.beginTransaction();
		
		try {
			ss.save(news);
			ss.getTransaction().commit();
			ss.close();
			return true;
		} catch (Exception e) {
			System.out.println("Error:" + e.getMessage());
			System.out.println("Reason:" + e.toString());
			e.printStackTrace();
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Boolean update(News news) {
		Session ss = HibernateUtils.getSessionFactory().openSession();
		ss.beginTransaction();
		try {
			ss.update(news);
			ss.getTransaction().commit();
			ss.close();
			return true;
		} catch (Exception e) {
			System.out.println("Error:" + e.getMessage());
			System.out.println("Reason:" + e.toString());
			e.printStackTrace();
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Boolean delete(int idNews) {
		Session ss = HibernateUtils.getSessionFactory().openSession();
		ss.beginTransaction();
		
		try {
			News news = ss.get(News.class, idNews);
			ss.delete(news);
			ss.getTransaction().commit();
			ss.close();
			return true;
		} catch (Exception e) {
			System.out.println("Error:" + e.getMessage());
			System.out.println("Reason:" + e.toString());
			e.printStackTrace();
		}
		
		return false;
	}

}

