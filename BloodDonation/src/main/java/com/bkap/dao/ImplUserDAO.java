package com.bkap.dao;

import java.util.List;

import org.hibernate.Session;

import com.bkap.entity.Users;
import com.bkap.service.HibernateUtils;

public class ImplUserDAO implements IUserDAO{

	@Override
	public Users login(String email, String password) {
		System.out.println("email : " + email);
		System.out.println("password : "+ password);
		Users user = null;
		try {
			Session ss = HibernateUtils.getSessionFactory().openSession();
			ss.beginTransaction();

			user = (Users) ss.createQuery("from Users where email = :uemail and password = :upass")
					.setParameter("uemail", email).setParameter("upass", password).getSingleResult();
			ss.getTransaction().commit();
			ss.close();
		} catch (Exception e) {
			System.out.println("Lỗi " + e.getMessage());
			e.printStackTrace();
		}
		return user;
	}
	
	@Override
	public List<Users> selectAll() {
		List<Users> users = null;
		try {
			Session ss = HibernateUtils.getSessionFactory().openSession();
			ss.beginTransaction();

			users = ss.createQuery("from Users").list();

			ss.getTransaction().commit();
			ss.close();
		} catch (Exception e) {
			System.out.println("Lỗi " + e.getMessage());
			e.printStackTrace();
		}
		return users;
	}
	
	@Override
	public Users selectById(int userId) {
		Users user = null;
		try {
			Session ss = HibernateUtils.getSessionFactory().openSession();
			ss.beginTransaction();
			
			user = ss.get(Users.class, userId);
			
			ss.getTransaction().commit();
			ss.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return user;
	}


	@Override
	public boolean insert(Users user) {
		Session ss = HibernateUtils.getSessionFactory().openSession();
		ss.beginTransaction();
		
		try {
			ss.save(user);
			System.out.println("Thêm mới người dùng"+ user.getUserId() + "thành công");
		}catch (Exception e) {
			System.out.println("Lỗi" + e.getMessage());
			e.printStackTrace();
			return false;
		}finally {
			ss.getTransaction().commit();
			ss.close();
		}
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean update(Users user) {
		Session ss = HibernateUtils.getSessionFactory().openSession();
		ss.beginTransaction();
		
		try {
			ss.update(user);
			System.out.println("Cập nhật người dùng"+ user.getUserId() + "thành công");
		}catch (Exception e) {
			System.out.println("Lỗi" + e.getMessage());
			e.printStackTrace();
			return false;
		}finally {
			ss.getTransaction().commit();
			ss.close();
		}
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean remove(int userId) {
		Session ss = HibernateUtils.getSessionFactory().openSession();
		ss.beginTransaction();
		
		try {
			Users user = (Users) ss.createQuery("from Users where userId = :uId").setParameter("uId", userId).getSingleResult();
			ss.remove(user);
			System.out.println("Xoá người dùng "+ user.getUserId() + " thành công");
		}catch (Exception e) {
			System.out.println("Lỗi " + e.getMessage());
			e.printStackTrace();
			return false;
		}finally {
			ss.getTransaction().commit();
			ss.close();
		}
		// TODO Auto-generated method stub
		return true;
	}



}
