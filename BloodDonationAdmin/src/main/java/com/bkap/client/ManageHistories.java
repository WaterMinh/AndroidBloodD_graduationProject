package com.bkap.client;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class ManageHistories
 */
@WebServlet("/ManageHistories")
public class ManageHistories extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageHistories() {
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
			List<History> listHistory = cws.getHistories();
			for (History history : listHistory) {
				User u = cws.getUserById(history.getUserId());
				DonationPlace dnp = cws.getDonationPlaceById(history.getPlace());

				history.setFullName(u.getFullName());
				history.setPlaceName(dnp.getNamePlace());
			}
			
			String txtId = request.getParameter("id");
			String method = request.getParameter("method");
			String txtIdHis = request.getParameter("idHis");
			if (txtId != null && !txtId.isEmpty() && Integer.parseInt(txtId) > 0) {
				int id = Integer.parseInt(txtId);
				History his = cws.getHistoryById(id);
				if (his != null) {
					request.setAttribute("title", "Cập nhập lịch sử hiến máu");
					request.setAttribute("active5", true);
					request.setAttribute("user", user);

					request.setAttribute("listUser", users);
					request.setAttribute("listDonationPlace", donationPlaces);
					request.setAttribute("u", his);
					request.getRequestDispatcher("add-history.jsp").forward(request, response);
				} else {					
					request.setAttribute("title", "Lịch sử hiến máu");
					request.setAttribute("active5", true);
					request.setAttribute("user", user);
					
					request.setAttribute("listHistory", listHistory);
					request.getRequestDispatcher("manage-histories.jsp").forward(request, response);
				}
			} else if (method != null && !method.isEmpty() && txtIdHis != null && !txtIdHis.isEmpty()) {
				int idHis = Integer.parseInt(txtIdHis);
				BkapRespone res = new BkapRespone();
				res.setCode(200);
				if (idHis > 0) {
					if (method.equals("delete")) {
						boolean isOk = cws.deleteHistory(idHis);
						res.setSuccess(isOk);
						if (isOk) {
							res.setMessage("Xoá lịch sử hiến máu ID " + idHis + " thành công");
						} else {
							res.setMessage("Xoá lịch sử hiến máu ID " + idHis + " thất bại");
						}
					} else {
						res.setSuccess(false);
						res.setMessage("Phương thức không tồn tại");
					}
				} else {
					res.setSuccess(false);
					res.setMessage("Lịch sử hiến máu không tồn tại");
				}
				Gson gson = new Gson();
				String json = gson.toJson(res);

				PrintWriter out = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.print(json);
				out.flush();
			} else {
				request.setAttribute("title", "Lịch sử hiến máu");
				request.setAttribute("active5", true);
				request.setAttribute("user", user);
				
				request.setAttribute("listHistory", listHistory);
				request.getRequestDispatcher("manage-histories.jsp").forward(request, response);
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
