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
import com.bkap.entity.DonationPlace;
import com.bkap.entity.User;
import com.bkap.utils.CallWebService;
import com.google.gson.Gson;

/**
 * Servlet implementation class AddDonationPlace
 */
@WebServlet("/AddDonationPlace")
public class AddDonationPlace extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDonationPlace() {
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
			String namePlace = request.getParameter("namePlace");
			if (namePlace != null && !namePlace.isEmpty()) {
				BkapRespone res = new BkapRespone();
				res.setCode(200);

				CallWebService cws = new CallWebService();
				List<DonationPlace> donationPlaces = cws.getDonationPlaces();
				Supplier<Stream<DonationPlace>> stream = () -> donationPlaces.stream();
				
				String txtIdPlace = request.getParameter("idPlace");
				
				if (!stream.get().anyMatch(item -> item.getNamePlace().equals(namePlace)) || (txtIdPlace != null && !txtIdPlace.isEmpty())) {
					String address = request.getParameter("address");
					String txtDayStart = request.getParameter("dayStart");
					String txtDayEnd = request.getParameter("dayEnd");
					String timeOpen = request.getParameter("timeOpen");
					
					if (address != null && !address.isEmpty() && txtDayStart != null && !txtDayStart.isEmpty() && txtDayEnd != null && !txtDayEnd.isEmpty()) {
						if (txtIdPlace != null && !txtIdPlace.isEmpty()) {
							int idPlace = Integer.parseInt(txtIdPlace);
							if (idPlace > 0) {
								if (stream.get().anyMatch(
										item -> item.getAddress().equals(address) && item.getIdPlace() != idPlace)) {
									res.setSuccess(false);
									res.setMessage("?????a ch??? ???? t???n t???i");
								} else {
									java.sql.Date dayStart = null;
									java.sql.Date dayEnd = null;
									try {
										Date getDayStart = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(txtDayStart);
										dayStart = new java.sql.Date(getDayStart.getTime());
									} catch (ParseException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									try {
										Date getDayEnd = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(txtDayEnd);
										dayEnd = new java.sql.Date(getDayEnd.getTime());
									} catch (ParseException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

									if (dayStart != null && dayEnd != null && !dayStart.after(dayEnd)) {
										DonationPlace dnp = new DonationPlace(idPlace, namePlace, dayStart, dayEnd, address, timeOpen);
										boolean isOk = cws.updatePlace(dnp);
										
										if (isOk) {
											res.setSuccess(true);
											res.setMessage("C???p nh???p ?????a ??i???m hi???n m??u th??nh c??ng");
										} else {
											res.setSuccess(false);
											res.setMessage("C???p nh???p ?????a ??i???m hi???n m??u th???t b???i");
										}
									} else {
										res.setSuccess(false);
										res.setMessage("Ng??y k???t th??c kh??ng h???p l???");
									}
								}
							} else {
								res.setSuccess(false);
								res.setMessage("?????a ??i???m hi???n m??u kh??ng t???n t???i");
							}
						} else {
							if (stream.get().anyMatch(item -> item.getAddress().equals(address))) {
								res.setSuccess(false);
								res.setMessage("?????a ch??? ???? t???n t???i");
							} else {
								java.sql.Date dayStart = null;
								java.sql.Date dayEnd = null;
								try {
									Date getDayStart = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(txtDayStart);
									dayStart = new java.sql.Date(getDayStart.getTime());
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								try {
									Date getDayEnd = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(txtDayEnd);
									dayEnd = new java.sql.Date(getDayEnd.getTime());
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

								if (dayStart != null && dayEnd != null && !dayStart.after(dayEnd)) {
									DonationPlace dnp = new DonationPlace(namePlace, dayStart, dayEnd, address, timeOpen);
									boolean isOk = cws.insertPlace(dnp);
									
									if (isOk) {
										res.setSuccess(true);
										res.setMessage("Th??m m???i ?????a ??i???m hi???n m??u th??nh c??ng");
									} else {
										res.setSuccess(false);
										res.setMessage("Th??m m???i ?????a ??i???m hi???n m??u th???t b???i");
									}
								} else {
									res.setSuccess(false);
									res.setMessage("Ng??y k???t th??c kh??ng h???p l???");
								}
							}
						}
					} else {
						res.setSuccess(false);
						res.setMessage("B???n ch??a ??i???n ?????y ????? th??ng tin");
					}
				} else {
					res.setSuccess(false);
					res.setMessage("N??i di???n ra ???? t???n t???i");
				}
				
				Gson gson = new Gson();
				String json = gson.toJson(res);

				PrintWriter out = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.print(json);
				out.flush();
			} else {
				request.setAttribute("title", "Th??m m???i ?????a ??i???m hi???n m??u");
				request.setAttribute("active3", true);
				request.setAttribute("user", user);
				
				request.getRequestDispatcher("add-donation-place.jsp").forward(request, response);
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
