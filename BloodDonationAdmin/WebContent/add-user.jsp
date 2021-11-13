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
			<li class="breadcrumb-item">Danh sách người dùng</li>
			<li class="breadcrumb-item"><a href="#">Thêm người dùng</a></li>
		</ul>
		<div id="clock"></div>
	</div>
	<div class="row">
		<div class="col-md-12">

			<div class="tile">

				<h3 class="tile-title">Tạo mới người dùng</h3>
				<div class="tile-body">
					<div class="row">
						<input type="hidden" name="userId" id="userId" value="${u.userId}" />
						<div class="form-group col-md-4">
							<label class="control-label">Họ và tên</label> <input
								class="form-control" type="text" name="fullName" id="fullName" value="${u.fullName}" required>
						</div>
						<div class="form-group col-md-4">
							<label class="control-label">Địa chỉ email</label> <input
								class="form-control" type="text" name="email" id="email" value="${u.email}" required>
						</div>
						<div class="form-group col-md-4">
							<label class="control-label">Mật khẩu</label> <input
								class="form-control" type="password" name="password" id="password" value="${u.password}" required>
						</div>
						<div class="form-group col-md-4">
							<label class="control-label">Địa chỉ thường trú</label> <input
								class="form-control" type="text" name="address" id="address" value="${u.address}" required>
						</div>
						<div class="form-group  col-md-4">
							<label class="control-label">Số điện thoại</label> <input
								class="form-control" type="number" name="telephone" id="telephone" value="${u.telephone}" required>
						</div>
						<div class="form-group col-md-4">
							<label class="control-label">Ngày sinh</label> <input
								class="form-control" type="date" name="birthday" id="birthday" value="<fmt:formatDate value="${u.birthday}" pattern="yyyy-MM-dd" />">
						</div>
						<div class="form-group col-md-4">
							<label class="control-label">Số CMND/CCCD</label> <input
								class="form-control" type="number" name="identityCard" id="identityCard" value="${u.identityCard}" required>
						</div>
						<div class="form-group col-md-4">
							<label class="control-label">Nghề nghiệp</label> <input
								class="form-control" type="text" name="job" id="job" value="${u.job}" required>
						</div>
						<div class="form-group col-md-4">
							<label class="control-label">Nhóm máu</label> <input
								class="form-control" type="text" name="bloodType" id="bloodType" value="${u.bloodType}" required>
						</div>
						<div class="form-group col-md-4">
							<label class="control-label">Giới tính</label> <select
								class="form-control" id="gender" required>
								<option>-- Chọn giới tính --</option>
								<option value="1" ${u.gender == true ? "selected" : ""}>Nam</option>
								<option value="0" ${u.gender == false ? "selected" : ""}>Nữ</option>
							</select>
						</div>

						<div class="form-group col-md-4">
							<label for="exampleSelect1" class="control-label">Chức vụ</label>
							<select class="form-control" id="permission" required>
								<option>-- Chọn chức vụ --</option>
								<option value="0" ${u.permission == 0 ? "selected" : ""}>Người dùng</option>
								<option value="1" ${u.permission == 1 ? "selected" : ""}>Admin</option>
							</select>
						</div>

						<div class="form-group col-md-4">
							<label class="control-label">Ảnh 3x4</label>
							<div id="myfileupload">
								<input type="file" id="uploadfile" name="avatar"
									onchange="readURL(this);" />
							</div>
							<div id="thumbbox">
								<img height="300" width="300" alt="Thumb image" id="thumbimage"
									style="display: none" /> <a class="removeimg"
									href="javascript:"></a>
							</div>
							<div id="boxchoice">
								<a href="javascript:" class="Choicefile"><i
									class='bx bx-upload'></i></a>
								<p style="clear: both"></p>
							</div>

						</div>
					</div>
				</div>
				<button class="btn btn-save" type="button" onclick="save()">Lưu lại</button>
				<a class="btn btn-cancel" href="ManageUsers">Hủy bỏ</a>
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
		var userId = document.getElementById("userId").value;
		var fullName = document.getElementById("fullName").value;
		var email = document.getElementById("email").value;
		var password = document.getElementById("password").value;
		var birthday = document.getElementById("birthday").value;
		var gender = document.getElementById("gender").value;
		var telephone = document.getElementById("telephone").value;
		var address = document.getElementById("address").value;
		var identityCard = document.getElementById("identityCard").value;
		var job = document.getElementById("job").value;
		var bloodType = document.getElementById("bloodType").value;
		var permission = document.getElementById("permission").value;
		var avatar = document.getElementById("uploadfile").files[0];
		
		if ((userId == "" && avatar == null) || fullName == "" || email == "" || password == "" || birthday == "" || gender == "" || gender == "-- Chọn giới tính --" || telephone == "" || address == "" || identityCard == "" || bloodType == "" || permission == "" || permission == "-- Chọn chức vụ --") {
	        swal({
	            title: "",
	            text: "Bạn chưa điền đầy đủ thông tin",
	            icon: "error",
	            close: true,
	            button: "Thử lại",
	          });
	    } else if (!RegexEmail(email)) {
			swal({
	            title: "",
	            text: "Email không đúng định dạng",
	            icon: "error",
	            close: true,
	            button: "Thử lại",
	          });
		} else if (password.length < 6) {
			swal({
	            title: "",
	            text: "Mật khẩu phải lớn hơn 6 kí tự",
	            icon: "error",
	            close: true,
	            button: "Thử lại",
	          });
	    } else {
			if (avatar != null) {
				var formData = new FormData();
				formData.append("avatar", avatar);
				formData.append("identityCard", identityCard);
				$.ajax({
					url : "http://192.168.0.110:8080/BloodDonation/api/bloodDonate3/upload",
					type : "post",
					contentType : false,
					processData : false,
					data : formData,
					success : function(result) {
						var json = $.parseJSON(result);
						if (json != null && json != "") {
							$.ajax({
								url : "AddUser",
								type : "post",
								dataType : 'text',
								data : {
									userId : userId,
									fullName : fullName,
									email : email,
									password : password,
									birthday : birthday,
									gender : gender,
									telephone : telephone,
									address : address,
									identityCard : identityCard,
									job : job,
									bloodType : bloodType,
									permission : permission,
									avatar : json
								},
								success : function(result) {
									var json = $
											.parseJSON(result);
									if (json.success) {
										window.location = "ManageUsers";
									} else {
										swal({
											title : "",
											text : json.message,
											icon : "error",
											close : true,
											button : "Thử lại",
										});
									}
								},
								error : function(xhr, status,
										error) {
									swal({
										title : "",
										text : "Đã xảy ra lỗi",
										icon : "error",
										close : true,
										button : "Thử lại",
									});
								}
							});
						} else {
							swal({
								title : "",
								text : "Upload ảnh thất bại",
								icon : "error",
								close : true,
								button : "Thử lại",
							});
						}
					},
					error : function(xhr, status, error) {
						swal({
							title : "",
							text : "Không kết nối được tới server",
							icon : "error",
							close : true,
							button : "Thử lại",
						});
					}
				});
			} else {
				$.ajax({
					url : "AddUser",
					type : "post",
					dataType : 'text',
					data : {
						userId : userId,
						fullName : fullName,
						email : email,
						password : password,
						birthday : birthday,
						gender : gender,
						telephone : telephone,
						address : address,
						identityCard : identityCard,
						job : job,
						bloodType : bloodType,
						permission : permission
					},
					success : function(result) {
						var json = $
								.parseJSON(result);
						if (json.success) {
							window.location = "ManageUsers";
						} else {
							swal({
								title : "",
								text : json.message,
								icon : "error",
								close : true,
								button : "Thử lại",
							});
						}
					},
					error : function(xhr, status,
							error) {
						swal({
							title : "",
							text : "Đã xảy ra lỗi",
							icon : "error",
							close : true,
							button : "Thử lại",
						});
					}
				});
			}
		}
	}

	function RegexEmail(emailInput) {
		var emailRegexStr = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
		var isvalid = emailRegexStr.test(emailInput);
		if (!isvalid) {
			return false;
		} else {
			return true;
		}
	}

	function readURL(input, thumbimage) {
		if (input.files && input.files[0]) { //Sử dụng  cho Firefox - chrome
			var reader = new FileReader();
			reader.onload = function(e) {
				$("#thumbimage").attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		} else { // Sử dụng cho IE
			$("#thumbimage").attr('src', input.value);

		}
		$("#thumbimage").show();
		$('.filename').text($("#uploadfile").val());
		$('.Choicefile').css('background', '#14142B');
		$('.Choicefile').css('cursor', 'default');
		$(".removeimg").show();
		$(".Choicefile").unbind('click');

	}
	$(document)
			.ready(
					function() {
						$(".Choicefile").bind('click', function() {
							$("#uploadfile").click();
						});
						$(".removeimg")
								.click(
										function() {
											$("#thumbimage").attr('src', '')
													.hide();
											$("#myfileupload")
													.html(
															'<input type="file" id="uploadfile"  onchange="readURL(this);" />');
											$(".removeimg").hide();
											$(".Choicefile").bind(
													'click',
													function() {
														$("#uploadfile")
																.click();
													});
											$('.Choicefile').css('background',
													'#14142B');
											$('.Choicefile').css('cursor',
													'pointer');
											$(".filename").text("");
										});
					})
</script>
<%@ include file="footer.jsp"%>