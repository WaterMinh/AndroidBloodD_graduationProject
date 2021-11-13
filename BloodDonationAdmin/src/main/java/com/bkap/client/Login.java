package com.bkap.client;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
			response.sendRedirect("Admin");
		}
		else {
			CallWebService cws = new CallWebService();
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			if (email != null && !email.isEmpty() && password != null && !password.isEmpty()) {
				User userLogin = new User(email, password);
				user = cws.checkLogin(userLogin);
				BkapRespone res = new BkapRespone();
				res.setCode(200);
				if (user != null && user.getPermission() == 1) {
					session.setAttribute("user", user);
					res.setSuccess(true);
					res.setMessage("Đăng nhập thành công");
				} else if (user != null && user.getPermission() == 0) {
					res.setSuccess(false);
					res.setMessage("Không đủ quyền hạn");
				} else {
					res.setSuccess(false);
					res.setMessage("Tài khoản hoặc mật khẩu không đúng");
				}

				Gson gson = new Gson();
				String json = gson.toJson(res);

				PrintWriter out = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.print(json);
				out.flush();
			} else {
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
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
