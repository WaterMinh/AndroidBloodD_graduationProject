package com.bkap.dao;

import java.util.List;

import com.bkap.entity.DonationPlaces;

public interface IDonationPlaceDAO {
	public List<DonationPlaces> selectAll();
	public DonationPlaces selectById(int idPlace);
	public Boolean insert(DonationPlaces donationPlaces);
	public Boolean update(DonationPlaces donationPlaces);
	public Boolean delete(int idPlace);

}

