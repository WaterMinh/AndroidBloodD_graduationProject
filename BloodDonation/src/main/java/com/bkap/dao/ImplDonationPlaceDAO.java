package com.bkap.dao;

import java.util.List;

import org.hibernate.Session;

import com.bkap.entity.DonationPlaces;
import com.bkap.service.HibernateUtils;

public class ImplDonationPlaceDAO implements IDonationPlaceDAO {

	@Override
	public List<DonationPlaces> selectAll() {
		Session ss = HibernateUtils.getSessionFactory().openSession();
		ss.beginTransaction();
		
		List<DonationPlaces> lst = ss.createQuery("from DonationPlaces ").list();
		ss.getTransaction().commit();
		ss.close();
		// TODO Auto-generated method stub
		return lst;
	}

	@Override
	public DonationPlaces selectById(int idPlace) {
		Session ss = HibernateUtils.getSessionFactory().openSession();
		ss.beginTransaction();
		
		DonationPlaces dnp = ss.get(DonationPlaces.class,idPlace);
		
		ss.getTransaction().commit();
		ss.close();
		// TODO Auto-generated method stub
		return dnp;
	}

	@Override
	public Boolean insert(DonationPlaces donationPlaces) {
		Session ss = HibernateUtils.getSessionFactory().openSession();
		ss.beginTransaction();
		
		try {
			ss.save(donationPlaces);
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
	public Boolean update(DonationPlaces donationPlaces) {
		Session ss = HibernateUtils.getSessionFactory().openSession();
		ss.beginTransaction();
		
		try {
			ss.update(donationPlaces);
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
	public Boolean delete(int idPlace) {
		Session ss = HibernateUtils.getSessionFactory().openSession();
		ss.beginTransaction();
		
		try {
			DonationPlaces dnp = ss.get(DonationPlaces.class, idPlace);
			ss.delete(dnp);
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

}
