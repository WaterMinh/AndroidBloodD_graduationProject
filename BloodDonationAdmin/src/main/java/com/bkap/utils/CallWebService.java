package com.bkap.utils;

import java.io.InputStream;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.bkap.entity.BkResponse;
import com.bkap.entity.DonationPlace;
import com.bkap.entity.History;
import com.bkap.entity.News;
import com.bkap.entity.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class CallWebService {
	
	public String urlApi = "http://192.168.0.110:8080/BloodDonation/api";
	
	public Boolean uploadAvatar(String avatar) {
		String api = urlApi + "/bloodDonate3/upload";
		Client client = new Client();
		WebResource resource = client.resource(api);

		InputStream is = getClass().getClassLoader().getResourceAsStream(avatar);
		
		ClientResponse response = resource.type(MediaType.MULTIPART_FORM_DATA).post(ClientResponse.class, is);
		
		Gson gson = new Gson();
		String data = response.getEntity(String.class);
		Boolean success = gson.fromJson(data, Boolean.class);
		
		return success;
	}
	
	public User checkLogin(User u) {
		String api = urlApi + "/bloodDonate3/user-login";
		
		Gson gson = new Gson();
		String json = gson.toJson(u);
		Client client = new Client();
		WebResource resource = client.resource(api);
		ClientResponse response = resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, json);
		String data = response.getEntity(String.class);
		if (data != null && !data.isEmpty()) {
			User user = gson.fromJson(data, User.class);
			if (user != null)
				return user;
		}
		
		return null;
	}
	
	public List<User> getUsers() {
		String api = urlApi + "/bloodDonate3/get-user";

		Client client = new Client();
		WebResource resource = client.resource(api);
		ClientResponse cliRes = resource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		String data = cliRes.getEntity(String.class);
		if (data != null && !data.isEmpty()) {
			Gson gson = new Gson();
			TypeToken<List<User>> token = new TypeToken<List<User>>() {};
			List<User> user = gson.fromJson(data, token.getType());
			if (user != null)
				return user;
		}
		
		return null;
	}
	
	public User getUserById(int userId) {
		String api = urlApi + "/bloodDonate3/get-user-by-id";
		Gson gson = new Gson();
		String json = gson.toJson(userId);
		Client client = new Client();
		WebResource resource = client.resource(api);
		ClientResponse response = resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, json);
		String data = response.getEntity(String.class);
		if (data != null && !data.isEmpty()) {
			User user = gson.fromJson(data, User.class);
			if (user != null) {
				return user;
			}
		}
		return null;
	}
	
	public Boolean insertUser(User u) {
		String api = urlApi + "/bloodDonate3/insert-user";
		
		Gson gson = new Gson();
		String json = gson.toJson(u);
		Client client = new Client();
		WebResource resource = client.resource(api);
		ClientResponse response = resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, json);
		String data = response.getEntity(String.class);
		BkResponse success = gson.fromJson(data, BkResponse.class);
		if (success.getId() == 100)
			return true;
		
		return false;
	}
	
	public Boolean updateUser(User u) {
		String api = urlApi + "/bloodDonate3/update-user";
		
		Gson gson = new Gson();
		String json = gson.toJson(u);
		Client client = new Client();
		WebResource resource = client.resource(api);
		ClientResponse response = resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, json);
		String data = response.getEntity(String.class);
		BkResponse success = gson.fromJson(data, BkResponse.class);
		if (success.getId() == 100)
			return true;
		
		return false;
	}
	
	public Boolean deleteUser(int userId) {
		String api = urlApi + "/bloodDonate3/delete-user";
		
		Gson gson = new Gson();
		String json = gson.toJson(userId);
		Client client = new Client();
		WebResource resource = client.resource(api);
		ClientResponse response = resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, json);
		String data = response.getEntity(String.class);
		BkResponse success = gson.fromJson(data, BkResponse.class);
		if (success.getId() == 100)
			return true;
		
		return false;
	}
	
	
	
	public List<DonationPlace> getDonationPlaces() {
		String api = urlApi + "/bloodDonate/list-places";
		
		Client client = new Client();
		WebResource resource = client.resource(api);
		ClientResponse cliRes = resource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		String data = cliRes.getEntity(String.class);
		if (data != null && !data.isEmpty()) {
			Gson gson = new Gson();
			TypeToken<List<DonationPlace>> token = new TypeToken<List<DonationPlace>>() {};
			List<DonationPlace> dp = gson.fromJson(data, token.getType());
			if (dp != null)
				return dp;
		}
		
		return null;
	}

	public DonationPlace getDonationPlaceById(int placeId) {
		String api = urlApi + "/bloodDonate/get-place-by-id";
		
		Gson gson = new Gson();
		String json = gson.toJson(placeId);
		Client client = new Client();
		WebResource resource = client.resource(api);
		ClientResponse response = resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, json);
		String data = response.getEntity(String.class);
		if (data != null && !data.isEmpty()) {
			DonationPlace dnp = gson.fromJson(data, DonationPlace.class);
			if (dnp != null) {
				return dnp;
			}
		}
		return null;
	}
	
	public Boolean insertPlace(DonationPlace u) {
		String api = urlApi + "/bloodDonate/insert-place";
		
		Gson gson = new Gson();
		String json = gson.toJson(u);
		Client client = new Client();
		WebResource resource = client.resource(api);
		ClientResponse response = resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, json);
		String data = response.getEntity(String.class);
		Boolean success = gson.fromJson(data, Boolean.class);
		
		return success;
	}
	
	public Boolean updatePlace(DonationPlace u) {
		String api = urlApi + "/bloodDonate/update-place";
		
		Gson gson = new Gson();
		String json = gson.toJson(u);
		Client client = new Client();
		WebResource resource = client.resource(api);
		ClientResponse response = resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, json);
		String data = response.getEntity(String.class);
		Boolean success = gson.fromJson(data, Boolean.class);
		
		return success;
	}
	
	public Boolean deletePlace(int placeId) {
		String api = urlApi + "/bloodDonate/delete-place";
		
		Gson gson = new Gson();
		String json = gson.toJson(placeId);
		Client client = new Client();
		WebResource resource = client.resource(api);
		ClientResponse response = resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, json);
		String data = response.getEntity(String.class);
		Boolean success = gson.fromJson(data, Boolean.class);
		
		return success;
	}

	
	public List<News> getNews() {
		String api = urlApi + "/bloodDonate2/list-news";
		
		Client client = new Client();
		WebResource resource = client.resource(api);
		ClientResponse cliRes = resource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		String data = cliRes.getEntity(String.class);
		if (data != null && !data.isEmpty()) {
			Gson gson = new Gson();
			TypeToken<List<News>> token = new TypeToken<List<News>>() {};
			List<News> news = gson.fromJson(data, token.getType());
			if (news != null)
				return news;
		}
		
		return null;
	}
	
	public News getNewsById(int newsId) {
		String api = urlApi + "/bloodDonate2/get-news-by-id";
		
		Gson gson = new Gson();
		String json = gson.toJson(newsId);
		Client client = new Client();
		WebResource resource = client.resource(api);
		ClientResponse response = resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, json);
		String data = response.getEntity(String.class);
		if (data != null && !data.isEmpty()) {
			News n = gson.fromJson(data, News.class);
			if (n != null) {
				return n;
			}
		}
		return null;
	}
	
	public Boolean insertNews(News u) {
		String api = urlApi + "/bloodDonate2/insert-news";
		
		Gson gson = new Gson();
		String json = gson.toJson(u);
		Client client = new Client();
		WebResource resource = client.resource(api);
		ClientResponse response = resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, json);
		String data = response.getEntity(String.class);
		Boolean success = gson.fromJson(data, Boolean.class);
		
		return success;
	}
	
	public Boolean updateNews(News u) {
		String api = urlApi + "/bloodDonate2/update-news";
		
		Gson gson = new Gson();
		String json = gson.toJson(u);
		Client client = new Client();
		WebResource resource = client.resource(api);
		ClientResponse response = resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, json);
		String data = response.getEntity(String.class);
		Boolean success = gson.fromJson(data, Boolean.class);
		
		return success;
	}
	
	public Boolean deleteNews(int newsId) {
		String api = urlApi + "/bloodDonate2/delete-news";
		
		Gson gson = new Gson();
		String json = gson.toJson(newsId);
		Client client = new Client();
		WebResource resource = client.resource(api);
		ClientResponse response = resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, json);
		String data = response.getEntity(String.class);
		Boolean success = gson.fromJson(data, Boolean.class);
		
		return success;
	}

	
	public List<History> getHistories() {
		String api = urlApi + "/bloodDonate1/list-history";
		
		Client client = new Client();
		WebResource resource = client.resource(api);
		ClientResponse cliRes = resource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		String data = cliRes.getEntity(String.class);
		if (data != null && !data.isEmpty()) {
			Gson gson = new Gson();
			TypeToken<List<History>> token = new TypeToken<List<History>>() {};
			List<History> histories  = gson.fromJson(data, token.getType());
			if (histories != null)
				return histories;
		}
		
		return null;
	}
	
	public History getHistoryById(int hisId) {
		String api = urlApi + "/bloodDonate1/get-history-by-id";
		
		Gson gson = new Gson();
		String json = gson.toJson(hisId);
		Client client = new Client();
		WebResource resource = client.resource(api);
		ClientResponse response = resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, json);
		String data = response.getEntity(String.class);
		if (data != null && !data.isEmpty()) {
			History h = gson.fromJson(data, History.class);
			if (h != null) {
				return h;
			}
		}
		return null;
	}

	public Boolean insertHistory(History u) {
		String api = urlApi + "/bloodDonate1/insert-history";
		
		Gson gson = new Gson();
		String json = gson.toJson(u);
		Client client = new Client();
		WebResource resource = client.resource(api);
		ClientResponse response = resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, json);
		String data = response.getEntity(String.class);
		BkResponse success = gson.fromJson(data, BkResponse.class);
		if (success.getId() == 100)
			return true;
		
		return false;
	}
	
	public Boolean updateHistory(History u) {
		String api = urlApi + "/bloodDonate1/update-history";
		
		Gson gson = new Gson();
		String json = gson.toJson(u);
		Client client = new Client();
		WebResource resource = client.resource(api);
		ClientResponse response = resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, json);
		String data = response.getEntity(String.class);
		BkResponse success = gson.fromJson(data, BkResponse.class);
		if (success.getId() == 100)
			return true;
		
		return false;
	}
	
	public Boolean deleteHistory(int hisId) {
		String api = urlApi + "/bloodDonate1/delete-history";
		
		Gson gson = new Gson();
		String json = gson.toJson(hisId);
		Client client = new Client();
		WebResource resource = client.resource(api);
		ClientResponse response = resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, json);
		String data = response.getEntity(String.class);
		BkResponse success = gson.fromJson(data, BkResponse.class);
		if (success.getId() == 100)
			return true;
		
		return false;
	}

}
