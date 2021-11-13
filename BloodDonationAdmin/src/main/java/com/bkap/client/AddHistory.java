package com.bkap.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bkap.entity.BkapRespone;
import com.bkap.entity.DonationPlace;
import com.bkap.entity.History;
import com.bkap.entity.User;
import com.bkap.utils.CallWebService;
import com.google.gson.Gson;

/**
 * Servlet implementation class AddHistory
 */
@WebServlet("/AddHistory")
public class AddHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddHistory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		User user = null;
		HttpSession session = request.getSession();
		try {
			user = (User) session.getAttribute("user");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if (user != null) {
			CallWebService cws = new CallWebService();
			
			List<User> users = cws.getUsers();
			List<DonationPlace> donationPlaces = cws.getDonationPlaces();
			
			String txtUserId = request.getParameter("userId");
			if (txtUserId != null && !txtUserId.isBlank()) {
				BkapRespone res = new BkapRespone();
				res.setCode(200);
				
				String txtIdHis = request.getParameter("idHis");
				int userId = Integer.parseInt(txtUserId);
				int place = Integer.parseInt(request.getParameter("place"));
				String txtDayDonation = request.getParameter("dayDonation");
				int weight = Integer.parseInt(request.getParameter("weight"));
				int amountOfBlood = Integer.parseInt(request.getParameter("amountOfBlood"));
				boolean tinhTrang = Boolean.parseBoolean(request.getParameter("tinhTrang"));
				boolean macBenh = Boolean.parseBoolean(request.getParameter("macBenh"));
				boolean sutCan = Boolean.parseBoolean(request.getParameter("sutCan"));
				boolean phauThuat = Boolean.parseBoolean(request.getParameter("phauThuat"));
				boolean xam = Boolean.parseBoolean(request.getParameter("xam"));
				boolean truyenMau = Boolean.parseBoolean(request.getParameter("truyenMau"));
				boolean maTuy = Boolean.parseBoolean(request.getParameter("maTuy"));
				boolean quanHe = Boolean.parseBoolean(request.getParameter("quanHe"));
				boolean quanHeCungGioi = Boolean.parseBoolean(request.getParameter("quanHeCungGioi"));
				boolean vaccine = Boolean.parseBoolean(request.getParameter("vaccine"));
				boolean vungDich = Boolean.parseBoolean(request.getParameter("vungDich"));
				boolean benh = Boolean.parseBoolean(request.getParameter("benh"));
				boolean thuocKS = Boolean.parseBoolean(request.getParameter("thuocKS"));
				boolean diKham = Boolean.parseBoolean(request.getParameter("diKham"));
				boolean tanTat = Boolean.parseBoolean(request.getParameter("tanTat"));
				boolean mangThai = Boolean.parseBoolean(request.getParameter("mangThai"));
				boolean status =  Boolean.parseBoolean(request.getParameter("status"));
				if (txtIdHis != null && !txtIdHis.isEmpty()) {
					int idHis = Integer.parseInt(txtIdHis);
					if (idHis > 0) {
						java.sql.Date dayDonation = null;
						try {
							Date getDayDonation = new SimpleDateFormat("yyyy-MM-dd").parse(txtDayDonation);
							dayDonation = new java.sql.Date(getDayDonation.getTime());
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (dayDonation != null) {
							History his = new History(idHis, 1, dayDonation, userId, weight, amountOfBlood, place, tinhTrang, macBenh, sutCan, phauThuat, xam, truyenMau, maTuy, quanHe, quanHeCungGioi, vaccine, vungDich, benh, thuocKS, diKham, tanTat, mangThai, status);
							boolean isOk = cws.updateHistory(his);
							
							if (isOk) {
								res.setSuccess(true);
								res.setMessage("Cập nhập lịch sử hiến máu thành công");
							} else {
								res.setSuccess(false);
								res.setMessage("Cập nhập lịch sử hiến máu thất bại");
							}
						} else {
							res.setSuccess(false);
							res.setMessage("Ngày hiến máu không hợp lệ");
						}
					} else {
						res.setSuccess(false);
						res.setMessage("Lịch sử hiến máu không tồn tại");
					}
				} else {
					java.sql.Date dayDonation = null;
					try {
						Date getDayDonation = new SimpleDateFormat("yyyy-MM-dd").parse(txtDayDonation);
						dayDonation = new java.sql.Date(getDayDonation.getTime());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (dayDonation != null) {
						History his = new History(1, dayDonation, userId, weight, amountOfBlood, place, tinhTrang, macBenh, sutCan, phauThuat, xam, truyenMau, maTuy, quanHe, quanHeCungGioi, vaccine, vungDich, benh, thuocKS, diKham, tanTat, mangThai, status);
						boolean isOk = cws.insertHistory(his);
						
						if (isOk) {
							res.setSuccess(true);
							res.setMessage("Đặt lịch hiến máu thành công");
						} else {
							res.setSuccess(false);
							res.setMessage("Đặt lịch hiến máu thất bại");
						}
					} else {
						res.setSuccess(false);
						res.setMessage("Ngày hiến máu không hợp lệ");
					}
				}
				
				Gson gson = new Gson();
				String json = gson.toJson(res);

				PrintWriter out = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.print(json);
				out.flush();
			} else {
				request.setAttribute("title", "Đặt lịch hiến máu");
				request.setAttribute("active5", true);
				request.setAttribute("user", user);
				
				request.setAttribute("listUser", users);
				request.setAttribute("listDonationPlace", donationPlaces);
				
				request.getRequestDispatcher("add-history.jsp").forward(request, response);
			}
		} else {
			response.sendRedirect("Login");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
