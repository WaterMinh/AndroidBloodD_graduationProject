<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<main class="app-content">
	<div class="row">
		<div class="col-md-12">
			<div class="app-title">
				<ul class="app-breadcrumb breadcrumb">
					<li class="breadcrumb-item"><a href="#"><b>Bảng điều
								khiển</b></a></li>
				</ul>
				<div id="clock"></div>
			</div>
		</div>
	</div>
	<div class="row">
		<!--Left-->
		<div class="col-md-12 col-lg-6">
			<div class="row">
				<!-- col-6 -->
				<div class="col-md-6">
					<div class="widget-small primary coloured-icon">
						<i class='icon bx bxs-user-account fa-3x'></i>
						<div class="info">
							<h4>Tổng người dùng</h4>
							<p>
								<b>${statistical.totalUser} người dùng</b>
							</p>
							<p class="info-tong">Tổng số người dùng đăng ký hiến máu.</p>
						</div>
					</div>
				</div>
				<!-- col-6 -->
				<div class="col-md-6">
					<div class="widget-small info coloured-icon">
						<i class='icon bx bxs-data fa-3x'></i>
						<div class="info">
							<h4>Tổng địa điểm</h4>
							<p>
								<b>${statistical.totalPlace} địa điểm</b>
							</p>
							<p class="info-tong">Tổng số địa điểm hiến máu.</p>
						</div>
					</div>
				</div>
				<!-- col-6 -->
				<div class="col-md-6">
					<div class="widget-small warning coloured-icon">
						<i class='icon bx bxs-shopping-bags fa-3x'></i>
						<div class="info">
							<h4>Tổng tin tức</h4>
							<p>
								<b>${statistical.totalNews} tin tức</b>
							</p>
							<p class="info-tong">Tổng số tin tức hiến máu.</p>
						</div>
					</div>
				</div>
				<!-- col-6 -->
				<div class="col-md-6">
					<div class="widget-small danger coloured-icon">
						<i class='icon bx bxs-error-alt fa-3x'></i>
						<div class="info">
							<h4>Tổng số lần hiến máu</h4>
							<p>
								<b>${statistical.totalHistory} lần</b>
							</p>
							<p class="info-tong">Tổng số lần người dùng đăng ký hiến máu.</p>
						</div>
					</div>
				</div>
				<!-- col-12 -->
				<div class="col-md-12">
					<div class="tile">
						<h3 class="tile-title">Lịch hiến máu mới</h3>
						<div>
							<table class="table table-bordered">
								<thead>
									<tr>
										<th>ID</th>
										<th>Tên</th>
										<th>Ngày</th>
										<th>Địa điểm</th>
									</tr>

								</thead>
								<tbody>
									<c:forEach items="${statistical.histories}" var="h">
										<tr>
											<td>${h.idHis}</td>
											<td>${h.fullName}</td>
											<td>${h.dayDonation}</td>
											<td>${h.placeName}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- / div trống-->
					</div>
				</div>
				<!-- / col-12 -->
				<!-- col-12 -->
				<div class="col-md-12">
					<div class="tile">
						<h3 class="tile-title">Người dùng mới</h3>
						<div>
							<table class="table table-hover">
								<thead>
									<tr>
										<th>ID</th>
										<th>Tên</th>
										<th>Email</th>
										<th>Số điện thoại</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${statistical.users}" var="u">
										<tr>
											<td>${u.userId}</td>
											<td>${u.fullName}</td>
											<td>${u.email}</td>
											<td>${u.telephone}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>

					</div>
				</div>
				<!-- / col-12 -->
			</div>
		</div>
		<!--END left-->
		<!--Right-->
		<div class="col-md-12 col-lg-6">
			<div class="row">
				<div class="col-md-12">
					<div class="tile">
						<h3 class="tile-title">Thống kê hiến máu 6 tháng gần nhất</h3>
						<div class="embed-responsive embed-responsive-16by9">
							<canvas class="embed-responsive-item" id="lineChart"></canvas>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="tile">
						<h3 class="tile-title">Thống kê người dùng mới 6 tháng gần nhất</h3>
						<div class="embed-responsive embed-responsive-16by9">
							<canvas class="embed-responsive-item" id="barChart"></canvas>
						</div>
					</div>
				</div>
			</div>

		</div>
		<!--END right-->
	</div>
</main>

<div class="text-center" style="font-size: 13px">
	<p>
		<b>Copyright <script type="text/javascript">
            document.write(new Date().getFullYear());
          </script> <i class="far fa-copyright" aria-hidden="true"></i> Phần mềm quản lý hiến máu
		</b>
	</p>
</div>

<script src="js/admin/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="js/admin/popper.min.js"></script>
<script src="https://unpkg.com/boxicons@latest/dist/boxicons.js"></script>
<!--===============================================================================================-->
<script src="js/admin/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="js/admin/main.js"></script>
<!--===============================================================================================-->
<script src="js/admin/plugins/pace.min.js"></script>
<!--===============================================================================================-->
<script type="text/javascript" src="js/admin/plugins/chart.js"></script>
<!--===============================================================================================-->
<script type="text/javascript">
    var dataH = {
      labels: [<c:forEach items="${statistical.months}" var="m">"Tháng ${m}",</c:forEach>],
      datasets: [
	      {
	        label: "Dữ liệu",
	        fillColor: "rgba(9, 109, 239, 0.651)  ",
	        pointColor: "rgb(9, 109, 239)",
	        strokeColor: "rgb(9, 109, 239)",
	        pointStrokeColor: "rgb(9, 109, 239)",
	        pointHighlightFill: "rgb(9, 109, 239)",
	        pointHighlightStroke: "rgb(9, 109, 239)",
	        data: [<c:forEach items="${statistical.totalHistories}" var="th">${th},</c:forEach>]
	      }
      ]
    };
    
    var dataU = {
    	      labels: [<c:forEach items="${statistical.months}" var="m">"Tháng ${m}",</c:forEach>],
    	      datasets: [
    		      {
    		        label: "Dữ liệu",
    		        fillColor: "rgba(9, 109, 239, 0.651)  ",
    		        pointColor: "rgb(9, 109, 239)",
    		        strokeColor: "rgb(9, 109, 239)",
    		        pointStrokeColor: "rgb(9, 109, 239)",
    		        pointHighlightFill: "rgb(9, 109, 239)",
    		        pointHighlightStroke: "rgb(9, 109, 239)",
    		        data: [<c:forEach items="${statistical.totalUsers}" var="tu">${tu},</c:forEach>]
    		      }
    	      ]
    	    };
    var ctxl = $("#lineChart").get(0).getContext("2d");
    var lineChart = new Chart(ctxl).Line(dataU);

    var ctxb = $("#barChart").get(0).getContext("2d");
    var barChart = new Chart(ctxb).Bar(dataH);
  </script>

<%@ include file="footer.jsp"%>