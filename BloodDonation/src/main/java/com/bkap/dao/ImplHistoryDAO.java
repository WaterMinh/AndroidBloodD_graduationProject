package com.bkap.dao;


import java.util.List;

import org.hibernate.Session;
import com.bkap.entity.History;
import com.bkap.service.HibernateUtils;

public class ImplHistoryDAO implements IHistoryDAO {

	@Override
	public List<History> selectAll() {
		Session ss = HibernateUtils.getSessionFactory().openSession();
		ss.beginTransaction();
		
		List<History> lst = ss.createQuery("from History").list();
		
		ss.getTransaction().commit();
		ss.close();
		// TODO Auto-generated method stub
		return lst;
	}
	
	@Override
	public boolean insert(History his) {
		Session ss = HibernateUtils.getSessionFactory().openSession();
		ss.beginTransaction();
		try {
			List<History> getList = selectByUserId(his.getUserId());
			his.setTimes(getList.size() + 1);
			ss.save(his);
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

	@Override
	public boolean update(History his) {
		Session ss = HibernateUtils.getSessionFactory().openSession();
		ss.beginTransaction();

		try {
			History getHis = selectById(his.getIdHis());
			his.setTimes(getHis.getTimes());
			
			ss.update(his);
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

	@Override
	public History selectById(int idHis) {
		Session ss = HibernateUtils.getSessionFactory().openSession();
		ss.beginTransaction();

		History his = ss.get(History.class, idHis);

		ss.getTransaction().commit();
		ss.close();

		return his;
	}
	
	@Override
	public boolean delete(int hisId) {
		Session ss = HibernateUtils.getSessionFactory().openSession();
		ss.beginTransaction();
		
		try {
			History his = ss.get(History.class, hisId);
			ss.delete(his);
			ss.getTransaction().commit();
			ss.close();
			return true;
		} catch (Exception e) {
			System.out.println("Error:" + e.getMessage());
			System.out.println("Reason:" + e.toString());
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<History> selectByUserId(int userId) {
		Session ss = HibernateUtils.getSessionFactory().openSession();
		ss.beginTransaction();
		
		List<History> lst = ss.createQuery("from History where userId = :userId")
								.setParameter("userId", userId).list();
		
		//ss.getTransaction().commit();
		ss.close();
		// TODO Auto-generated method stub
		return lst;
	}

}

