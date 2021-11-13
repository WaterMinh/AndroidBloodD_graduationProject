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
import com.bkap.entity.User;
import com.bkap.utils.CallWebService;
import com.google.gson.Gson;

/**
 * Servlet implementation class ManagePlaces
 */
@WebServlet("/ManagePlaces")
public class ManagePlaces extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagePlaces() {
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
			
			String txtId = request.getParameter("id");
			String method = request.getParameter("method");
			String txtIdPlace = request.getParameter("idPlace");
			if (txtId != null && !txtId.isEmpty() && Integer.parseInt(txtId) > 0) {
				int id = Integer.parseInt(txtId);
				DonationPlace dnp = cws.getDonationPlaceById(id);
				if (dnp != null) {
					request.setAttribute("title", "Cập nhập địa điểm hiến máu");
					request.setAttribute("active3", true);
					request.setAttribute("user", user);

					request.setAttribute("u", dnp);
					request.getRequestDispatcher("add-donation-place.jsp").forward(request, response);
				} else {
					List<DonationPlace> listDonationPlace = cws.getDonationPlaces();
					
					request.setAttribute("title", "Quản lý địa điểm hiến máu");
					request.setAttribute("active3", true);
					request.setAttribute("user", user);
					

					request.setAttribute("listDonationPlace", listDonationPlace);
					request.getRequestDispatcher("manage-donation-places.jsp").forward(request, response);
				}
			} else if (method != null && !method.isEmpty() && txtIdPlace != null && !txtIdPlace.isEmpty()) {
				int idPlace = Integer.parseInt(txtIdPlace);
				BkapRespone res = new BkapRespone();
				res.setCode(200);
				if (idPlace > 0) {
					if (method.equals("delete")) {
						boolean isOk = cws.deletePlace(idPlace);
						res.setSuccess(isOk);
						if (isOk) {
							res.setMessage("Xoá địa điểm ID " + idPlace + " thành công");
						} else {
							res.setMessage("Xoá địa điểm ID " + idPlace + " thất bại");
						}
					} else {
						res.setSuccess(false);
						res.setMessage("Phương thức không tồn tại");
					}
				} else {
					res.setSuccess(false);
					res.setMessage("Địa điểm không tồn tại");
				}
				Gson gson = new Gson();
				String json = gson.toJson(res);

				PrintWriter out = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.print(json);
				out.flush();
			} else {
				List<DonationPlace> listDonationPlace = cws.getDonationPlaces();

				request.setAttribute("title", "Quản lý địa điểm hiến máu");
				request.setAttribute("active3", true);
				request.setAttribute("user", user);

				request.setAttribute("listDonationPlace", listDonationPlace);
				request.getRequestDispatcher("manage-donation-places.jsp").forward(request, response);
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
