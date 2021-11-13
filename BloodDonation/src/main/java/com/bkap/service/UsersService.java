package com.bkap.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.bkap.dao.IUserDAO;
import com.bkap.dao.ImplUserDAO;
import com.bkap.entity.Messages;
import com.bkap.entity.Users;
import com.bkap.model.BkResponse;
import com.google.gson.Gson;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.FormDataParam;


@Path("/bloodDonate3/")
public class UsersService {
	@GET	
	@Path("hello")
	@Produces(MediaType.APPLICATION_JSON)
	public String hello() {
		return "User Server đã truy vẫn ok";
	}
	
	@POST
	@Path("user-login")
	@Produces(MediaType.APPLICATION_JSON)
	public String userLogin (String json) {
		Gson gson = new Gson();
		Users u = gson.fromJson(json, Users.class);

		IUserDAO dao = new ImplUserDAO();
		Users users = dao.login(u.getEmail(), u.getPassword());
		return gson.toJson(users);
	}
	
	@GET
	@Path("get-user")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUser () {
		Gson gson = new Gson();

		IUserDAO dao = new ImplUserDAO();
		List<Users> users = dao.selectAll();
		return gson.toJson(users);
	}
	
	@POST
	@Path("get-user-by-id")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUserById (String json) {
		Gson gson = new Gson();
		int userId = gson.fromJson(json, Integer.class);
		
		IUserDAO dao = new ImplUserDAO();
		Users users = dao.selectById(userId);
		return gson.toJson(users);
	}
	
	@POST
	@Path("insert-user")
	@Produces(MediaType.APPLICATION_JSON)
	public String insertUser(String json) {
		Gson gson = new Gson();
		Users users = gson.fromJson(json, Users.class);

		IUserDAO dao = new ImplUserDAO();
		boolean isOk = dao.insert(users);
		if (isOk) {
			return gson.toJson(new Messages(100, "Thêm mới người dùng thành công!!"));
		} else {
			return gson.toJson(new Messages(101, "Thêm mới người dùng thất bại!!"));
		}
	}
	
//	@POST
//	@Path("update-user")
//	@Produces(MediaType.APPLICATION_JSON)
//	public String updateUser(String json) {
//		Gson gson = new Gson();
//		Users users = gson.fromJson(json, Users.class);
//
//		IUserDAO dao = new ImplUserDAO();
//		
//		Users oldUser = dao.selectById(users.getUserId());
//		oldUser.setFullName(users.getFullName());
//		oldUser.setBirthday(users.getBirthday());
//		oldUser.setPassword(users.getPassword());
//		oldUser.setTelephone(users.getTelephone());
//		oldUser.setEmail(users.getEmail());
//		oldUser.setIdentityCard(users.getIdentityCard());
//		oldUser.setJob(users.getJob());
//		oldUser.setAddress(users.getAddress());
//		
//		System.out.println("User : " + oldUser);
//		boolean isOk = dao.update(oldUser);
//		if (isOk) {
//			return gson.toJson(new Messages(100, "Cap nhat nguoi dung thanh cong"));
//		} else {
//			return gson.toJson(new Messages(101, "Cap nhat nguoi dung that bai"));
//		}
//	}
	@POST
	@Path("update-user")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateUser(String json) {
		Gson gson = new Gson();
		Users users = gson.fromJson(json, Users.class);
		
		IUserDAO dao = new ImplUserDAO();
		Users u = dao.selectById(users.getUserId());
		users.setCreateDate(u.getCreateDate());
		if (users.getAvatar() == null || users.getAvatar().isEmpty())
			users.setAvatar(u.getAvatar());

		if (users.getBloodType() == null || users.getBloodType().isEmpty())
			users.setBloodType(u.getBloodType());
		
		boolean isOk = dao.update(users);
		
		if (isOk)
			return gson.toJson(new BkResponse(100, "Cập nhập người dùng thành công"));
		
		return gson.toJson(new BkResponse(101, "Cập nhập người dùng thất bại"));
	}
	
	@POST
	@Path("delete-user")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteUser(String json) {
		Gson gson = new Gson();
		int users = gson.fromJson(json, Integer.class);

		IUserDAO dao = new ImplUserDAO();
		boolean isOk = dao.remove(users);
		if (isOk)
			return gson.toJson(new BkResponse(100, "Xoá người dùng thành công"));
		
		return gson.toJson(new BkResponse(101, "Xoá người dùng thất bại"));
	}
	/**
	 * Tao bien de upload file anh
	 */
	@Context
	private javax.servlet.ServletContext servletContext;
	private final String HOST_UPLOAD = "/Upload/";

	@POST
	@Path("upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String upload(@FormDataParam("avatar") InputStream is, @FormDataParam("avatar") FormDataContentDisposition fdcd, @FormDataParam("identityCard") String identityCard, @FormDataParam("userId") String userId, FormDataMultiPart form) {
		String name = fdcd.getFileName();
		String extension = name.substring(name.lastIndexOf("."));
		String filePath = HOST_UPLOAD + "avatar_" + identityCard + extension;

		System.out.println("Filepath: " + filePath);

		Gson gson = new Gson();
		boolean isOk = saveFile(is, filePath);
		if (isOk) {
			if (userId != null && !userId.isEmpty()) {
				try {
					int uId = Integer.parseInt(userId);
					IUserDAO dao = new ImplUserDAO();
					Users u = dao.selectById(uId);
					u.setAvatar(filePath);
					dao.update(u);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			return gson.toJson(filePath);
		}
			
		return null;
	}
	
	private boolean saveFile(InputStream is, String filePath) {
	// Ghi ra file >>> OutputStream
		try {
			String realPath = servletContext.getRealPath(filePath);
			System.out.println("Realpath: " + realPath);
			File file = new File(realPath);
			if (!file.exists()) {
				file.createNewFile();
			}
			System.out.println(file.getAbsolutePath());
	
			FileOutputStream fos = new FileOutputStream(file);
			int data;
			byte buffer[] = new byte[1024]; 
			while ((data = is.read(buffer)) != -1) {
				fos.write(buffer);
			}
	
			fos.flush(); 
			fos.close();
	
			return true;
		} catch (FileNotFoundException e) {
			System.out.println("Lỗi: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Lỗi: " + e.getMessage());
			e.printStackTrace();
		}
	
		return false;
	}



}
