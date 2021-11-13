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
import com.bkap.entity.News;
import com.bkap.entity.User;
import com.bkap.utils.CallWebService;
import com.google.gson.Gson;

/**
 * Servlet implementation class ManageNews
 */
@WebServlet("/ManageNews")
public class ManageNews extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageNews() {
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
			
			List<News> listNew = cws.getNews();
			
			String txtId = request.getParameter("id");
			String method = request.getParameter("method");
			String txtIdNews = request.getParameter("idNews");
			if (txtId != null && !txtId.isEmpty() && Integer.parseInt(txtId) > 0) {
				int id = Integer.parseInt(txtId);
				News n = cws.getNewsById(id);
				if (n != null) {
					request.setAttribute("title", "Cập nhập tin tức");
					request.setAttribute("active4", true);
					request.setAttribute("user", user);

					request.setAttribute("u", n);
					request.getRequestDispatcher("add-news.jsp").forward(request, response);
				} else {
					request.setAttribute("title", "Quản lý tin tức");
					request.setAttribute("active4", true);
					request.setAttribute("user", user);

					request.setAttribute("listNew", listNew);
					request.getRequestDispatcher("manage-news.jsp").forward(request, response);
				}
			} else if (method != null && !method.isEmpty() && txtIdNews != null && !txtIdNews.isEmpty()) {
				int idNews = Integer.parseInt(txtIdNews);
				BkapRespone res = new BkapRespone();
				res.setCode(200);
				if (idNews > 0) {
					if (method.equals("delete")) {
						boolean isOk = cws.deleteNews(idNews);
						res.setSuccess(isOk);
						if (isOk) {
							res.setMessage("Xoá tin tức ID " + idNews + " thành công");
						} else {
							res.setMessage("Xoá tin tức ID " + idNews + " thất bại");
						}
					} else {
						res.setSuccess(false);
						res.setMessage("Phương thức không tồn tại");
					}
				} else {
					res.setSuccess(false);
					res.setMessage("Tin tức không tồn tại");
				}
				Gson gson = new Gson();
				String json = gson.toJson(res);

				PrintWriter out = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.print(json);
				out.flush();
			} else {
				request.setAttribute("title", "Quản lý tin tức");
				request.setAttribute("active4", true);
				request.setAttribute("user", user);

				request.setAttribute("listNew", listNew);
				request.getRequestDispatcher("manage-news.jsp").forward(request, response);
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
