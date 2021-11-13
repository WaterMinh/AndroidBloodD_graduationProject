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
import com.bkap.entity.User;
import com.bkap.utils.CallWebService;
import com.google.gson.Gson;

/**
 * Servlet implementation class ManageUsers
 */
@WebServlet("/ManageUsers")
public class ManageUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageUsers() {
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
			String txtUserId = request.getParameter("userId");
			if (txtId != null && !txtId.isEmpty() && Integer.parseInt(txtId) > 0) {
				int id = Integer.parseInt(txtId);
				User u = cws.getUserById(id);
				if (u != null) {
					request.setAttribute("title", "Cập nhập người dùng");
					request.setAttribute("active2", true);
					request.setAttribute("user", user);

					request.setAttribute("u", u);
					request.getRequestDispatcher("add-user.jsp").forward(request, response);
				} else {
					List<User> users = cws.getUsers();
					
					request.setAttribute("title", "Quản lý người dùng");
					request.setAttribute("active2", true);
					request.setAttribute("user", user);
					

					request.setAttribute("listUser", users);
					request.getRequestDispatcher("manage-users.jsp").forward(request, response);
				}
			} else if (method != null && !method.isEmpty() && txtUserId != null && !txtUserId.isEmpty()) {
				int userId = Integer.parseInt(txtUserId);
				BkapRespone res = new BkapRespone();
				res.setCode(200);
				if (userId > 0) {
					if (method.equals("delete")) {
						boolean isOk = cws.deleteUser(userId);
						res.setSuccess(isOk);
						if (isOk) {
							res.setMessage("Xoá người dùng " + userId + " thành công");
						} else {
							res.setMessage("Xoá người dùng " + userId + " thất bại");
						}
					} else {
						res.setSuccess(false);
						res.setMessage("Phương thức không tồn tại");
					}
				} else {
					res.setSuccess(false);
					res.setMessage("Người dùng không tồn tại");
				}
				Gson gson = new Gson();
				String json = gson.toJson(res);

				PrintWriter out = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.print(json);
				out.flush();
			} else {
				List<User> users = cws.getUsers();
				
				request.setAttribute("title", "Quản lý người dùng");
				request.setAttribute("active2", true);
				request.setAttribute("user", user);
				

				request.setAttribute("listUser", users);
				request.getRequestDispatcher("manage-users.jsp").forward(request, response);
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
