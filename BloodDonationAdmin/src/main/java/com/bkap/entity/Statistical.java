package com.bkap.entity;

import java.util.List;

public class Statistical {
	private int totalUser;
	private int totalPlace;
	private int totalNews;
	private int totalHistory;
	private List<User> users;
	private List<History> histories;
	private List<Integer> months;
	private List<Integer> totalHistories;
	private List<Integer> totalUsers;
	
	public Statistical() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Statistical(int totalUser, int totalPlace, int totalNews, int totalHistory, List<User> users,
			List<History> histories, List<Integer> months, List<Integer> totalHistories, List<Integer> totalUsers) {
		super();
		this.totalUser = totalUser;
		this.totalPlace = totalPlace;
		this.totalNews = totalNews;
		this.totalHistory = totalHistory;
		this.users = users;
		this.histories = histories;
		this.months = months;
		this.totalHistories = totalHistories;
		this.totalUsers = totalUsers;
	}


	public int getTotalUser() {
		return totalUser;
	}

	public void setTotalUser(int totalUser) {
		this.totalUser = totalUser;
	}

	public int getTotalPlace() {
		return totalPlace;
	}

	public void setTotalPlace(int totalPlace) {
		this.totalPlace = totalPlace;
	}

	public int getTotalNews() {
		return totalNews;
	}

	public void setTotalNews(int totalNews) {
		this.totalNews = totalNews;
	}

	public int getTotalHistory() {
		return totalHistory;
	}

	public void setTotalHistory(int totalHistory) {
		this.totalHistory = totalHistory;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<History> getHistories() {
		return histories;
	}

	public void setHistories(List<History> histories) {
		this.histories = histories;
	}

	public List<Integer> getMonths() {
		return months;
	}

	public void setMonths(List<Integer> months) {
		this.months = months;
	}

	public List<Integer> getTotalHistories() {
		return totalHistories;
	}

	public void setTotalHistories(List<Integer> totalHistories) {
		this.totalHistories = totalHistories;
	}

	public List<Integer> getTotalUsers() {
		return totalUsers;
	}

	public void setTotalUsers(List<Integer> totalUsers) {
		this.totalUsers = totalUsers;
	}
}
