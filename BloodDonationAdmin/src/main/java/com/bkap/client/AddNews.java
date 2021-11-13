package com.bkap.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

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
 * Servlet implementation class AddNews
 */
@WebServlet("/AddNews")
public class AddNews extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNews() {
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
			String title = request.getParameter("title");
			if (title != null && !title.isEmpty()) {
				BkapRespone res = new BkapRespone();
				res.setCode(200);

				CallWebService cws = new CallWebService();
				List<News> news = cws.getNews();
				Supplier<Stream<News>> stream = () -> news.stream();
				
				String txtIdNews = request.getParameter("idNews");
				
				if (!stream.get().anyMatch(item -> item.getTitle().equals(title)) || (txtIdNews != null && !txtIdNews.isEmpty())) {
					String txtPublicDate = request.getParameter("publicDate");
					String info = request.getParameter("info");
					String image = request.getParameter("image");
					
					if (txtPublicDate != null && !txtPublicDate.isEmpty() && info != null && !info.isEmpty()) {
						if (txtIdNews != null && !txtIdNews.isEmpty()) {
							int idNews = Integer.parseInt(txtIdNews);
							if (idNews > 0) {
								java.sql.Date publicDate = null;
								try {
									Date getPublicDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(txtPublicDate);
									publicDate = new java.sql.Date(getPublicDate.getTime());
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								if (image == null || image.isEmpty()) {
									News getNews = cws.getNewsById(idNews);
									image = getNews.getImage();
								}
								
								News n = new News(idNews, image, publicDate, title, info);
								boolean isOk = cws.updateNews(n);

								if (isOk) {
									res.setSuccess(true);
									res.setMessage("Cập nhập tin tức thành công");
								} else {
									res.setSuccess(false);
									res.setMessage("Cập nhập tin tức  thất bại");
								}
							} else {
								res.setSuccess(false);
								res.setMessage("Tin tức không tồn tại");
							}
						} else {
							java.sql.Date publicDate = null;
							try {
								Date getPublicDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(txtPublicDate);
								publicDate = new java.sql.Date(getPublicDate.getTime());
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							News n = new News(image, publicDate, title, info);
							boolean isOk = cws.insertNews(n);

							if (isOk) {
								res.setSuccess(true);
								res.setMessage("Thêm mới tin tức thành công");
							} else {
								res.setSuccess(false);
								res.setMessage("Thêm mới tin tức thất bại");
							}
						}
					} else {
						res.setSuccess(false);
						res.setMessage("Bạn chưa điền đầy đủ thông tin");
					}
				} else {
					res.setSuccess(false);
					res.setMessage("Tiêu đề đã tồn tại");
				}
				
				Gson gson = new Gson();
				String json = gson.toJson(res);

				PrintWriter out = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.print(json);
				out.flush();
			} else {
				request.setAttribute("title", "Thêm mới tin tức");
				request.setAttribute("active4", true);
				request.setAttribute("user", user);

				request.getRequestDispatcher("add-news.jsp").forward(request, response);
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
