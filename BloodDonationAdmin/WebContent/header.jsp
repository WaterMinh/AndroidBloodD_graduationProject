<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<!DOCTYPE html>
<html lang="en">

<head>
	<title>${title}</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- Main CSS-->
	<link rel="stylesheet" type="text/css" href="css/admin/main.css">
	<link rel="stylesheet"
		href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
	<!-- or -->
	<link rel="stylesheet"
		href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">
	<!-- Font-icon css-->
	<link rel="stylesheet" type="text/css"
		href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-sweetalert/1.0.1/sweetalert.min.css">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
	<link rel="stylesheet"
		href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
</head>

<body onload="time()" class="app sidebar-mini rtl">
	<!-- Navbar-->
	<header class="app-header">
		<!-- Sidebar toggle button-->
		<a class="app-sidebar__toggle" href="#" data-toggle="sidebar"
			aria-label="Hide Sidebar"></a>
		<!-- Navbar Right Menu-->
		<ul class="app-nav">


			<!-- User Menu-->
			<li><a class="app-nav__item" href="Logout"><i
					class='bx bx-log-out bx-rotate-180'></i> </a></li>
		</ul>
	</header>
	<!-- Sidebar menu-->
	<div class="app-sidebar__overlay" data-toggle="sidebar"></div>
	<aside class="app-sidebar">
		<div class="app-sidebar__user">
			<img src="images/logo-bkap2.png"
				height="70px" alt="User Image">
			<div>
				<p class="app-sidebar__user-name">
					<b>${user.fullName}</b>
				</p>
				<p class="app-sidebar__user-designation">Chào mừng bạn trở lại</p>
			</div>
		</div>
		<hr>
		<ul class="app-menu">
			<li><a class="app-menu__item <c:if test="${active1}">active</c:if>" href="Admin"><i
					class='app-menu__icon bx bx-tachometer'></i><span
					class="app-menu__label">Bảng điều khiển</span></a></li>
			<li><a class="app-menu__item <c:if test="${active2}">active</c:if>" href="ManageUsers"><i
					class='app-menu__icon bx bx-id-card'></i> <span
					class="app-menu__label">Quản lý người dùng</span></a></li>
			<li><a class="app-menu__item <c:if test="${active3}">active</c:if>" href="ManagePlaces"><i
					class='app-menu__icon bx bx-user-voice'></i><span
					class="app-menu__label">Quản lý địa điểm</span></a></li>
			<li><a class="app-menu__item <c:if test="${active4}">active</c:if>" href="ManageNews"><i
					class='app-menu__icon bx bx-purchase-tag-alt'></i><span
					class="app-menu__label">Quản lý tin tức</span></a></li>
			<li><a class="app-menu__item <c:if test="${active5}">active</c:if>" href="ManageHistories"><i
					class='app-menu__icon bx bx-task'></i><span
					class="app-menu__label">Lịch sử hiến máu</span></a></li>
		</ul>
	</aside>