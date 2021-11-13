package com.bkap.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
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
import com.bkap.entity.User;
import com.bkap.utils.CallWebService;
import com.google.gson.Gson;

/**
 * Servlet implementation class AddUser
 */
@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
			String email = request.getParameter("email");
			
			if (email != null && !email.isEmpty()) {
				BkapRespone res = new BkapRespone();
				res.setCode(200);

				CallWebService cws = new CallWebService();
				List<User> users = cws.getUsers();
				Supplier<Stream<User>> stream = () -> users.stream();
				
				String txtUserId = request.getParameter("userId");
				if (!stream.get().anyMatch(item -> item.getEmail().equals(email)) || (txtUserId != null && !txtUserId.isEmpty())) {
					String fullName = request.getParameter("fullName");
					String password = request.getParameter("password");
					String txtBirthday = request.getParameter("birthday");
					String txtGender = request.getParameter("gender");
					String telephone = request.getParameter("telephone");
					String address = request.getParameter("address");
					String identityCard = request.getParameter("identityCard");
					String job = request.getParameter("job");
					String bloodType = request.getParameter("bloodType");
					String avatar = request.getParameter("avatar");
					int permission = Integer.parseInt(request.getParameter("permission"));

					if (fullName != null && !fullName.isEmpty() && password != null && !password.isEmpty()
							&& txtBirthday != null && !txtBirthday.isEmpty() && txtGender != null
							&& !txtGender.isEmpty() && telephone != null && !telephone.isEmpty() && address != null
							&& !address.isEmpty() && identityCard != null && !identityCard.isEmpty()
							&& bloodType != null && !bloodType.isEmpty()) {

						if (txtUserId != null && !txtUserId.isEmpty()) {
							int userId = Integer.parseInt(txtUserId);
							if (userId > 0) {
								if (stream.get().anyMatch(
										item -> item.getTelephone().equals(telephone) && item.getUserId() != userId)) {
									res.setSuccess(false);
									res.setMessage("Số điện thoại đã tồn tại");
								} else if (stream.get().anyMatch(item -> item.getIdentityCard().equals(identityCard)
										&& item.getUserId() != userId)) {
									res.setSuccess(false);
									res.setMessage("Số CMND/CCCD đã tồn tại");
								} else {
									boolean gender = true;
									if (txtGender.equals("0"))
										gender = false;
									java.sql.Date birthday = null;
									try {
										Date getBirthday = new SimpleDateFormat("yyyy-MM-dd").parse(txtBirthday);
										birthday = new java.sql.Date(getBirthday.getTime());
									} catch (ParseException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

									if (birthday != null && calculateAge(birthday.toLocalDate(), new Date().toInstant()
											.atZone(ZoneId.systemDefault()).toLocalDate()) >= 16) {
										java.sql.Date createDate = new java.sql.Date(new Date().getTime());
										
										if (avatar == null || avatar.isEmpty()) {
											User getUser = cws.getUserById(userId);
											avatar = getUser.getAvatar();
										}
										
										User u = new User(userId, fullName, password, avatar, birthday, gender,
												telephone, address, email, identityCard, job, bloodType, permission, createDate);

										boolean isOk = cws.updateUser(u);
										if (isOk) {
											res.setSuccess(true);
											res.setMessage("Cập nhập người dùng thành công");
										} else {
											res.setSuccess(false);
											res.setMessage("Cập nhập người dùng thất bại");
										}
									} else {
										res.setSuccess(false);
										res.setMessage("Người dùng phải lớn hơn 16 tuổi");
									}
								}
							} else {
								res.setSuccess(false);
								res.setMessage("Người dùng không tồn tại");
							}
						}
						else {
							if (stream.get().anyMatch(item -> item.getTelephone().equals(telephone))) {
								res.setSuccess(false);
								res.setMessage("Số điện thoại đã tồn tại");
							} else if (stream.get().anyMatch(item -> item.getIdentityCard().equals(identityCard))) {
								res.setSuccess(false);
								res.setMessage("Số CMND/CCCD đã tồn tại");
							} else {
								boolean gender = true;
								if (txtGender.equals("0"))
									gender = false;
								java.sql.Date birthday = null;
								try {
									Date getBirthday = new SimpleDateFormat("yyyy-MM-dd").parse(txtBirthday);
									birthday = new java.sql.Date(getBirthday.getTime());
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

								if (birthday != null && calculateAge(birthday.toLocalDate(),
										new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()) >= 16) {
									
									java.sql.Date createDate = new java.sql.Date(new Date().getTime());
									
									User u = new User(fullName, password, avatar, birthday, gender, telephone, address,
											email, identityCard, job, bloodType, permission, createDate);

									boolean isOk = cws.insertUser(u);
									if (isOk) {
										res.setSuccess(true);
										res.setMessage("Thêm mới người dùng thành công");
									} else {
										res.setSuccess(false);
										res.setMessage("Thêm mới người dùng thất bại");
									}
								} else {
									res.setSuccess(false);
									res.setMessage("Người dùng chưa đủ 16 tuổi");
								}
							}
						}
					} else {
						res.setSuccess(false);
						res.setMessage("Bạn chưa điền đầy đủ thông tin");
					}
				} else {
					res.setSuccess(false);
					res.setMessage("Email đã tồn tại");
				}
				
				Gson gson = new Gson();
				String json = gson.toJson(res);

				PrintWriter out = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.print(json);
				out.flush();
			} else {
				request.setAttribute("title", "Thêm mới người dùng");
				request.setAttribute("active2", true);
				request.setAttribute("user", user);
				request.getRequestDispatcher("add-user.jsp").forward(request, response);
			}
		} else {
			response.sendRedirect("Login");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
		if ((birthDate != null) && (currentDate != null)) {
			return Period.between(birthDate, currentDate).getYears();
		} else {
			return 0;
		}
	}
}
