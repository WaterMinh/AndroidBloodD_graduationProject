package com.bkap.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bkap.dao.IDonationPlaceDAO;
import com.bkap.dao.IHistoryDAO;
import com.bkap.dao.ImplDonationPlaceDAO;
import com.bkap.dao.ImplHistoryDAO;
import com.bkap.entity.DonationPlaces;
import com.bkap.entity.History;
import com.bkap.model.BkResponse;
import com.bkap.model.HistoryResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/bloodDonate1/")
public class HistoryService {
	@GET
	@Path("list-history")
	@Produces(MediaType.APPLICATION_JSON)
	public String getListHistory() {
		IHistoryDAO dao = new ImplHistoryDAO();
		List<History> lst = dao.selectAll();
		
		Gson gson = new Gson();
		String json = gson.toJson(lst);
		
		return json;
	}
	
	@GET
	@Path("list-history/{userId: (\\d)+}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getListHistory(@PathParam("userId") int userId) {
		Gson gson = new Gson();
		IHistoryDAO dao = new ImplHistoryDAO();
		List<History> lst = dao.selectByUserId(userId);
		
		IDonationPlaceDAO donationPlaceDAO = new ImplDonationPlaceDAO();
		
		List<HistoryResponse> result = new ArrayList<HistoryResponse>();
		for (History history : lst) {	
			DonationPlaces place = donationPlaceDAO.selectById(history.getPlace());
			result.add(new HistoryResponse(history, place));
		}
		
		String json = gson.toJson(result);
		
		return json;
	}
	
	@POST
	@Path("get-history-by-id")
	@Produces(MediaType.APPLICATION_JSON)
	public String getHistoryById(String json) {
		Gson gson = new Gson();
		int hisId = gson.fromJson(json, Integer.class);
		
		IHistoryDAO dao = new ImplHistoryDAO();
		History h = dao.selectById(hisId);
		
		return gson.toJson(h);
	}
	
	@POST
	@Path ("insert-history")
	@Produces(MediaType.APPLICATION_JSON)
	public String insert(String json) {
		Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy").create();
		System.out.println(json);
		History his = gson.fromJson(json, History.class);
		IHistoryDAO dao = new ImplHistoryDAO();
		List<History> lst = dao.selectByUserId(his.getUserId());
		for (History history : lst) {
			if (history.getDayDonation().compareTo(his.getDayDonation()) == 0 || (history.getDayDonation().compareTo(his.getDayDonation()) == 0 && history.getPlace() == his.getPlace()))
				return gson.toJson(new BkResponse(102, "Ngày hiến màu và địa điểm đã tồn tại"));
		}
		
		boolean isOk = dao.insert(his);
				
		if (isOk)
			return gson.toJson(new BkResponse(100, "Thêm mới lịch hiến máu thành công"));
		
		return gson.toJson(new BkResponse(101, "Thêm mới lịch hiến máu thất bại"));
	}
	
	
	@POST
	@Path("update-history")
	@Produces(MediaType.APPLICATION_JSON)
	public String update(String json) {
		
		Gson gson = new Gson();
		IHistoryDAO dao = new ImplHistoryDAO();
		History his = gson.fromJson(json, History.class);

		boolean isOk = dao.update(his);

		if (isOk)
			return gson.toJson(new BkResponse(100, "Cập nhập lịch hiến máu thành công"));
		
		return gson.toJson(new BkResponse(101, "Cập nhập lịch hiến máu thất bại"));
	}
	
	@POST
	@Path("delete-history")
	@Produces(MediaType.APPLICATION_JSON)
	public String delete(String json) {
		Gson gson = new Gson();
		int idHis = gson.fromJson(json, Integer.class);
		
		IHistoryDAO dao = new ImplHistoryDAO();
		boolean isOk = dao.delete(idHis);
		
		if (isOk)
			return gson.toJson(new BkResponse(100, "Xoá lịch hiến máu thành công"));
		
		return gson.toJson(new BkResponse(101, "Xoá lịch hiến máu thất bại"));
	}

}
