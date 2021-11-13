<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<style>
.Choicefile {
	display: block;
	background: #14142B;
	border: 1px solid #fff;
	color: #fff;
	width: 150px;
	text-align: center;
	text-decoration: none;
	cursor: pointer;
	padding: 5px 0px;
	border-radius: 5px;
	font-weight: 500;
	align-items: center;
	justify-content: center;
}

.Choicefile:hover {
	text-decoration: none;
	color: white;
}

#uploadfile, .removeimg {
	display: none;
}

#thumbbox {
	position: relative;
	width: 100%;
	margin-bottom: 20px;
}

.removeimg {
	height: 25px;
	position: absolute;
	background-repeat: no-repeat;
	top: 5px;
	left: 5px;
	background-size: 25px;
	width: 25px;
	/* border: 3px solid red; */
	border-radius: 50%;
}

.removeimg::before {
	-webkit-box-sizing: border-box;
	box-sizing: border-box;
	content: '';
	border: 1px solid red;
	background: red;
	text-align: center;
	display: block;
	margin-top: 11px;
	transform: rotate(45deg);
}

.removeimg::after {
	/* color: #FFF; */
	/* background-color: #DC403B; */
	content: '';
	background: red;
	border: 1px solid red;
	text-align: center;
	display: block;
	transform: rotate(-45deg);
	margin-top: -2px;
}
</style>
<main class="app-content">
	<div class="app-title">
		<ul class="app-breadcrumb breadcrumb">
			<li class="breadcrumb-item">Lịch sử hiến máu</li>
			<li class="breadcrumb-item"><a href="#">Thêm mới lịch sử</a></li>
		</ul>
		<div id="clock"></div>
	</div>
	<div class="row">
		<div class="col-md-12">

			<div class="tile">

				<h3 class="tile-title">Tạo mới lịch sử hiến máu</h3>
				<div class="tile-body">
					<form class="row">
						<input type="hidden" name="idHis" id="idHis" value="${u.idHis}" />
						<div class="form-group col-md-8">
							<label class="control-label">Người dùng</label> <select
								class="form-control" id="userId" required ${u.idHis > 0 ? "disabled" : ""}>
								<option>-- Chọn người dùng --</option>
								<c:forEach items="${listUser}" var="itemUser">
									<option value="${itemUser.userId}"
										${itemUser.userId == u.userId ? "selected" : ""}>${itemUser.fullName}
										- ${itemUser.email} - ${itemUser.telephone}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group col-md-4">
							<label class="control-label">Nơi hiến máu</label> <select
								class="form-control" id="place" required>
								<option>-- Chọn nơi hiến máu --</option>
								<c:forEach items="${listDonationPlace}" var="dnp">
									<option value="${dnp.idPlace}"
										${dnp.idPlace == u.place ? "selected" : ""}>${dnp.namePlace}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group  col-md-4">
							<label class="control-label">Cân nặng (kg)</label> <input
								class="form-control" type="number" name="weight" id="weight"
								value="${u.weight}" required>
						</div>
						<div class="form-group col-md-4">
							<label class="control-label">Số lượng (ml)</label> <input
								class="form-control" type="number" name="amountOfBlood"
								id="amountOfBlood" value="${u.amountOfBlood}" required>
						</div>
						<div class="form-group col-md-4">
							<label class="control-label">Ngày hiến máu</label> <input
								class="form-control" type="date" name="dayDonation"
								id="dayDonation"
								value="<fmt:formatDate value="${u.dayDonation}" pattern="yyyy-MM-dd" />">
						</div>
						<div class="form-group col-md-4">
							<input type="checkbox" id="tinhTrang" name="tinhTrang" ${u.tinhTrang?"checked":""}> <label
								for="tinhTrang" class="control-label">Đã từng hiến máu</label>
						</div>
						<div class="form-group col-md-4">
							<input type="checkbox" id="macBenh" name="macBenh" ${u.macBenh?"checked":""}> <label
								for="macBenh" class="control-label">Mắc bệnh nền</label>
						</div>
						<div class="form-group col-md-4">
							<input type="checkbox" id="sutCan" name="sutCan" ${u.sutCan?"checked":""}> <label
								for="sutCan" class="control-label">Sụt cân</label>
						</div>
						<div class="form-group col-md-4">
							<input type="checkbox" id="phauThuat" name="phauThuat" ${u.phauThuat?"checked":""}> <label
								for="phauThuat" class="control-label">Phẫu thuật</label>
						</div>
						<div class="form-group col-md-4">
							<input type="checkbox" id="xam" name="xam" ${u.xam?"checked":""}> <label
								for="xam" class="control-label">Xăm hình</label>
						</div>
						<div class="form-group col-md-4">
							<input type="checkbox" id="truyenMau" name="truyenMau" ${u.truyenMau?"checked":""}> <label
								for="truyenMau" class="control-label">Được truyền máu</label>
						</div>
						<div class="form-group col-md-4">
							<input type="checkbox" id="maTuy" name="maTuy" ${u.maTuy?"checked":""}> <label
								for="maTuy" class="control-label">Sử dụng ma tuý</label>
						</div>
						<div class="form-group col-md-4">
							<input type="checkbox" id="quanHe" name="quanHe" ${u.quanHe?"checked":""}> <label
								for="quanHe" class="control-label">Quan hệ nhiễm bệnh</label>
						</div>
						<div class="form-group col-md-4">
							<input type="checkbox" id="quanHeCungGioi" name="quanHeCungGioi" ${u.quanHeCungGioi?"checked":""}> <label
								for="quanHeCungGioi" class="control-label">Quan hệ cùng giới</label>
						</div>
						<div class="form-group col-md-4">
							<input type="checkbox" id="vaccine" name="vaccine" ${u.vaccine?"checked":""}> <label
								for="vaccine" class="control-label">Tiêm vaccine</label>
						</div>
						<div class="form-group col-md-4">
							<input type="checkbox" id="vungDich" name="vungDich" ${u.vungDich?"checked":""}> <label
								for="vungDich" class="control-label">Đến từ vùng dịch</label>
						</div>
						<div class="form-group col-md-4">
							<input type="checkbox" id="benh" name="benh" ${u.benh?"checked":""}> <label
								for="benh" class="control-label">Ho, nhức đầu, sốt</label>
						</div>
						<div class="form-group col-md-4">
							<input type="checkbox" id="diKham" name="diKham" ${u.diKham?"checked":""}> <label
								for="diKham" class="control-label">Xét nghiệm răng</label>
						</div>
						<div class="form-group col-md-4">
							<input type="checkbox" id="tanTat" name="tanTat" ${u.tanTat?"checked":""}> <label
								for="tanTat" class="control-label">Tàn tật</label>
						</div>
						<div class="form-group col-md-4">
							<input type="checkbox" id="mangThai" name="mangThai" ${u.mangThai?"checked":""}> <label
								for="mangThai" class="control-label">Mang thai</label>
						</div>
						<div class="form-group col-md-12">
							<input type="checkbox" id="thuocKS" name="thuocKS" ${u.thuocKS?"checked":""}> <label
								for="thuocKS" class="control-label">Dùng thuốc kháng sinh</label>
						</div>
						<div class="form-group col-md-12">
							<input type="checkbox" id="status" name="status" ${u.status?"checked":""}> <label
								for="status" class="control-label">Đã hoàn thành</label>
						</div>
					</form>
				</div>
				<button class="btn btn-save" type="button" onclick="save()">Lưu lại</button>
				<a class="btn btn-cancel" href="ManageHistories">Hủy bỏ</a>
			</div>
		</div>
	</div>
</main>


<!-- Essential javascripts for application to work-->
<script src="js/admin/jquery-3.2.1.min.js"></script>
<script src="js/admin/popper.min.js"></script>
<script src="js/admin/bootstrap.min.js"></script>
<script src="js/admin/main.js"></script>
<!-- The javascript plugin to display page loading on top-->
<script src="js/admin/plugins/pace.min.js"></script>
<script>
	function save() {
		var idHis = document.getElementById("idHis").value;
		var userId = document.getElementById("userId").value;
		var weight = document.getElementById("weight").value;
		var amountOfBlood = document.getElementById("amountOfBlood").value;
		var dayDonation = document.getElementById("dayDonation").value;
		var place = document.getElementById("place").value;
		
		var tinhTrang = $("#tinhTrang").is(":checked") ? "true" : "false";
		var macBenh = $("#macBenh").is(":checked") ? "true" : "false";
		var sutCan = $("#sutCan").is(":checked") ? "true" : "false";
		var phauThuat = $("#phauThuat").is(":checked") ? "true" : "false";
		var xam = $("#xam").is(":checked") ? "true" : "false";
		var truyenMau = $("#truyenMau").is(":checked") ? "true" : "false";
		var maTuy = $("#maTuy").is(":checked") ? "true" : "false";
		var quanHe = $("#quanHe").is(":checked") ? "true" : "false";
		var quanHeCungGioi = $("#quanHeCungGioi").is(":checked") ? "true" : "false";
		var vaccine = $("#vaccine").is(":checked") ? "true" : "false";
		var vungDich = $("#vungDich").is(":checked") ? "true" : "false";
		var benh = $("#benh").is(":checked") ? "true" : "false";
		var thuocKS = $("#thuocKS").is(":checked") ? "true" : "false";
		var diKham = $("#diKham").is(":checked") ? "true" : "false";
		var tanTat = $("#tanTat").is(":checked") ? "true" : "false";
		var mangThai = $("#mangThai").is(":checked") ? "true" : "false";
		var status = $("#status").is(":checked") ? "true" : "false";
		
		if (userId == "" || dayDonation == "" || userId == "-- Chọn người dùng --" || place == "" || place == "-- Chọn nơi hiến máu --") {
	        swal({
	            title: "",
	            text: "Bạn chưa điền đầy đủ thông tin",
	            icon: "error",
	            close: true,
	            button: "Thử lại",
	          });
	    } else {
	    	$.ajax({
				url: "AddHistory",
				type: "post",
				dataType: "text",
				data: {
					idHis: idHis,
					userId: userId,
					weight: weight,
					amountOfBlood: amountOfBlood,
					dayDonation: dayDonation,
					place: place,
					tinhTrang: tinhTrang,
					macBenh: macBenh,
					sutCan: sutCan,
					phauThuat: phauThuat,
					xam: xam,
					truyenMau: truyenMau,
					maTuy: maTuy,
					quanHe: quanHe,
					quanHeCungGioi: quanHeCungGioi,
					vaccine: vaccine,
					vungDich: vungDich,
					benh: benh,
					thuocKS: thuocKS,
					diKham: diKham,
					tanTat: tanTat,
					mangThai: mangThai,
					status: status
				},
				success: function(result) {
					var json = $.parseJSON(result);
					if (json.success) {
						window.location = "ManageHistories";
					} else {
						swal({
							title: "",
							text: json.message,
							icon: "error",
							close: true,
							button: "Thử lại",
						});
					}
				},
				error: function(xhr, status, error) {
					swal({
						title: "",
						text: "Đã xảy ra lỗi",
						icon: "error",
						close: true,
						button: "Thử lại",
					});
				}
			});
	    }
	}
	
</script>
<%@ include file="footer.jsp"%>