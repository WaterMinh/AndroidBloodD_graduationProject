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
			<li class="breadcrumb-item">Danh sách địa chỉ hiến máu</li>
			<li class="breadcrumb-item"><a href="#">Thêm địa chỉ hiến máu</a></li>
		</ul>
		<div id="clock"></div>
	</div>
	<div class="row">
		<div class="col-md-12">

			<div class="tile">

				<h3 class="tile-title">Tạo mới địa chỉ hiến máu</h3>
				<div class="tile-body">
					<form class="row">
						<input type="hidden" name="idPlace" id="idPlace" value="${u.idPlace}" />
						<div class="form-group col-md-6">
							<label class="control-label">Nơi diễn ra</label> <input
								class="form-control" type="text" name="namePlace" id="namePlace" value="${u.namePlace}" required>
						</div>
						<div class="form-group col-md-6">
							<label class="control-label">Địa chỉ</label> <input
								class="form-control" type="text" name="address" id="address" value="${u.address}" required>
						</div>
						<div class="form-group col-md-6">
							<label class="control-label">Ngày bắt đầu</label> <input
								class="form-control" type="datetime-local" name="dayStart" id="dayStart" value="<fmt:formatDate value="${u.dayStart}" pattern="yyyy-MM-dd'T'HH:mm" />">
						</div>
						<div class="form-group col-md-6">
							<label class="control-label">Ngày kết thúc</label> <input
								class="form-control" type="datetime-local" name="dayEnd" id="dayEnd" value="<fmt:formatDate value="${u.dayEnd}" pattern="yyyy-MM-dd'T'HH:mm" />">
						</div>
						<div class="form-group col-md-6">
							<label class="control-label">Giờ diễn ra</label> <input
								class="form-control" type="text" name="timeOpen" id="timeOpen" value="${u.timeOpen}">
						</div>
					</form>
				</div>
				<button class="btn btn-save" type="button" onclick="save()">Lưu lại</button>
				<a class="btn btn-cancel" href="ManagePlaces">Hủy bỏ</a>
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
		var idPlace = document.getElementById("idPlace").value;
		var namePlace = document.getElementById("namePlace").value;
		var address = document.getElementById("address").value;
		var dayStart = document.getElementById("dayStart").value;
		var dayEnd = document.getElementById("dayEnd").value;
		var timeOpen = document.getElementById("timeOpen").value;
		
		if (namePlace == "" || address == "" || dayStart == "" || dayEnd == "") {
	        swal({
	            title: "",
	            text: "Bạn chưa điền đầy đủ thông tin",
	            icon: "error",
	            close: true,
	            button: "Thử lại",
	          });
	    } else {
	    	$.ajax({
				url: "AddDonationPlace",
				type: "post",
				dataType: "text",
				data: {
					idPlace: idPlace,
					namePlace: namePlace,
					address: address,
					dayStart: dayStart,
					dayEnd: dayEnd,
					timeOpen: timeOpen
				},
				success: function(result) {
					var json = $.parseJSON(result);
					if (json.success) {
						window.location = "ManagePlaces";
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