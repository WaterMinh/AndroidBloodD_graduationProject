package com.bkap.client;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bkap.entity.DonationPlace;
import com.bkap.entity.History;
import com.bkap.entity.News;
import com.bkap.entity.Statistical;
import com.bkap.entity.User;
import com.bkap.utils.CallWebService;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
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
			List<News> news = cws.getNews();
			List<History> histories = cws.getHistories();
			for (History history : histories) {
				User u = cws.getUserById(history.getUserId());
				DonationPlace dnp = cws.getDonationPlaceById(history.getPlace());

				history.setFullName(u.getFullName());
				history.setPlaceName(dnp.getNamePlace());
			}
			
			List<User> selectUsers = users;
			if (users.size() >= 5)
				selectUsers = users.subList(users.size() - 5, users.size() - 1);
			
			List<History> selectHis = histories;
			if (histories.size() >= 5)
				selectHis = histories.subList(histories.size() - 5, histories.size() - 1);
			
			LocalDate today = LocalDate.now();
			int cMonth = today.getMonthValue();
			
			List<Integer> months = new ArrayList<Integer>();

			Supplier<Stream<User>> streamUser = () -> users.stream();
			Supplier<Stream<History>> streamHistory = () -> histories.stream();
			
			List<Integer> totalUsers = new ArrayList<Integer>();
			List<Integer> totalHistories = new ArrayList<Integer>();
			
			for (int i = cMonth - 5; i <= cMonth; i++) {
				int month = i;
				months.add(month);
				int totalU = streamUser.get().filter(item -> item.getCreateDate().toLocalDate().getMonthValue() == month).toList().size();
				totalUsers.add(totalU);
				
				int totalH = streamHistory.get().filter(item -> item.getDayDonation().toLocalDate().getMonthValue() == month).toList().size();
				totalHistories.add(totalH);
			}
			
			Statistical s = new Statistical(users.size(), donationPlaces.size(), news.size(), histories.size(), selectUsers, selectHis, months, totalUsers, totalHistories);
			request.setAttribute("statistical", s);
			
			request.setAttribute("title", "Quản trị Admin");
			request.setAttribute("active1", true);
			request.setAttribute("user", user);
			
			request.getRequestDispatcher("admin.jsp").forward(request, response);
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
