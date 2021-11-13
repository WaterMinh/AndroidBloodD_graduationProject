package com.bkap.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.bkap.dao.INewsDAO;
import com.bkap.dao.ImplNewsDAO;
import com.bkap.entity.Messages;
import com.bkap.entity.News;
import com.google.gson.Gson;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.FormDataParam;

@Path("/bloodDonate2/")
public class NewsService {
	@GET
	@Path("list-news")
	@Produces(MediaType.APPLICATION_JSON)
	public String getListNews() {
		INewsDAO dao = new ImplNewsDAO();
		List<News> lst = dao.selectAll();
		
		Gson gson = new Gson();
		String json = gson.toJson(lst);
		
		return json;
	}
	
	@POST
	@Path("get-news-by-id")
	@Produces(MediaType.APPLICATION_JSON)
	public String getNewsById(String json) {
		Gson gson = new Gson();
		int idNews = gson.fromJson(json, Integer.class);
		
		INewsDAO dao = new ImplNewsDAO();
		News newss = dao.selectById(idNews);
		
		return gson.toJson(newss);
	}
	
	@Path("insert-news")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String insert(String json) {
		Gson gson = new Gson();
		News news = gson.fromJson(json, News.class);
		INewsDAO dao = new ImplNewsDAO();
		boolean saveOk = dao.insert(news);
				
		return gson.toJson(saveOk);
		
	}
	
	@POST
	@Path("update-news")
	@Produces(MediaType.APPLICATION_JSON)
	public String update(String json) {
		
		Gson gson = new Gson();
		News news = gson.fromJson(json, News.class);
		INewsDAO dao = new ImplNewsDAO();

		boolean updateOk = dao.update(news);

		return gson.toJson(updateOk);
	}
	
	@POST
	@Path("delete-news")
	@Produces(MediaType.APPLICATION_JSON)
	public String delete(String json) {
		Gson gson = new Gson();
		int idNews = gson.fromJson(json, Integer.class);
		
		INewsDAO dao = new ImplNewsDAO();
		boolean deleteOk = dao.delete(idNews);
		
		return gson.toJson(deleteOk);
	}
	
	@Context
	private javax.servlet.ServletContext servletContext;
	private final String HOST_UPLOAD = "/Upload/News/";

	@POST
	@Path("upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String upload(@FormDataParam("image") InputStream is, @FormDataParam("image") FormDataContentDisposition fdcd, FormDataMultiPart form) {

		String filePath = HOST_UPLOAD + fdcd.getFileName();

		System.out.println("Filepath: " + filePath);

		Gson gson = new Gson();
		boolean isOk = saveFile(is, filePath);
		if (isOk)
			return gson.toJson(filePath);
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

