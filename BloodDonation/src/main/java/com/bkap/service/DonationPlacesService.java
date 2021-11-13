package com.bkap.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bkap.dao.IDonationPlaceDAO;
import com.bkap.dao.ImplDonationPlaceDAO;
import com.bkap.entity.DonationPlaces;
import com.bkap.entity.Messages;
import com.google.gson.Gson;


@Path("/bloodDonate/")
public class DonationPlacesService {
	@GET	
	@Path("hello")
	@Produces(MediaType.APPLICATION_JSON)
	public String hello() {
		return "BloodBkap Server truy vấn ok";
	
	}
	
	/**
	 * truy váº«n dá»¯ liá»‡u danh sÃ¡ch dáº¡ng Json
	 */
	@GET
	@Path("list-places")
	@Produces(MediaType.APPLICATION_JSON)
	public String getListPlaces() {
		IDonationPlaceDAO dao = new ImplDonationPlaceDAO();
		List<DonationPlaces> lst = dao.selectAll();
		
		Gson gson = new Gson();
		String json = gson.toJson(lst);
		
		return json;
	}
	
	@POST
	@Path("get-place-by-id")
	@Produces(MediaType.APPLICATION_JSON)
	public String getPlaceById(String json) {
		Gson gson = new Gson();
		int idPlace = gson.fromJson(json, Integer.class);
		
		IDonationPlaceDAO dao = new ImplDonationPlaceDAO();
		DonationPlaces dnp = dao.selectById(idPlace);
		
		return gson.toJson(dnp);
	}
	
	/**
	 * thêm mới dữ liệu dạng Json
	 */
	@Path("insert-place")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String insert(String json) {
		Gson gson = new Gson();
		DonationPlaces dnp = gson.fromJson(json, DonationPlaces.class);
		IDonationPlaceDAO dao = new ImplDonationPlaceDAO();
		boolean SaveOk = dao.insert(dnp);
				
		if (SaveOk) {
			return gson.toJson(new Messages(100, "Thêm mớit dữ liệu thành công!"));
		} else {
			return gson.toJson(new Messages(101, "Thêm mới thất bại!"));
		}	
		
	}
	
	@POST
	@Path("update-place")
	@Produces(MediaType.APPLICATION_JSON)
	public String update(String json) {
		
		Gson gson = new Gson();
		IDonationPlaceDAO dao = new ImplDonationPlaceDAO();
		DonationPlaces dnp = gson.fromJson(json, DonationPlaces.class);

		boolean updateOk = dao.update(dnp);

		if (updateOk) {
			return gson.toJson(new Messages(100, "Cập nhật dữ liệu thành công!"));
		} else {
			return gson.toJson(new Messages(101, "Cập nhật thất bại!"));
		}
	}
	
	@POST
	@Path("delete-place")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteById(String json) {
		Gson gson = new Gson();
		int idPlace = gson.fromJson(json, Integer.class);
		
		IDonationPlaceDAO dao = new ImplDonationPlaceDAO();
		boolean deleteOk = dao.delete(idPlace);
		
		return gson.toJson(deleteOk);
	}
	
//	@Path("delete-place/{id: \\d}")
//	@POST
//	@Produces(MediaType.APPLICATION_JSON)
//	public String deleteById(@PathParam("idPlace") String idPlace) {
//		
//		IDonationPlaceDAO dao = new ImplDonationPlaceDAO();
//		boolean deleteOk = dao.delete(Integer.parseInt(idPlace));
//		
//		System.out.println("id:"+idPlace);
//		if (deleteOk) {
//			return "Xóa thành công";
//		} else {
//			return "Xóa thất bại";
//		}
//	}
	
}
